package service.exception;

/**
 * Exceção customizada para erros de validação na camada de serviço.
 * Lançada quando os dados fornecidos não atendem às regras de negócio.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public class ValidacaoException extends RuntimeException {
    
    /**
     * Construtor que cria uma exceção com uma mensagem de erro.
     * 
     * @param message A mensagem de erro descrevendo a violação da regra de negócio
     */
    public ValidacaoException(String message) {
        super(message);
    }
    
    /**
     * Construtor que cria uma exceção com uma mensagem de erro e uma causa.
     * 
     * @param message A mensagem de erro descrevendo a violação da regra de negócio
     * @param cause   A causa da exceção
     */
    public ValidacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
