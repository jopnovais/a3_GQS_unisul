package service;

import model.Aluno;
import java.util.List;

/**
 * Interface que define os serviços disponíveis para operações com alunos.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public interface AlunoService {
    
    /**
     * Salva um novo aluno no sistema.
     * 
     * @param aluno O aluno a ser salvo
     * @throws service.exception.ValidacaoException Se os dados do aluno forem inválidos
     */
    void salvar(Aluno aluno) throws service.exception.ValidacaoException;
    
    /**
     * Atualiza os dados de um aluno existente no sistema.
     * 
     * @param aluno O aluno com os dados atualizados
     * @throws service.exception.ValidacaoException Se os dados do aluno forem inválidos ou o ID não for informado
     */
    void atualizar(Aluno aluno) throws service.exception.ValidacaoException;
    
    /**
     * Exclui um aluno do sistema pelo ID.
     * 
     * @param id O identificador único do aluno a ser excluído
     */
    void excluir(int id);
    
    /**
     * Busca um aluno pelo ID.
     * 
     * @param id O identificador único do aluno
     * @return O aluno encontrado, ou null se não encontrado
     */
    Aluno buscarPorId(int id);
    
    /**
     * Lista todos os alunos cadastrados no sistema.
     * 
     * @return Lista contendo todos os alunos cadastrados
     */
    List<Aluno> listarTodos();
    
    /**
     * Calcula a idade com base na data de nascimento fornecida.
     * 
     * @param dataNascimento A data de nascimento
     * @return A idade calculada em anos
     * @throws service.exception.ValidacaoException Se a data de nascimento for nula
     */
    int calcularIdade(java.util.Date dataNascimento);
}

