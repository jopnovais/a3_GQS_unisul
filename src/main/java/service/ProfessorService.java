package service;

import model.Professor;
import java.util.List;

/**
 * Define os serviços relacionados ao gerenciamento de professores,
 * incluindo validações, operações de cadastro, atualização, exclusão e consultas.
 */
public interface ProfessorService {
    
    /**
     * Salva um novo professor após validações de regras de negócio.
     *
     * @param professor objeto {@link Professor} a ser salvo.
     * @throws service.exception.ValidacaoException caso alguma validação seja violada.
     */
    void salvar(Professor professor) throws service.exception.ValidacaoException;
    
    /**
     * Atualiza os dados de um professor existente.
     *
     * @param professor professor contendo dados atualizados.
     * @throws service.exception.ValidacaoException caso o professor seja inválido ou não possua ID.
     */
    void atualizar(Professor professor) throws service.exception.ValidacaoException;
    
    /**
     * Exclui um professor pelo seu identificador.
     *
     * @param id identificador único do professor.
     */
    void excluir(int id);
    
    /**
     * Busca um professor pelo seu ID.
     *
     * @param id identificador do professor.
     * @return professor correspondente ou {@code null} caso não seja encontrado.
     */
    Professor buscarPorId(int id);
    
    /**
     * Busca um professor utilizando seu CPF.
     *
     * @param cpf CPF do professor.
     * @return instância de {@link Professor} encontrada ou {@code null} caso não exista.
     */
    Professor buscarPorCpf(String cpf);
    
    /**
     * Retorna uma lista contendo todos os professores cadastrados.
     *
     * @return lista de professores.
     */
    List<Professor> listarTodos();
    
    /**
     * Calcula a idade a partir da data de nascimento fornecida.
     *
     * @param dataNascimento data de nascimento do professor.
     * @return idade calculada.
     * @throws service.exception.ValidacaoException caso a data seja nula.
     */
    int calcularIdade(java.util.Date dataNascimento);
    
    /**
     * Valida uma string formatada utilizada em campos como CPF, telefone, entre outros.
     * Pode aplicar regras específicas de formatação, caracteres permitidos ou estrutura.
     *
     * @param input texto a ser validado.
     * @return texto validado e possivelmente normalizado.
     * @throws service.exception.ValidacaoException caso a string seja inválida.
     */
    String validarFormatado(String input);
}
