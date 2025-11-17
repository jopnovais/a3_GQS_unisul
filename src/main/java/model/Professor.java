package model;

import java.sql.SQLException;
import java.util.ArrayList;

import repository.ProfessorRepository;
import repository.ProfessorRepositoryImpl;

/**
 * Representa um professor no sistema acadêmico.
 *
 * <p>Esta classe herda de {@link Pessoa}, acrescentando informações
 * específicas como campus, CPF, contato, título acadêmico e salário.</p>
 *
 * <p>Também encapsula operações de persistência utilizando
 * um {@link ProfessorRepository} seguindo uma estrutura em camadas.</p>
 */
public class Professor extends Pessoa {

    /** Campus onde o professor atua. */
    private String campus;

    /** CPF do professor. */
    private String cpf;

    /** Meio de contato do professor (e-mail, telefone etc.). */
    private String contato;

    /** Título acadêmico (ex.: Doutor, Mestre, Especialista). */
    private String titulo;

    /** Salário do professor. */
    private double salario;

    /** Repositório responsável pelas operações de persistência. */
    private final ProfessorRepository repository;

    /**
     * Construtor padrão. Inicializa o repositório.
     */
    public Professor() {
        this.repository = new ProfessorRepositoryImpl();
    }

    /**
     * Construtor que cria um professor sem dados herdados de {@link Pessoa}.
     *
     * @param campus  Campus onde atua.
     * @param cpf     CPF do professor.
     * @param contato Contato do professor.
     * @param titulo  Título acadêmico.
     * @param salario Salário atual.
     */
    public Professor(String campus, String cpf, String contato, String titulo, double salario) {
        this.campus = campus;
        this.cpf = cpf;
        this.contato = contato;
        this.titulo = titulo;
        this.salario = salario;
        this.repository = new ProfessorRepositoryImpl();
    }

    /**
     * Construtor completo, incluindo dados herdados de {@link Pessoa}.
     *
     * @param campus  Campus onde atua.
     * @param cpf     CPF do professor.
     * @param contato Contato do professor.
     * @param titulo  Título acadêmico.
     * @param salario Salário.
     * @param id      ID do professor.
     * @param nome    Nome completo.
     * @param idade   Idade.
     */
    public Professor(String campus, String cpf, String contato, String titulo, double salario,
                     int id, String nome, int idade) {
        super(id, nome, idade);
        this.campus = campus;
        this.cpf = cpf;
        this.contato = contato;
        this.titulo = titulo;
        this.salario = salario;
        this.repository = new ProfessorRepositoryImpl();
    }

    /** @return Campus onde o professor atua. */
    public String getCampus() {
        return campus;
    }

    /** @param campus Novo campus. */
    public void setCampus(String campus) {
        this.campus = campus;
    }

    /** @return CPF do professor. */
    public String getCpf() {
        return cpf;
    }

    /** @param cpf Novo CPF. */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /** @return Contato do professor. */
    public String getContato() {
        return contato;
    }

    /** @param contato Novo contato. */
    public void setContato(String contato) {
        this.contato = contato;
    }

    /** @return Título acadêmico. */
    public String getTitulo() {
        return titulo;
    }

    /** @param titulo Novo título acadêmico. */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** @return Salário atual. */
    public double getSalario() {
        return salario;
    }

    /** @param salario Novo salário. */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Retorna uma representação textual do professor.
     *
     * @return Dados formatados do professor.
     */
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

    // -------------------------------------------------------------------------
    // MÉTODOS DE PERSISTÊNCIA COM O REPOSITORY
    // -------------------------------------------------------------------------

    /**
     * Retorna uma lista contendo todos os professores cadastrados.
     *
     * @return {@link ArrayList} de professores.
     */
    public ArrayList getMinhaLista() {
        return new ArrayList(repository.findAll());
    }

    /**
     * Insere um novo professor no banco de dados.
     *
     * @param campus  Campus onde atua.
     * @param cpf     CPF.
     * @param contato Contato.
     * @param titulo  Título acadêmico.
     * @param salario Salário.
     * @param nome    Nome completo.
     * @param idade   Idade.
     * @return true se o professor foi salvo com sucesso.
     * @throws SQLException Em caso de erro ao salvar no banco.
     */
    public boolean InsertProfessorBD(String campus, String cpf, String contato,
                                     String titulo, double salario,
                                     String nome, int idade) throws SQLException {
        Professor objeto = new Professor(campus, cpf, contato, titulo, salario, 0, nome, idade);
        return repository.save(objeto);
    }

    /**
     * Remove um professor pelo ID.
     *
     * @param id ID do professor.
     * @return true se removido com sucesso.
     */
    public boolean DeleteProfessorBD(int id) {
        return repository.delete(id);
    }

    /**
     * Atualiza os dados de um professor no banco.
     *
     * @param campus  Novo campus.
     * @param cpf     Novo CPF.
     * @param contato Novo contato.
     * @param titulo  Novo título.
     * @param salario Novo salário.
     * @param id      ID do professor.
     * @param nome    Novo nome.
     * @param idade   Nova idade.
     * @return true se atualizado com sucesso.
     */
    public boolean UpdateProfessorBD(String campus, String cpf, String contato,
                                     String titulo, double salario,
                                     int id, String nome, int idade) {
        Professor objeto = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);
        return repository.update(objeto);
    }

    /**
     * Busca um professor pelo ID.
     *
     * @param id ID a ser procurado.
     * @return Instância de {@link Professor}, ou null se não encontrado.
     */
    public Professor carregaProfessor(int id) {
        return repository.findById(id);
    }

    /**
     * Retorna o maior ID existente no banco.
     *
     * @return Maior ID.
     * @throws SQLException Em caso de erro na consulta.
     */
    public int maiorID() throws SQLException {
        return repository.getMaxId();
    }
}
