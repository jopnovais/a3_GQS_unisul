package model;

import java.sql.SQLException;
import java.util.ArrayList;

import repository.ProfessorRepository;
import repository.ProfessorRepositoryImpl;

public class Professor extends Pessoa {

    private String campus;
    private String cpf;
    private String contato;
    private String titulo;
    private double salario;
    private final ProfessorRepository repository;

    // Construtores
    public Professor() {
        this.repository = new ProfessorRepositoryImpl();
    }

    public Professor(String campus, String cpf, String contato, String titulo, double salario) {
        this.campus = campus;
        this.cpf = cpf;
        this.contato = contato;
        this.titulo = titulo;
        this.salario = salario;
        this.repository = new ProfessorRepositoryImpl();
    }

    public Professor(String campus, String cpf, String contato, String titulo, double salario, int id, String nome, int idade) {
        super(id, nome, idade);
        this.campus = campus;
        this.cpf = cpf;
        this.contato = contato;
        this.titulo = titulo;
        this.salario = salario;
        this.repository = new ProfessorRepositoryImpl();
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Idade: " + this.getIdade()
                + "\n Campus: " + this.getCampus()
                + "\n CPF:" + this.getCpf()
                + "\n Contato:" + this.getContato()
                + "\n Título:" + this.getTitulo()
                + "\n Salário:" + this.getSalario()
                + "\n -----------";
    }

    /*
        ABAIXO OS M�TODOS PARA USO JUNTO COM O DAO
        SIMULANDO A ESTRUTURA EM CAMADAS PARA USAR COM BANCOS DE DADOS.
     */
    // Retorna a Lista de Professores (objetos)
    public ArrayList getMinhaLista() {
        return new ArrayList(repository.findAll());
    }

    public boolean InsertProfessorBD(String campus, String cpf, String contato, String titulo, double salario, String nome, int idade) throws SQLException {
        Professor objeto = new Professor(campus, cpf, contato, titulo, salario, 0, nome, idade);
        return repository.save(objeto);
    }

    public boolean DeleteProfessorBD(int id) {
        return repository.delete(id);
    }

    public boolean UpdateProfessorBD(String campus, String cpf, String contato, String titulo, double salario, int id, String nome, int idade) {
        Professor objeto = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);
        return repository.update(objeto);
    }

    public Professor carregaProfessor(int id) {
        return repository.findById(id);
    }

    public int maiorID() throws SQLException {
        return repository.getMaxId();
    }
}
