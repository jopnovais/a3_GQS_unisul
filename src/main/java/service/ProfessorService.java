package service;

import model.Professor;
import java.util.List;

/**
 * Interface que define os serviços disponíveis para operações com professores.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public interface ProfessorService {
    
    /**
     * Salva um novo professor no sistema.
     * 
     * @param professor O professor a ser salvo
     * @throws service.exception.ValidacaoException Se os dados do professor forem inválidos
     */
    void salvar(Professor professor) throws service.exception.ValidacaoException;
    
    /**
     * Atualiza os dados de um professor existente no sistema.
     * 
     * @param professor O professor com os dados atualizados
     * @throws service.exception.ValidacaoException Se os dados do professor forem inválidos ou o ID não for informado
     */
    void atualizar(Professor professor) throws service.exception.ValidacaoException;
    
    /**
     * Exclui um professor do sistema pelo ID.
     * 
     * @param id O identificador único do professor a ser excluído
     */
    void excluir(int id);
    
    /**
     * Busca um professor pelo ID.
     * 
     * @param id O identificador único do professor
     * @return O professor encontrado, ou null se não encontrado
     */
    Professor buscarPorId(int id);
    
    /**
     * Busca um professor pelo CPF.
     * 
     * @param cpf O CPF do professor (11 dígitos numéricos)
     * @return O professor encontrado, ou null se não encontrado
     */
    Professor buscarPorCpf(String cpf);
    
    /**
     * Lista todos os professores cadastrados no sistema.
     * 
     * @return Lista contendo todos os professores cadastrados
     */
    List<Professor> listarTodos();
    
    /**
     * Calcula a idade com base na data de nascimento fornecida.
     * 
     * @param dataNascimento A data de nascimento
     * @return A idade calculada em anos
     * @throws service.exception.ValidacaoException Se a data de nascimento for nula
     */
    int calcularIdade(java.util.Date dataNascimento);
    
    /**
     * Valida e formata uma string, removendo caracteres não numéricos.
     * 
     * @param input A string a ser formatada
     * @return String contendo apenas os caracteres numéricos, ou string vazia se input for null
     */
    String validarFormatado(String input);
}

