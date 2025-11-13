package repository;

import db.ConnectionFactory;
import repository.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractRepository {
    
    protected Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }
    
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
    
    private String buildMaxIdQuery(String tableName) {
        if ("tb_alunos".equals(tableName)) {
            return "SELECT MAX(id) as max_id FROM tb_alunos";
        } else if ("tb_professores".equals(tableName)) {
            return "SELECT MAX(id) as max_id FROM tb_professores";
        }
        throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
    }
    
    private boolean isValidTableName(String tableName) {
        return "tb_alunos".equals(tableName) || "tb_professores".equals(tableName);
    }
    
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
    
    private String buildDeleteQuery(String tableName) {
        if ("tb_alunos".equals(tableName)) {
            return "DELETE FROM tb_alunos WHERE id = ?";
        } else if ("tb_professores".equals(tableName)) {
            return "DELETE FROM tb_professores WHERE id = ?";
        }
        throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
    }
}

