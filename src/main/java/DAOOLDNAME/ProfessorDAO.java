package dao;

import model.Professor;
import view.TelaLogin;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável pelo acesso e manipulação dos dados da entidade
 * {@link Professor} no banco de dados MySQL. Implementa operações CRUD,
 * gerencia conexões e fornece métodos auxiliares para consultas específicas.
 *
 * <p>Esta classe utiliza conexão direta com MySQL via JDBC, utilizando
 * as credenciais fornecidas pela tela de login do sistema.</p>
 */
public class ProfessorDAO {

    /** Lista estática utilizada para armazenar objetos Professor recuperados do banco. */
    public static ArrayList<Professor> MinhaLista2 = new ArrayList<Professor>();

    /** Construtor padrão. */
    public ProfessorDAO() {
    }

    /**
     * Retorna o maior ID existente na tabela de professores.
     *
     * @return maior valor de ID encontrado; retorna 0 se nenhum registro existe.
     * @throws SQLException caso ocorra erro na consulta SQL.
     */
    public int maiorID() throws SQLException {
        int maiorID = 0;
        Connection conn = this.getConexao();
        if (conn == null) {
            return maiorID;
        }

        try (Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_professores")) {

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
     * <p>Utiliza as credenciais informadas em {@link TelaLogin#userDB}
     * e {@link TelaLogin#passwordDB}.</p>
     *
     * @return uma conexão válida, ou {@code null} se ocorrer erro.
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
     * Retorna uma lista contendo todos os professores cadastrados no banco.
     * 
     * @return lista de objetos {@link Professor}.
     */
    public ArrayList getMinhaLista() {
        MinhaLista2.clear();

        Connection conn = this.getConexao();
        if (conn == null) {
            return MinhaLista2;
        }

        try (Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT * FROM tb_professores")) {

            while (res.next()) {
                String campus = res.getString("campus");
                String cpf = res.getString("cpf");
                String contato = res.getString("contato");
                String titulo = res.getString("titulo");
                double salario = res.getDouble("salario");
                int id = res.getInt("id");
                String nome = res.getString("nome");
                int idade = res.getInt("idade");

                Professor objeto = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);
                MinhaLista2.add(objeto);
            }
        } catch (SQLException ex) {
        }

        return MinhaLista2;
    }

    /**
     * Insere um novo professor no banco de dados.
     *
     * @param objeto objeto {@link Professor} contendo os dados a serem salvos.
     * @return {@code true} se a inserção foi bem-sucedida.
     */
    public boolean InsertProfessorBD(Professor objeto) {
        String sql = "INSERT INTO tb_professores(id,nome,idade,campus,cpf,contato,titulo,salario) VALUES(?,?,?,?,?,?,?,?)";

        Connection conn = this.getConexao();
        if (conn == null) {
            throw new RuntimeException("Não foi possível conectar ao banco de dados");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setInt(3, objeto.getIdade());
            stmt.setString(4, objeto.getCampus());
            stmt.setString(5, objeto.getCpf());
            stmt.setString(6, objeto.getContato());
            stmt.setString(7, objeto.getTitulo());
            stmt.setDouble(8, objeto.getSalario());

            stmt.execute();
            return true;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    /**
     * Remove um professor do banco de dados com base no ID informado.
     *
     * @param id identificador do professor a ser removido.
     * @return {@code true} se a remoção foi bem-sucedida.
     */
    public boolean DeleteProfessorBD(int id) {
        Connection conn = this.getConexao();
        if (conn == null) {
            return false;
        }

        String sql = "DELETE FROM tb_professores WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException erro) {
            return false;
        }
    }

    /**
     * Atualiza os dados de um professor existente no banco de dados.
     *
     * @param objeto objeto {@link Professor} contendo os dados atualizados.
     * @return {@code true} se a atualização foi bem-sucedida.
     */
    public boolean UpdateProfessorBD(Professor objeto) {
        String sql = "UPDATE tb_professores set nome = ? ,idade = ? ,campus = ? ,cpf = ? ,contato = ? ,titulo = ? ,salario = ? WHERE id = ?";

        Connection conn = this.getConexao();
        if (conn == null) {
            throw new RuntimeException("Não foi possível conectar ao banco de dados");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getIdade());
            stmt.setString(3, objeto.getCampus());
            stmt.setString(4, objeto.getCpf());
            stmt.setString(5, objeto.getContato());
            stmt.setString(6, objeto.getTitulo());
            stmt.setDouble(7, objeto.getSalario());
            stmt.setInt(8, objeto.getId());

            stmt.execute();
            return true;
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    /**
     * Carrega os dados de um professor específico a partir do seu ID.
     *
     * @param id identificador do professor a ser buscado.
     * @return um objeto {@link Professor} preenchido com os dados encontrados;
     *         caso o ID não exista, retorna um objeto com apenas o ID definido.
     */
    public Professor carregaProfessor(int id) {
        Professor objeto = new Professor();
        objeto.setId(id);

        Connection conn = this.getConexao();
        if (conn == null) {
            return objeto;
        }

        String sql = "SELECT * FROM tb_professores WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    objeto.setNome(res.getString("nome"));
                    objeto.setIdade(res.getInt("idade"));
                    objeto.setCampus(res.getString("campus"));
                    objeto.setCpf(res.getString("cpf"));
                    objeto.setContato(res.getString("contato"));
                    objeto.setTitulo(res.getString("titulo"));
                    objeto.setSalario(res.getDouble("salario"));
                }
            }
        } catch (SQLException erro) {
        }
        return objeto;
    }
}
