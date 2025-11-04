package model;

import java.util.*;
import DAO.AlunoDAO;
import java.sql.SQLException;

public class Aluno extends Pessoa {

    // Atributos
    private String curso;
    private int fase;
    private final AlunoDAO dao;

    // Método Construtor de Objeto Vazio
    public Aluno() {
        this.dao = new AlunoDAO(); // inicializado n�o importa em qual construtor
    }

    // Método Construtor de Objeto, inserindo dados
    public Aluno(String curso, int fase) {
        this.curso = curso;
        this.fase = fase;
        this.dao = new AlunoDAO(); // inicializado n�o importa em qual construtor
    }

    // Método Construtor usando tamb�m o construtor da SUPERCLASSE
    public Aluno(String curso, int fase, int id, String nome, int idade) {
        super(id, nome, idade);
        this.curso = curso;
        this.fase = fase;
        this.dao = new AlunoDAO(); // inicializado n�o importa em qual construtor
    }

    // Métodos GET e SET
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

    // Override necess�rio para poder retornar os dados de Pessoa no toString para aluno.
    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Idade: " + this.getIdade()
                + "\n Curso: " + this.getCurso()
                + "\n Fase:" + this.getFase()
                + "\n -----------";
    }

    /*
    
        ABAIXO OS M�TODOS PARA USO JUNTO COM O DAO
        SIMULANDO A ESTRUTURA EM CAMADAS PARA USAR COM BANCOS DE DADOS.
    
     */
    // Retorna a Lista de Alunos (objetos)
    public ArrayList getMinhaLista() {
        //return AlunoDAO.MinhaLista;
        return dao.getMinhaLista();
    }

    // Cadastra novo aluno
    public boolean InsertAlunoBD(String curso, int fase, String nome, int idade) throws SQLException {
        int id = this.maiorID() + 1;
        Aluno objeto = new Aluno(curso, fase, id, nome, idade);
        dao.InsertAlunoBD(objeto);
        return true;

    }

    // Deleta um aluno espec�fico pelo seu campo ID
    public boolean DeleteAlunoBD(int id) {
        dao.DeleteAlunoBD(id);
        return true;
    }

    // Edita um aluno espec�fico pelo seu campo ID
    public boolean UpdateAlunoBD(String curso, int fase, int id, String nome, int idade) {
        Aluno objeto = new Aluno(curso, fase, id, nome, idade);
        dao.UpdateAlunoBD(objeto);
        return true;
    }

    // carrega dados de um aluno espec�fico pelo seu ID
    public Aluno carregaAluno(int id) {
        dao.carregaAluno(id);
        return null;
    }

    // retorna o maior ID da nossa base de dados
    public int maiorID() throws SQLException {
        return dao.maiorID();
    }
}
