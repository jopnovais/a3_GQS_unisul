package repository;

import model.Aluno;
import java.util.List;

public interface AlunoRepository {
    boolean save(Aluno aluno);
    boolean update(Aluno aluno);
    boolean delete(int id);
    Aluno findById(int id);
    List<Aluno> findAll();
    int getMaxId();
}

