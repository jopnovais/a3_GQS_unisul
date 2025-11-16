package model;

import java.sql.SQLException;
import java.util.ArrayList;

import repository.ProfessorRepository;
import repository.ProfessorRepositoryImpl;

/**
 * Classe que representa um professor no sistema.
 * Estende a classe Pessoa e adiciona informações específicas de professor como campus, CPF, contato, título e salário.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public class Professor extends Pessoa {

    /**
     * Campus onde o professor atua.
     */
    private String campus;

    /**
     * CPF do professor (11 dígitos numéricos).
     */
    private String cpf;

    /**
     * Número de contato do professor (11 dígitos numéricos).
     */
    private String contato;

    /**
     * Título acadêmico do professor (ex: Mestre, Doutor).
     */
    private String titulo;

    /**
     * Salário do professor.
     */
    private double salario;

    /**
     * Repositório responsável pelas operações de persistência de professores.
     */
    private final ProfessorRepository repository;

    /**
     * Construtor padrão sem parâmetros.
     * Inicializa o repositório de professores.
     */
    public Professor() {
        this.repository = new ProfessorRepositoryImpl();
    }

    /**
     * Construtor que inicializa apenas os atributos específicos de professor.
     * 
     * @param campus  Campus onde o professor atua
     * @param cpf     CPF do professor (11 dígitos numéricos)
     * @param contato Número de contato do professor (11 dígitos numéricos)
     * @param titulo  Título acadêmico do professor
     * @param salario Salário do professor
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
     * Construtor completo que inicializa todos os atributos do professor.
     * 
     * @param campus  Campus onde o professor atua
     * @param cpf     CPF do professor (11 dígitos numéricos)
     * @param contato Número de contato do professor (11 dígitos numéricos)
     * @param titulo  Título acadêmico do professor
     * @param salario Salário do professor
     * @param id      Identificador único do professor
     * @param nome    Nome completo do professor
     * @param idade   Idade do professor em anos
     */
    public Professor(String campus, String cpf, String contato, String titulo, double salario, int id, String nome, int idade) {
        super(id, nome, idade);
        this.campus = campus;
        this.cpf = cpf;
        this.contato = contato;
        this.titulo = titulo;
        this.salario = salario;
        this.repository = new ProfessorRepositoryImpl();
    }

    /**
     * Retorna o campus onde o professor atua.
     * 
     * @return O campus do professor
     */
    public String getCampus() {
        return campus;
    }

    /**
     * Define o campus onde o professor atua.
     * 
     * @param campus O campus a ser atribuído
     */
    public void setCampus(String campus) {
        this.campus = campus;
    }

    /**
     * Retorna o CPF do professor.
     * 
     * @return O CPF do professor
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do professor.
     * 
     * @param cpf O CPF a ser atribuído (11 dígitos numéricos)
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o número de contato do professor.
     * 
     * @return O contato do professor
     */
    public String getContato() {
        return contato;
    }

    /**
     * Define o número de contato do professor.
     * 
     * @param contato O contato a ser atribuído (11 dígitos numéricos)
     */
    public void setContato(String contato) {
        this.contato = contato;
    }

    /**
     * Retorna o título acadêmico do professor.
     * 
     * @return O título do professor
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título acadêmico do professor.
     * 
     * @param titulo O título a ser atribuído
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Retorna o salário do professor.
     * 
     * @return O salário do professor
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Define o salário do professor.
     * 
     * @param salario O salário a ser atribuído
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Retorna uma representação em string do professor.
     * 
     * @return String formatada com todas as informações do professor
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

    /*
        ABAIXO OS M�TODOS PARA USO JUNTO COM O DAO
        SIMULANDO A ESTRUTURA EM CAMADAS PARA USAR COM BANCOS DE DADOS.
     */
    // Retorna a Lista de Professores (objetos)
    public ArrayList getMinhaLista() {
        return new ArrayList(repository.findAll());
    }

    /**
     * Insere um novo professor no banco de dados.
     * 
     * @param campus  Campus onde o professor atua
     * @param cpf     CPF do professor (11 dígitos numéricos)
     * @param contato Número de contato do professor (11 dígitos numéricos)
     * @param titulo  Título acadêmico do professor
     * @param salario Salário do professor
     * @param nome    Nome completo do professor
     * @param idade   Idade do professor em anos
     * @return true se o professor foi inserido com sucesso, false caso contrário
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados
     */
    public boolean InsertProfessorBD(String campus, String cpf, String contato, String titulo, double salario, String nome, int idade) throws SQLException {
        Professor objeto = new Professor(campus, cpf, contato, titulo, salario, 0, nome, idade);
        return repository.save(objeto);
    }

    /**
     * Remove um professor do banco de dados pelo ID.
     * 
     * @param id Identificador único do professor a ser removido
     * @return true se o professor foi removido com sucesso, false caso contrário
     */
    public boolean DeleteProfessorBD(int id) {
        return repository.delete(id);
    }

    /**
     * Atualiza os dados de um professor existente no banco de dados.
     * 
     * @param campus  Campus onde o professor atua
     * @param cpf     CPF do professor (11 dígitos numéricos)
     * @param contato Número de contato do professor (11 dígitos numéricos)
     * @param titulo  Título acadêmico do professor
     * @param salario Salário do professor
     * @param id      Identificador único do professor a ser atualizado
     * @param nome    Nome completo do professor
     * @param idade   Idade do professor em anos
     * @return true se o professor foi atualizado com sucesso, false caso contrário
     */
    public boolean UpdateProfessorBD(String campus, String cpf, String contato, String titulo, double salario, int id, String nome, int idade) {
        Professor objeto = new Professor(campus, cpf, contato, titulo, salario, id, nome, idade);
        return repository.update(objeto);
    }

    /**
     * Carrega um professor do banco de dados pelo ID.
     * 
     * @param id Identificador único do professor a ser carregado
     * @return O objeto Professor encontrado, ou null se não encontrado
     */
    public Professor carregaProfessor(int id) {
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
