package repository;

import db.ConnectionFactory;
import model.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepositoryImpl implements ProfessorRepository {
    
    @Override
    public boolean save(Professor professor) {
        String sql = "INSERT INTO tb_professores(nome, idade, campus, cpf, contato, titulo, salario) VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setString(3, professor.getCampus());
            stmt.setString(4, professor.getCpf());
            stmt.setString(5, professor.getContato());
            stmt.setString(6, professor.getTitulo());
            stmt.setDouble(7, professor.getSalario());
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao salvar professor: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar professor", e);
        }
    }
    
    @Override
    public boolean update(Professor professor) {
        String sql = "UPDATE tb_professores SET nome = ?, idade = ?, campus = ?, cpf = ?, contato = ?, titulo = ?, salario = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setString(3, professor.getCampus());
            stmt.setString(4, professor.getCpf());
            stmt.setString(5, professor.getContato());
            stmt.setString(6, professor.getTitulo());
            stmt.setDouble(7, professor.getSalario());
            stmt.setInt(8, professor.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar professor: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar professor", e);
        }
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM tb_professores WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar professor: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Professor findById(int id) {
        String sql = "SELECT * FROM tb_professores WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    Professor professor = new Professor();
                    professor.setId(res.getInt("id"));
                    professor.setNome(res.getString("nome"));
                    professor.setIdade(res.getInt("idade"));
                    professor.setCampus(res.getString("campus"));
                    professor.setCpf(res.getString("cpf"));
                    professor.setContato(res.getString("contato"));
                    professor.setTitulo(res.getString("titulo"));
                    professor.setSalario(res.getDouble("salario"));
                    return professor;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar professor: " + e.getMessage());
        }
        
        return null;
    }
    
    @Override
    public List<Professor> findAll() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM tb_professores";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {
            
            while (res.next()) {
                Professor professor = new Professor();
                professor.setId(res.getInt("id"));
                professor.setNome(res.getString("nome"));
                professor.setIdade(res.getInt("idade"));
                professor.setCampus(res.getString("campus"));
                professor.setCpf(res.getString("cpf"));
                professor.setContato(res.getString("contato"));
                professor.setTitulo(res.getString("titulo"));
                professor.setSalario(res.getDouble("salario"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar professores: " + e.getMessage());
        }
        
        return professores;
    }
    
    @Override
    public int getMaxId() {
        String sql = "SELECT MAX(id) as max_id FROM tb_professores";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {
            
            if (res.next()) {
                return res.getInt("max_id");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar maior ID: " + e.getMessage());
        }
        
        return 0;
    }
}

