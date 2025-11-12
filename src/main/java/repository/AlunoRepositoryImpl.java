package repository;

import db.ConnectionFactory;
import model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepositoryImpl implements AlunoRepository {
    
    @Override
    public boolean save(Aluno aluno) {
        String sql = "INSERT INTO tb_alunos(nome, idade, curso, fase) VALUES(?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getFase());
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao salvar aluno: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar aluno", e);
        }
    }
    
    @Override
    public boolean update(Aluno aluno) {
        String sql = "UPDATE tb_alunos SET nome = ?, idade = ?, curso = ?, fase = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getFase());
            stmt.setInt(5, aluno.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM tb_alunos WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar aluno: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public Aluno findById(int id) {
        String sql = "SELECT * FROM tb_alunos WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(res.getInt("id"));
                    aluno.setNome(res.getString("nome"));
                    aluno.setIdade(res.getInt("idade"));
                    aluno.setCurso(res.getString("curso"));
                    aluno.setFase(res.getInt("fase"));
                    return aluno;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
        }
        
        return null;
    }
    
    @Override
    public List<Aluno> findAll() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM tb_alunos";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {
            
            while (res.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(res.getInt("id"));
                aluno.setNome(res.getString("nome"));
                aluno.setIdade(res.getInt("idade"));
                aluno.setCurso(res.getString("curso"));
                aluno.setFase(res.getInt("fase"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar alunos: " + e.getMessage());
        }
        
        return alunos;
    }
    
    @Override
    public int getMaxId() {
        String sql = "SELECT MAX(id) as max_id FROM tb_alunos";
        
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

