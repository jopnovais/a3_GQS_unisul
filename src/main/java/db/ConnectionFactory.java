package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    
    private static final String DB_URL = "jdbc:sqlite:projeto_faculdade.db";
    
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            initializeTables(connection);
            return connection;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados SQLite: " + e.getMessage());
            throw e;
        }
    }
    
    private static void initializeTables(Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            String createAlunosTable = 
                "CREATE TABLE IF NOT EXISTS tb_alunos (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR(250), " +
                "idade INTEGER, " +
                "curso VARCHAR(45), " +
                "fase INTEGER" +
                ")";
            
            String createProfessoresTable = 
                "CREATE TABLE IF NOT EXISTS tb_professores (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR(250), " +
                "idade INTEGER, " +
                "campus VARCHAR(45), " +
                "cpf VARCHAR(14), " +
                "contato VARCHAR(16), " +
                "titulo VARCHAR(45), " +
                "salario REAL" +
                ")";
            
            stmt.execute(createAlunosTable);
            stmt.execute(createProfessoresTable);
        } catch (SQLException e) {
            System.err.println("Erro ao inicializar tabelas: " + e.getMessage());
        }
    }
}

