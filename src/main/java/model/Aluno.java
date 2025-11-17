package model;

import java.util.*;
import repository.AlunoRepository;
import repository.AlunoRepositoryImpl;
import java.sql.SQLException;

/**
 * Representa um aluno dentro do sistema acadêmico.
 * 
 * <p>A classe herda de {@link Pessoa}, adicionando informações específicas
 * como curso, fase e operações relacionadas ao persistência no banco.</p>
 *
 * <p>A classe usa um {@link AlunoRepository} para realizar operações
 * CRUD no banco de dados.</p>
 */
public class Aluno extends Pessoa {

    /** Curso no qual o aluno está matriculado. */
    private String curso;

    /** Fase/período atual do aluno. */
    private int fase;

    /** Repositório responsável pelas operações com banco de dados. */
    private final AlunoRepository repository;

    /**
     * Construtor padrão. Inicializa o repositório e cria um aluno vazio.
     */
    public Aluno() {
        this.repository = new AlunoRepositoryImpl();
    }

    /**
     * Construtor que cria um aluno sem dados herdados de {@link Pessoa}.
     *
     * @param curso Curso do aluno.
     * @param fase  Fase/semestre atual.
     */
    public Aluno(String curso, int fase) {
        this.curso = curso;
        this.fase = fase;
        this.repository = new AlunoRepositoryImpl();
    }

    /**
     * Construtor completo, incluindo dados pessoais herdados.
     *
     * @param curso Curso do aluno.
     * @param fase  Fase/semestre atual.
     * @param id    Identificador único do aluno.
     * @param nome  Nome do aluno.
     * @param idade Idade do aluno.
     */
    public Aluno(String curso, int fase, int id, String nome, int idade) {
        super(id, nome, idade);
        this.curso = curso;
        this.fase = fase;
        this.repository = new AlunoRepositoryImpl();
    }

    /**
     * Retorna o curso do aluno.
     *
     * @return Curso.
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Define o curso do aluno.
     *
     * @param curso Novo curso.
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Retorna a fase/semestre do aluno.
     *
     * @return Fase atual.
     */
    public int getFase() {
        return fase;
    }

    /**
     * Define a fase/semestre atual do aluno.
     *
     * @param fase Nova fase.
     */
    public void setFase(int fase) {
        this.fase = fase;
    }

    /**
     * Retorna uma representação textual do aluno.
     *
     * @return String formatada com os dados do aluno.
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
     * Retorna uma lista contendo todos os alunos cadastrados.
     *
     * @return {@link ArrayList} de alunos.
     */
    public ArrayList getMinhaLista() {
        return new ArrayList(repository.findAll());
    }

    /**
     * Insere um novo aluno no banco de dados.
     *
     * @param curso Curso do aluno.
     * @param fase  Fase atual.
     * @param nome  Nome do aluno.
     * @param idade Idade do aluno.
     * @return true se o aluno foi salvo com sucesso.
     * @throws SQLException Caso ocorra erro de conexão ou persistência.
     */
    public boolean InsertAlunoBD(String curso, int fase, String nome, int idade) throws SQLException {
        Aluno objeto = new Aluno(curso, fase, 0, nome, idade);
        return repository.save(objeto);
    }

    /**
     * Remove um aluno do banco de dados pelo ID.
     *
     * @param id ID do aluno a remover.
     * @return true se foi removido com sucesso.
     */
    public boolean DeleteAlunoBD(int id) {
        return repository.delete(id);
    }

    /**
     * Atualiza os dados de um aluno no banco.
     *
     * @param curso Novo curso.
     * @param fase  Nova fase.
     * @param id    ID do aluno.
     * @param nome  Novo nome.
     * @param idade Nova idade.
     * @return true se atualizado com sucesso.
     */
    public boolean UpdateAlunoBD(String curso, int fase, int id, String nome, int idade) {
        Aluno objeto = new Aluno(curso, fase, id, nome, idade);
        return repository.update(objeto);
    }

    /**
     * Carrega um aluno específico pelo ID.
     *
     * @param id ID do aluno.
     * @return Objeto {@link Aluno} encontrado ou null se não existir.
     */
    public Aluno carregaAluno(int id) {
        return repository.findById(id);
    }

    /**
     * Retorna o maior ID existente no banco de dados.
     *
     * @return Maior ID encontrado.
     * @throws SQLException Caso ocorra erro ao consultar o banco.
     */
    public int maiorID() throws SQLException {
        return repository.getMaxId();
    }
}
