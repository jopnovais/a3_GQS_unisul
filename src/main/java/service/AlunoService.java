package service;

import model.Aluno;
import java.util.List;

public interface AlunoService {
    
    void salvar(Aluno aluno) throws service.exception.ValidacaoException;
    
    void atualizar(Aluno aluno) throws service.exception.ValidacaoException;
    
    void excluir(int id);
    
    Aluno buscarPorId(int id);
    
    List<Aluno> listarTodos();
    
    int calcularIdade(java.util.Date dataNascimento);
}

