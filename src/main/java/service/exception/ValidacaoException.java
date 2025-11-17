package service.exception;

/**
 * Exceção utilizada para indicar erros de validação nas regras de negócio.
 * <p>
 * Esta classe estende {@link RuntimeException}, permitindo que falhas de 
 * validação sejam lançadas sem necessidade de declaração explícita.
 */
public class ValidacaoException extends RuntimeException {

    /**
     * Cria uma nova exceção de validação com uma mensagem descritiva.
     *
     * @param message mensagem explicando o motivo da falha de validação.
     */
    public ValidacaoException(String message) {
        super(message);
    }

    /**
     * Cria uma nova exceção de validação com uma mensagem e uma causa associada.
     *
     * @param message mensagem explicando o erro.
     * @param cause exceção original que causou esta falha.
     */
    public ValidacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
