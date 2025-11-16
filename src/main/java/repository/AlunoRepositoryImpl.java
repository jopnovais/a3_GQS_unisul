package repository;

import model.Aluno;
import repository.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface AlunoRepository.
 * Fornece operações de persistência para alunos usando SQLite.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public class AlunoRepositoryImpl extends AbstractRepository implements AlunoRepository {
    
    /**
     * Salva um novo aluno no banco de dados.
     * 
     * @param aluno O aluno a ser salvo
     * @return true se o aluno foi salvo com sucesso
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
    @Override
    public boolean save(Aluno aluno) {
        String sql = "INSERT INTO tb_alunos(nome, idade, curso, fase) VALUES(?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getFase());
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao salvar aluno: " + e.getMessage(), e);
        }
    }
    
    /**
     * Atualiza os dados de um aluno existente no banco de dados.
     * 
     * @param aluno O aluno com os dados atualizados
     * @return true se o aluno foi atualizado com sucesso, false caso contrário
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
    @Override
    public boolean update(Aluno aluno) {
        String sql = "UPDATE tb_alunos SET nome = ?, idade = ?, curso = ?, fase = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getFase());
            stmt.setInt(5, aluno.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao atualizar aluno: " + e.getMessage(), e);
        }
    }
    
    /**
     * Remove um aluno do banco de dados pelo ID.
     * 
     * @param id O identificador único do aluno a ser removido
     * @return true se o aluno foi removido com sucesso, false caso contrário
     */
    @Override
    public boolean delete(int id) {
        return executeDelete("tb_alunos", id);
    }
    
    /**
     * Busca um aluno pelo ID.
     * 
     * @param id O identificador único do aluno
     * @return O aluno encontrado, ou null se não encontrado
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
    @Override
    public Aluno findById(int id) {
        String sql = "SELECT * FROM tb_alunos WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return criarAlunoDoResultSet(res);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar aluno por ID: " + e.getMessage(), e);
        }
        
        return null;
    }
    
    /**
     * Cria um objeto Aluno a partir de um ResultSet.
     * 
     * @param res O ResultSet contendo os dados do aluno
     * @return Um objeto Aluno populado com os dados do ResultSet
     * @throws SQLException Se ocorrer um erro ao acessar os dados do ResultSet
     */
    private Aluno criarAlunoDoResultSet(ResultSet res) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setId(res.getInt("id"));
        aluno.setNome(res.getString("nome"));
        aluno.setIdade(res.getInt("idade"));
        aluno.setCurso(res.getString("curso"));
        aluno.setFase(res.getInt("fase"));
        return aluno;
    }
    
    /**
     * Retorna todos os alunos cadastrados no banco de dados.
     * 
     * @return Lista contendo todos os alunos cadastrados
     * @throws DataAccessException Se ocorrer um erro ao acessar o banco de dados
     */
    @Override
    public List<Aluno> findAll() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM tb_alunos";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet res = stmt.executeQuery()) {
            
            while (res.next()) {
                alunos.add(criarAlunoDoResultSet(res));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar todos os alunos: " + e.getMessage(), e);
        }
        
        return alunos;
    }
    
    /**
     * Retorna o maior ID cadastrado no banco de dados.
     * 
     * @return O maior ID encontrado, ou 0 se não houver registros
     */
    @Override
    public int getMaxId() {
        return executeMaxIdQuery("tb_alunos");
    }
}
