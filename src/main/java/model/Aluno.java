package model;

import java.util.*;
import repository.AlunoRepository;
import repository.AlunoRepositoryImpl;
import java.sql.SQLException;

public class Aluno extends Pessoa {

    private String curso;
    private int fase;
    private final AlunoRepository repository;

    public Aluno() {
        this.repository = new AlunoRepositoryImpl();
    }

    public Aluno(String curso, int fase) {
        this.curso = curso;
        this.fase = fase;
        this.repository = new AlunoRepositoryImpl();
    }

    public Aluno(String curso, int fase, int id, String nome, int idade) {
        super(id, nome, idade);
        this.curso = curso;
        this.fase = fase;
        this.repository = new AlunoRepositoryImpl();
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Idade: " + this.getIdade()
                + "\n Curso: " + this.getCurso()
                + "\n Fase:" + this.getFase()
                + "\n -----------";
    }

    public ArrayList getMinhaLista() {
        return new ArrayList(repository.findAll());
    }

    public boolean InsertAlunoBD(String curso, int fase, String nome, int idade) throws SQLException {
        Aluno objeto = new Aluno(curso, fase, 0, nome, idade);
        return repository.save(objeto);
    }

    public boolean DeleteAlunoBD(int id) {
        return repository.delete(id);
    }

    public boolean UpdateAlunoBD(String curso, int fase, int id, String nome, int idade) {
        Aluno objeto = new Aluno(curso, fase, id, nome, idade);
        return repository.update(objeto);
    }

    public Aluno carregaAluno(int id) {
        return repository.findById(id);
    }

    public int maiorID() throws SQLException {
        return repository.getMaxId();
    }
}
