package repository;

import model.Professor;
import repository.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface ProfessorRepository.
 * Fornece operações de persistência para professores usando SQLite.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public class ProfessorRepositoryImpl extends AbstractRepository implements ProfessorRepository {
    
    /**
     * Salva um novo professor no banco de dados.
     * 
     * @param professor O professor a ser salvo
     * @return true se o professor foi salvo com sucesso
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
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
    
    /**
     * Atualiza os dados de um professor existente no banco de dados.
     * 
     * @param professor O professor com os dados atualizados
     * @return true se o professor foi atualizado com sucesso, false caso contrário
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
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
    
    /**
     * Remove um professor do banco de dados pelo ID.
     * 
     * @param id O identificador único do professor a ser removido
     * @return true se o professor foi removido com sucesso, false caso contrário
     */
    @Override
    public boolean delete(int id) {
        return executeDelete("tb_professores", id);
    }
    
    /**
     * Busca um professor pelo ID.
     * 
     * @param id O identificador único do professor
     * @return O professor encontrado, ou null se não encontrado
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
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
    
    /**
     * Busca um professor pelo CPF.
     * 
     * @param cpf O CPF do professor
     * @return O professor encontrado, ou null se não encontrado
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
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
    
    /**
     * Cria um objeto Professor a partir de um ResultSet.
     * 
     * @param res O ResultSet contendo os dados do professor
     * @return Um objeto Professor populado com os dados do ResultSet
     * @throws SQLException Se ocorrer um erro ao acessar os dados do ResultSet
     */
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
    
    /**
     * Retorna todos os professores cadastrados no banco de dados.
     * 
     * @return Lista contendo todos os professores cadastrados
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
    @Override
    public List<Professor> findAll() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM tb_professores";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet res = stmt.executeQuery()) {
            
            while (res.next()) {
                professores.add(criarProfessorDoResultSet(res));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar todos os professores: " + e.getMessage(), e);
        }
        
        return professores;
    }
    
    /**
     * Retorna o maior ID cadastrado no banco de dados.
     * 
     * @return O maior ID encontrado, ou 0 se não houver registros
     */
    @Override
    public int getMaxId() {
        return executeMaxIdQuery("tb_professores");
    }
}
