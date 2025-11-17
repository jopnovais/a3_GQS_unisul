package service;

import model.Professor;
import java.util.List;

public interface ProfessorService {
    
    void salvar(Professor professor) throws service.exception.ValidacaoException;
    
    void atualizar(Professor professor) throws service.exception.ValidacaoException;
    
    void excluir(int id);
    
    Professor buscarPorId(int id);
    
    Professor buscarPorCpf(String cpf);
    
    List<Professor> listarTodos();
    
    int calcularIdade(java.util.Date dataNascimento);
    
    String validarFormatado(String input);
}

