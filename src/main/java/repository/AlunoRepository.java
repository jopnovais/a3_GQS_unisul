package repository;

import model.Aluno;
import java.util.List;

/**
 * Interface que define as operações de persistência para alunos.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public interface AlunoRepository {
    
    /**
     * Salva um novo aluno no banco de dados.
     * 
     * @param aluno O aluno a ser salvo
     * @return true se o aluno foi salvo com sucesso, false caso contrário
     */
    boolean save(Aluno aluno);
    
    /**
     * Atualiza os dados de um aluno existente no banco de dados.
     * 
     * @param aluno O aluno com os dados atualizados
     * @return true se o aluno foi atualizado com sucesso, false caso contrário
     */
    boolean update(Aluno aluno);
    
    /**
     * Remove um aluno do banco de dados pelo ID.
     * 
     * @param id O identificador único do aluno a ser removido
     * @return true se o aluno foi removido com sucesso, false caso contrário
     */
    boolean delete(int id);
    
    /**
     * Busca um aluno pelo ID.
     * 
     * @param id O identificador único do aluno
     * @return O aluno encontrado, ou null se não encontrado
     */
    Aluno findById(int id);
    
    /**
     * Retorna todos os alunos cadastrados no banco de dados.
     * 
     * @return Lista contendo todos os alunos cadastrados
     */
    List<Aluno> findAll();
    
    /**
     * Retorna o maior ID cadastrado no banco de dados.
     * 
     * @return O maior ID encontrado, ou 0 se não houver registros
     */
    int getMaxId();
}
