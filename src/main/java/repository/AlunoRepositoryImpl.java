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
 * Implementação concreta de {@link AlunoRepository} responsável por realizar
 * operações de persistência no banco de dados SQLite usando a estrutura
 * fornecida por {@link AbstractRepository}. <br><br>
 *
 * Esta classe encapsula toda a lógica de acesso a dados da entidade {@link Aluno},
 * garantindo separação clara entre regras de negócio e persistência.
 */
public class AlunoRepositoryImpl extends AbstractRepository implements AlunoRepository {
    
    /**
     * Insere um novo aluno na tabela <code>tb_alunos</code>.
     *
     * @param aluno objeto {@link Aluno} contendo os dados a serem salvos
     * @return true se o registro for inserido com sucesso
     * @throws DataAccessException caso ocorra algum erro na operação SQL
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
     * Atualiza os dados de um aluno já cadastrado no banco.
     *
     * @param aluno instância existente com dados atualizados
     * @return true se algum registro foi modificado; false caso contrário
     * @throws DataAccessException caso ocorra algum erro ao executar o UPDATE
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
     * Remove um aluno da base pelo seu identificador único.
     *
     * @param id identificador do aluno
     * @return true se o registro foi removido
     */
    @Override
    public boolean delete(int id) {
        return executeDelete("tb_alunos", id);
    }
    
    /**
     * Busca um aluno pelo ID.
     *
     * @param id identificador do aluno
     * @return o aluno encontrado ou null caso não exista
     * @throws DataAccessException caso ocorra falha na consulta
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
     * Constrói um objeto {@link Aluno} a partir de um {@link ResultSet}.
     *
     * @param res resultado da consulta SQL
     * @return instância preenchida de {@link Aluno}
     * @throws SQLException caso o acesso aos campos falhe
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
     * Retorna todos os alunos cadastrados na base.
     *
     * @return lista contendo todos os alunos
     * @throws DataAccessException caso a consulta falhe
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
     * Obtém o maior ID da tabela <code>tb_alunos</code>.
     *
     * @return maior ID encontrado ou 0 caso a tabela esteja vazia
     */
    @Override
    public int getMaxId() {
        return executeMaxIdQuery("tb_alunos");
    }
}
