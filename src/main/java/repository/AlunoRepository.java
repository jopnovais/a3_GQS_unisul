package repository;

import model.Aluno;
import java.util.List;

/**
 * Interface que define as operações de acesso a dados para a entidade {@link Aluno}. <br><br>
 *
 * Ela estabelece o contrato que os repositórios concretos devem implementar,
 * garantindo um padrão consistente para operações de CRUD e consultas
 * relacionadas ao modelo de alunos.
 */
public interface AlunoRepository {

    /**
     * Salva um novo aluno no banco de dados.
     *
     * @param aluno objeto {@link Aluno} a ser persistido
     * @return true se a operação for bem-sucedida, false caso contrário
     */
    boolean save(Aluno aluno);

    /**
     * Atualiza os dados de um aluno existente no banco de dados.
     *
     * @param aluno instância já existente com dados atualizados
     * @return true se o registro foi alterado, false se não houve mudanças
     */
    boolean update(Aluno aluno);

    /**
     * Remove um aluno do banco pelo ID.
     *
     * @param id identificador do aluno
     * @return true se um registro foi removido, false se nenhum foi encontrado
     */
    boolean delete(int id);

    /**
     * Busca um aluno específico pelo ID.
     *
     * @param id identificador do aluno
     * @return o objeto {@link Aluno} correspondente ou null se não encontrado
     */
    Aluno findById(int id);

    /**
     * Retorna todos os alunos cadastrados.
     *
     * @return lista contendo todas as instâncias de {@link Aluno}
     */
    List<Aluno> findAll();

    /**
     * Obtém o maior ID existente na tabela de alunos.
     *
     * @return valor numérico do maior ID, ou 0 caso a tabela esteja vazia
     */
    int getMaxId();
}
