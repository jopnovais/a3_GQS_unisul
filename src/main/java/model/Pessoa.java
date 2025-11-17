package model;

/**
 * Classe abstrata que representa uma pessoa no sistema.
 * 
 * <p>Fornece atributos básicos como ID, nome e idade,
 * além de métodos de acesso e modificação.</p>
 *
 * <p>Outras classes, como {@link Aluno} e {@link Professor},
 * herdam desta classe para reutilizar essas propriedades comuns.</p>
 */
public abstract class Pessoa {

    /** Identificador único da pessoa. */
    private int id;

    /** Nome da pessoa. */
    private String nome;

    /** Idade da pessoa. */
    private int idade;

    /**
     * Construtor padrão. Cria uma pessoa sem atributos inicializados.
     */
    public Pessoa() {
    }

    /**
     * Construtor completo da classe.
     *
     * @param id    Identificador único.
     * @param nome  Nome da pessoa.
     * @param idade Idade da pessoa.
     */
    public Pessoa(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    /**
     * Obtém o ID da pessoa.
     *
     * @return ID atual.
     */
    public int getId() {
        return id;
    }

    /**
     * Define um novo ID para a pessoa.
     *
     * @param id Novo identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome da pessoa.
     *
     * @return Nome atual.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a idade da pessoa.
     *
     * @return Idade atual.
     */
    public int getIdade() {
        return idade;
    }

    /**
     * Define a idade da pessoa.
     *
     * @param idade Nova idade.
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

}
