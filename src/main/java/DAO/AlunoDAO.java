package DAO;

import model.Aluno;
import view.TelaLogin;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável pela manipulação dos dados da entidade {@link Aluno}
 * no banco de dados MySQL. Implementa operações CRUD e auxilia no gerenciamento
 * da conexão utilizando JDBC.
 *
 * <p>As credenciais utilizadas para conexão são obtidas na tela de login
 * através de {@link TelaLogin#userDB} e {@link TelaLogin#passwordDB}.</p>
 */
public class AlunoDAO {

    /** Lista estática utilizada para armazenar objetos Aluno recuperados do banco. */
    public static ArrayList<Aluno> MinhaLista = new ArrayList<>();

    /** Construtor padrão. */
    public AlunoDAO() {
    }

    /**
     * Retorna o maior ID encontrado na tabela de alunos.
     *
     * @return o maior valor de ID existente; retorna 0 caso não haja registros ou erro.
     * @throws SQLException caso ocorra erro na execução da consulta SQL.
     */
    public int maiorID() throws SQLException {
        int maiorID = 0;
        Connection conn = this.getConexao();
        if (conn == null) {
            return maiorID;
        }

        try (Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_alunos")) {

            if (res.next()) {
                maiorID = res.getInt("id");
            }
        } catch (SQLException ex) {
        }
        return maiorID;
    }

    /**
     * Estabelece e retorna uma conexão com o banco de dados MySQL.
     *
     * @return conexão ativa se for bem-sucedida; caso contrário, retorna {@code null}.
     */
    public Connection getConexao() {

        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String url = "jdbc:mysql://localhost:3306/db_escola?useTimezone=true&serverTimezone=UTC";
            String user = TelaLogin.userDB;
            String password = TelaLogin.passwordDB;

            connection = DriverManager.getConnection(url, user, password);

            if (connection.isValid(1)) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }

            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("O driver não foi encontrado. " + e.getMessage());
            return null;

        } catch (SQLException e) {
            System.out.println("Não foi possível conectar...");
            return null;
        }
    }

    /**
     * Retorna uma lista contendo todos os registros de alunos presentes no banco de dados.
     *
     * @return lista de objetos {@link Aluno}.
     */
    public ArrayList getMinhaLista() {
        MinhaLista.clear();

        Connection conn = this.getConexao();
        if (conn == null) {
            return MinhaLista;
        }

        try (Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT * FROM tb_alunos")) {

            while (res.next()) {
                String curso = res.getString("curso");
                int fase = res.getInt("fase");
                int id = res.getInt("id");
                String nome = res.getString("nome");
                int idade = res.getInt("idade");

                Aluno objeto = new Aluno(curso, fase, id, nome, idade);
                MinhaLista.add(objeto);
            }
        } catch (SQLException ex) {
        }

        return MinhaLista;
    }

    /**
     * Insere um novo registro de aluno no banco de dados.
     *
     * @param objeto objeto {@link Aluno} contendo os dados a serem salvos.
     * @return {@code true} se a inserção foi realizada com sucesso.
     */
    public boolean InsertAlunoBD(Aluno objeto) {
        String sql = "INSERT INTO tb_alunos(id,nome,idade,curso,fase) VALUES(?,?,?,?,?)";

        Connection conn = this.getConexao();
        if (conn == null) {
            throw new RuntimeException("Não foi possível conectar ao banco de dados");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setInt(3, objeto.getIdade());
            stmt.setString(4, objeto.getCurso());
            stmt.setInt(5, objeto.getFase());

            stmt.execute();
            return true;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    /**
     * Remove um aluno do banco de dados com base no ID fornecido.
     *
     * @param id identificador do aluno a ser removido.
     * @return {@code true} se o aluno foi removido com sucesso.
     */
    public boolean DeleteAlunoBD(int id) {
        Connection conn = this.getConexao();
        if (conn == null) {
            return false;
        }

        String sql = "DELETE FROM tb_alunos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException erro) {
            return false;
        }
    }

    /**
     * Atualiza os dados de um aluno existente no banco de dados.
     *
     * @param objeto objeto {@link Aluno} contendo informações atualizadas.
     * @return {@code true} se a atualização foi realizada com sucesso.
     */
    public boolean UpdateAlunoBD(Aluno objeto) {
        String sql = "UPDATE tb_alunos set nome = ? ,idade = ? ,curso = ? ,fase = ? WHERE id = ?";

        Connection conn = this.getConexao();
        if (conn == null) {
            throw new RuntimeException("Não foi possível conectar ao banco de dados");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getIdade());
            stmt.setString(3, objeto.getCurso());
            stmt.setInt(4, objeto.getFase());
            stmt.setInt(5, objeto.getId());

            stmt.execute();
            return true;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    /**
     * Carrega os dados de um aluno específico a partir do seu ID.
     *
     * @param id identificador do aluno a ser buscado.
     * @return um objeto {@link Aluno} preenchido com os dados encontrados;
     *         caso não exista, devolve um objeto contendo apenas o ID.
     */
    public Aluno carregaAluno(int id) {
        Aluno objeto = new Aluno();
        objeto.setId(id);

        Connection conn = this.getConexao();
        if (conn == null) {
            return objeto;
        }

        String sql = "SELECT * FROM tb_alunos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    objeto.setNome(res.getString("nome"));
                    objeto.setIdade(res.getInt("idade"));
                    objeto.setCurso(res.getString("curso"));
                    objeto.setFase(res.getInt("fase"));
                }
            }
        } catch (SQLException erro) {
        }
        return objeto;
    }
}
