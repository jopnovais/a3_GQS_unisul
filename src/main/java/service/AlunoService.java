package service;

import model.Aluno;
import java.util.List;

/**
 * Interface responsável por definir as operações de regra de negócio
 * relacionadas ao gerenciamento de alunos.
 * <p>
 * Inclui métodos para cadastro, atualização, exclusão, consulta e 
 * funcionalidades auxiliares como cálculo de idade.
 */
public interface AlunoService {
    
    /**
     * Salva um novo aluno no sistema após realizar validações de negócio.
     *
     * @param aluno objeto do tipo {@link Aluno} contendo os dados a serem salvos.
     * @throws service.exception.ValidacaoException caso os dados do aluno
     *         não atendam às regras de validação.
     */
    void salvar(Aluno aluno) throws service.exception.ValidacaoException;
    
    /**
     * Atualiza os dados de um aluno existente após validação.
     *
     * @param aluno objeto {@link Aluno} com as informações atualizadas.
     * @throws service.exception.ValidacaoException caso os dados fornecidos
     *         sejam inválidos para atualização.
     */
    void atualizar(Aluno aluno) throws service.exception.ValidacaoException;
    
    /**
     * Remove um aluno do sistema com base no ID informado.
     *
     * @param id identificador único do aluno a ser removido.
     */
    void excluir(int id);
    
    /**
     * Busca um aluno pelo seu identificador.
     *
     * @param id identificador único do aluno.
     * @return o {@link Aluno} correspondente ou {@code null} caso não seja encontrado.
     */
    Aluno buscarPorId(int id);
    
    /**
     * Retorna uma lista contendo todos os alunos cadastrados.
     *
     * @return lista de objetos {@link Aluno}.
     */
    List<Aluno> listarTodos();
    
    /**
     * Calcula a idade com base na data de nascimento informada.
     *
     * @param dataNascimento data de nascimento do aluno.
     * @return idade atual calculada.
     */
    int calcularIdade(java.util.Date dataNascimento);
}
