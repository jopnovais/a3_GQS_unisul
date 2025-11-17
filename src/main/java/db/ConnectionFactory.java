package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados SQLite
 * utilizado pelo sistema. Também garante que as tabelas necessárias
 * sejam criadas automaticamente ao iniciar a aplicação.
 *
 * <p>A classe utiliza o padrão de fábrica (Factory Pattern) para
 * fornecer conexões prontas para uso.</p>
 */
public class ConnectionFactory {
    
    /** Caminho do banco de dados SQLite utilizado pelo sistema. */
    private static final String DB_URL = "jdbc:sqlite:db_escola.db";
    
    /**
     * Obtém uma conexão ativa com o banco de dados SQLite.
     * 
     * <p>Ao estabelecer a conexão, o método também chama
     * {@link #initializeTables(Connection)} para garantir que as tabelas
     * essenciais existam.</p>
     *
     * @return uma conexão válida com o banco de dados.
     * @throws SQLException se ocorrer erro ao abrir a conexão.
     */
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
    
    /**
     * Cria as tabelas necessárias no banco de dados caso elas ainda não existam.
     * 
     * <p>Atualmente, este método garante a existência das tabelas:</p>
     * <ul>
     *   <li><b>tb_alunos</b> – Armazena dados dos alunos.</li>
     *   <li><b>tb_professores</b> – Armazena dados dos professores.</li>
     * </ul>
     *
     * @param connection conexão ativa usada para executar comandos SQL.
     */
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
