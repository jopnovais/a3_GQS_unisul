package DAO;

import model.Professor;
import view.TelaLogin;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfessorDAO {

    public static ArrayList<Professor> MinhaLista2 = new ArrayList<Professor>();

    public ProfessorDAO() {
    }

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

    public Connection getConexao() {

        Connection connection = null;

        try {

            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String url = "jdbc:mysql://localhost:3306/db_escola?useTimezone=true&serverTimezone=UTC";
            String user = TelaLogin.userDB;
            String password = TelaLogin.passwordDB;

            connection = DriverManager.getConnection(url, user, password);
            
            // Verifica se a conexão é válida
            if (connection.isValid(1)) {
                System.out.println("Status: Conectado!");
            }

            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado
            System.out.println("O driver não foi encontrado. " + e.getMessage());
            return null;

        } catch (SQLException e) {
            System.out.println("Não foi possível conectar...");
            return null;
        }
    }

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

    public boolean DeleteProfessorBD(int id) {
        Connection conn = this.getConexao();
        if (conn == null) {
            return false;
        }

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM tb_professores WHERE id = " + id);
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

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

    public Professor carregaProfessor(int id) {
        Professor objeto = new Professor();
        objeto.setId(id);

        Connection conn = this.getConexao();
        if (conn == null) {
            return objeto;
        }

        try (Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT * FROM tb_professores WHERE id = " + id)) {
            
            if (res.next()) {
                objeto.setNome(res.getString("nome"));
                objeto.setIdade(res.getInt("idade"));
                objeto.setCampus(res.getString("campus"));
                objeto.setCpf(res.getString("cpf"));
                objeto.setContato(res.getString("contato"));
                objeto.setTitulo(res.getString("titulo"));
                objeto.setSalario(res.getDouble("salario"));
            }
        } catch (SQLException erro) {
        }
        return objeto;
    }
}
