package repository;

import model.Professor;
import java.util.List;

public interface ProfessorRepository {
    boolean save(Professor professor);
    boolean update(Professor professor);
    boolean delete(int id);
    Professor findById(int id);
    
    Professor findByCpf(String cpf);
    
    List<Professor> findAll();
    
    int getMaxId();
}

