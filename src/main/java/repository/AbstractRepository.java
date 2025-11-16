package repository;

import db.ConnectionFactory;
import repository.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe abstrata que fornece funcionalidades comuns para repositórios.
 * Contém métodos auxiliares para operações de banco de dados compartilhadas.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public abstract class AbstractRepository {
    
    /**
     * Obtém uma conexão com o banco de dados.
     * 
     * @return Uma conexão com o banco de dados
     * @throws SQLException Se ocorrer um erro ao obter a conexão
     */
    protected Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }
    
    /**
     * Executa uma consulta para obter o maior ID de uma tabela específica.
     * 
     * @param tableName O nome da tabela (deve ser "tb_alunos" ou "tb_professores")
     * @return O maior ID encontrado, ou 0 se não houver registros
     * @throws IllegalArgumentException Se o nome da tabela for inválido
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
    protected int executeMaxIdQuery(String tableName) {
        if (!isValidTableName(tableName)) {
            throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
        }
        
        String sql = buildMaxIdQuery(tableName);
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             java.sql.ResultSet res = stmt.executeQuery(sql)) {
            
            if (res.next()) {
                return res.getInt("max_id");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar maior ID: " + e.getMessage(), e);
        }
        
        return 0;
    }
    
    /**
     * Constrói a query SQL para buscar o maior ID de uma tabela.
     * 
     * @param tableName O nome da tabela
     * @return A query SQL formatada
     * @throws IllegalArgumentException Se o nome da tabela for inválido
     */
    private String buildMaxIdQuery(String tableName) {
        if ("tb_alunos".equals(tableName)) {
            return "SELECT MAX(id) as max_id FROM tb_alunos";
        } else if ("tb_professores".equals(tableName)) {
            return "SELECT MAX(id) as max_id FROM tb_professores";
        }
        throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
    }
    
    /**
     * Valida se o nome da tabela é permitido.
     * 
     * @param tableName O nome da tabela a ser validado
     * @return true se o nome da tabela for válido, false caso contrário
     */
    private boolean isValidTableName(String tableName) {
        return "tb_alunos".equals(tableName) || "tb_professores".equals(tableName);
    }
    
    /**
     * Executa uma operação de exclusão em uma tabela específica.
     * 
     * @param tableName O nome da tabela (deve ser "tb_alunos" ou "tb_professores")
     * @param id O identificador único do registro a ser excluído
     * @return true se o registro foi excluído com sucesso, false caso contrário
     * @throws IllegalArgumentException Se o nome da tabela for inválido
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
    protected boolean executeDelete(String tableName, int id) {
        if (!isValidTableName(tableName)) {
            throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
        }
        
        String sql = buildDeleteQuery(tableName);
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar registro: " + e.getMessage(), e);
        }
    }
    
    /**
     * Constrói a query SQL para deletar um registro de uma tabela.
     * 
     * @param tableName O nome da tabela
     * @return A query SQL formatada
     * @throws IllegalArgumentException Se o nome da tabela for inválido
     */
    private String buildDeleteQuery(String tableName) {
        if ("tb_alunos".equals(tableName)) {
            return "DELETE FROM tb_alunos WHERE id = ?";
        } else if ("tb_professores".equals(tableName)) {
            return "DELETE FROM tb_professores WHERE id = ?";
        }
        throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
    }
}
