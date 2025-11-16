package dao;

import model.Aluno;
import view.TelaLogin;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlunoDAO {

    public static ArrayList<Aluno> MinhaLista = new ArrayList<Aluno>();

    public AlunoDAO() {
    }

    public int maiorID() throws SQLException {
        int maiorID = 0;
        Connection conn = this.getConexao();
        if (conn == null) {
            return maiorID;
        }

        try (Statement stmt = conn.createStatement(); ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_alunos")) {

            if (res.next()) {
                maiorID = res.getInt("id");
            }
        } catch (SQLException ex) {
        }
        return maiorID;
    }

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
                System.out.println("Status: N�O CONECTADO!");
            }

            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver nao foi encontrado. " + e.getMessage());
            return null;

        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }
    }

    public ArrayList getMinhaLista() {
        MinhaLista.clear();

        Connection conn = this.getConexao();
        if (conn == null) {
            return MinhaLista;
        }

        try (Statement stmt = conn.createStatement(); ResultSet res = stmt.executeQuery("SELECT * FROM tb_alunos")) {

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
