package view;

/**
 * Exceção personalizada usada para exibir mensagens específicas na camada de visão.
 */
public class Mensagens extends Exception {

    /**
     * Cria uma nova exceção com a mensagem especificada.
     *
     * @param msg mensagem descritiva do erro
     */
    public Mensagens(String msg) {
        super(msg);
    }
}
