package model;

/**
 * Classe abstrata que representa uma pessoa no sistema.
 * Serve como classe base para Aluno e Professor, contendo atributos comuns.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public abstract class Pessoa {

    /**
     * Identificador único da pessoa no sistema.
     */
    private int id;

    /**
     * Nome completo da pessoa.
     */
    private String nome;

    /**
     * Idade da pessoa em anos.
     */
    private int idade;

    /**
     * Construtor padrão sem parâmetros.
     * Inicializa uma instância de Pessoa com valores padrão.
     */
    public Pessoa() {
    }

    /**
     * Construtor completo que inicializa todos os atributos da pessoa.
     * 
     * @param id    Identificador único da pessoa
     * @param nome  Nome completo da pessoa
     * @param idade Idade da pessoa em anos
     */
    public Pessoa(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    /**
     * Retorna o identificador único da pessoa.
     * 
     * @return O ID da pessoa
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único da pessoa.
     * 
     * @param id O ID a ser atribuído à pessoa
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome completo da pessoa.
     * 
     * @return O nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome completo da pessoa.
     * 
     * @param nome O nome a ser atribuído à pessoa
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a idade da pessoa em anos.
     * 
     * @return A idade da pessoa
     */
    public int getIdade() {
        return idade;
    }

    /**
     * Define a idade da pessoa em anos.
     * 
     * @param idade A idade a ser atribuída à pessoa
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

}
