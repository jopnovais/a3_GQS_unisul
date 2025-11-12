package repository;

import model.Professor;
import repository.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepositoryImpl extends AbstractRepository implements ProfessorRepository {
    
    @Override
    public boolean save(Professor professor) {
        String sql = "INSERT INTO tb_professores(nome, idade, campus, cpf, contato, titulo, salario) VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
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
            throw new DataAccessException("Erro ao salvar professor: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean update(Professor professor) {
        String sql = "UPDATE tb_professores SET nome = ?, idade = ?, campus = ?, cpf = ?, contato = ?, titulo = ?, salario = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
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
            throw new DataAccessException("Erro ao atualizar professor: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean delete(int id) {
        return executeDelete("tb_professores", id);
    }
    
    @Override
    public Professor findById(int id) {
        String sql = "SELECT * FROM tb_professores WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return criarProfessorDoResultSet(res);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar professor por ID: " + e.getMessage(), e);
        }
        
        return null;
    }
    
    @Override
    public Professor findByCpf(String cpf) {
        String sql = "SELECT * FROM tb_professores WHERE cpf = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return criarProfessorDoResultSet(res);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar professor por CPF: " + e.getMessage(), e);
        }
        
        return null;
    }
    
    private Professor criarProfessorDoResultSet(ResultSet res) throws SQLException {
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
    
    @Override
    public List<Professor> findAll() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM tb_professores";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {
            
            while (res.next()) {
                professores.add(criarProfessorDoResultSet(res));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar todos os professores: " + e.getMessage(), e);
        }
        
        return professores;
    }
    
    @Override
    public int getMaxId() {
        return executeMaxIdQuery("tb_professores");
    }
}

