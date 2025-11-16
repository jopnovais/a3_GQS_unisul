package model;

import java.util.*;
import repository.AlunoRepository;
import repository.AlunoRepositoryImpl;
import java.sql.SQLException;

/**
 * Classe que representa um aluno no sistema.
 * Estende a classe Pessoa e adiciona informações específicas de aluno como curso e fase.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public class Aluno extends Pessoa {

    /**
     * Nome do curso em que o aluno está matriculado.
     */
    private String curso;

    /**
     * Fase/periodo atual do aluno no curso.
     */
    private int fase;

    /**
     * Repositório responsável pelas operações de persistência de alunos.
     */
    private final AlunoRepository repository;

    /**
     * Construtor padrão sem parâmetros.
     * Inicializa o repositório de alunos.
     */
    public Aluno() {
        this.repository = new AlunoRepositoryImpl();
    }

    /**
     * Construtor que inicializa apenas os atributos específicos de aluno.
     * 
     * @param curso Nome do curso do aluno
     * @param fase  Fase/periodo atual do aluno
     */
    public Aluno(String curso, int fase) {
        this.curso = curso;
        this.fase = fase;
        this.repository = new AlunoRepositoryImpl();
    }

    /**
     * Construtor completo que inicializa todos os atributos do aluno.
     * 
     * @param curso Nome do curso do aluno
     * @param fase  Fase/periodo atual do aluno
     * @param id    Identificador único do aluno
     * @param nome  Nome completo do aluno
     * @param idade Idade do aluno em anos
     */
    public Aluno(String curso, int fase, int id, String nome, int idade) {
        super(id, nome, idade);
        this.curso = curso;
        this.fase = fase;
        this.repository = new AlunoRepositoryImpl();
    }

    /**
     * Retorna o nome do curso do aluno.
     * 
     * @return O nome do curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Define o nome do curso do aluno.
     * 
     * @param curso O nome do curso a ser atribuído
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Retorna a fase/periodo atual do aluno.
     * 
     * @return A fase do aluno
     */
    public int getFase() {
        return fase;
    }

    /**
     * Define a fase/periodo atual do aluno.
     * 
     * @param fase A fase a ser atribuída ao aluno
     */
    public void setFase(int fase) {
        this.fase = fase;
    }

    /**
     * Retorna uma representação em string do aluno.
     * 
     * @return String formatada com todas as informações do aluno
     */
    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Idade: " + this.getIdade()
                + "\n Curso: " + this.getCurso()
                + "\n Fase:" + this.getFase()
                + "\n -----------";
    }

    /**
     * Retorna uma lista com todos os alunos cadastrados no banco de dados.
     * 
     * @return ArrayList contendo todos os alunos cadastrados
     */
    public ArrayList getMinhaLista() {
        return new ArrayList(repository.findAll());
    }

    /**
     * Insere um novo aluno no banco de dados.
     * 
     * @param curso Nome do curso do aluno
     * @param fase  Fase/periodo atual do aluno
     * @param nome  Nome completo do aluno
     * @param idade Idade do aluno em anos
     * @return true se o aluno foi inserido com sucesso, false caso contrário
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados
     */
    public boolean InsertAlunoBD(String curso, int fase, String nome, int idade) throws SQLException {
        Aluno objeto = new Aluno(curso, fase, 0, nome, idade);
        return repository.save(objeto);
    }

    /**
     * Remove um aluno do banco de dados pelo ID.
     * 
     * @param id Identificador único do aluno a ser removido
     * @return true se o aluno foi removido com sucesso, false caso contrário
     */
    public boolean DeleteAlunoBD(int id) {
        return repository.delete(id);
    }

    /**
     * Atualiza os dados de um aluno existente no banco de dados.
     * 
     * @param curso Nome do curso do aluno
     * @param fase  Fase/periodo atual do aluno
     * @param id    Identificador único do aluno a ser atualizado
     * @param nome  Nome completo do aluno
     * @param idade Idade do aluno em anos
     * @return true se o aluno foi atualizado com sucesso, false caso contrário
     */
    public boolean UpdateAlunoBD(String curso, int fase, int id, String nome, int idade) {
        Aluno objeto = new Aluno(curso, fase, id, nome, idade);
        return repository.update(objeto);
    }

    /**
     * Carrega um aluno do banco de dados pelo ID.
     * 
     * @param id Identificador único do aluno a ser carregado
     * @return O objeto Aluno encontrado, ou null se não encontrado
     */
    public Aluno carregaAluno(int id) {
        return repository.findById(id);
    }

    /**
     * Retorna o maior ID cadastrado no banco de dados.
     * 
     * @return O maior ID encontrado, ou 0 se não houver registros
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados
     */
    public int maiorID() throws SQLException {
        return repository.getMaxId();
    }
}
