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
        String sql = "SELECT MAX(id) as max_id FROM " + tableName;
        
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
    
    protected boolean executeDelete(String tableName, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar registro: " + e.getMessage(), e);
        }
    }
}

