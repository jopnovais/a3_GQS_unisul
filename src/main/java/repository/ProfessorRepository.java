package repository;

import model.Professor;
import java.util.List;

/**
 * Interface que define as operações de persistência para professores.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public interface ProfessorRepository {
    
    /**
     * Salva um novo professor no banco de dados.
     * 
     * @param professor O professor a ser salvo
     * @return true se o professor foi salvo com sucesso, false caso contrário
     */
    boolean save(Professor professor);
    
    /**
     * Atualiza os dados de um professor existente no banco de dados.
     * 
     * @param professor O professor com os dados atualizados
     * @return true se o professor foi atualizado com sucesso, false caso contrário
     */
    boolean update(Professor professor);
    
    /**
     * Remove um professor do banco de dados pelo ID.
     * 
     * @param id O identificador único do professor a ser removido
     * @return true se o professor foi removido com sucesso, false caso contrário
     */
    boolean delete(int id);
    
    /**
     * Busca um professor pelo ID.
     * 
     * @param id O identificador único do professor
     * @return O professor encontrado, ou null se não encontrado
     */
    Professor findById(int id);
    
    /**
     * Busca um professor pelo CPF.
     * 
     * @param cpf O CPF do professor
     * @return O professor encontrado, ou null se não encontrado
     */
    Professor findByCpf(String cpf);
    
    /**
     * Retorna todos os professores cadastrados no banco de dados.
     * 
     * @return Lista contendo todos os professores cadastrados
     */
    List<Professor> findAll();
    
    /**
     * Retorna o maior ID cadastrado no banco de dados.
     * 
     * @return O maior ID encontrado, ou 0 se não houver registros
     */
    int getMaxId();
}
