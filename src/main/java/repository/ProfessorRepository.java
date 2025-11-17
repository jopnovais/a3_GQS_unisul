package repository;

import model.Professor;
import java.util.List;

/**
 * Interface que define as operações de persistência da entidade {@link Professor}. <br>
 * Fornece métodos para salvar, atualizar, remover e consultar professores no repositório.
 */
public interface ProfessorRepository {

    /**
     * Salva um novo professor na base de dados.
     *
     * @param professor objeto {@link Professor} a ser persistido
     * @return true se o registro for inserido com sucesso
     */
    boolean save(Professor professor);

    /**
     * Atualiza os dados de um professor já existente.
     *
     * @param professor instância com os dados atualizados
     * @return true se o registro foi alterado com sucesso
     */
    boolean update(Professor professor);

    /**
     * Exclui um professor do repositório com base no seu ID.
     *
     * @param id identificador do professor
     * @return true se o professor foi removido
     */
    boolean delete(int id);

    /**
     * Busca um professor pelo ID.
     *
     * @param id identificador do professor
     * @return o professor correspondente ou null se não existir
     */
    Professor findById(int id);

    /**
     * Busca um professor usando o CPF como chave.
     *
     * @param cpf CPF do professor
     * @return o professor correspondente ou null se não encontrado
     */
    Professor findByCpf(String cpf);

    /**
     * Retorna todos os professores cadastrados.
     *
     * @return lista completa de professores
     */
    List<Professor> findAll();

    /**
     * Obtém o maior ID registrado na tabela de professores.
     *
     * @return o maior ID ou 0 caso a tabela esteja vazia
     */
    int getMaxId();
}
