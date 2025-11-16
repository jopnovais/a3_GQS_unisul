package repository.exception;

/**
 * Exceção customizada para erros na camada de acesso a dados (Repositório).
 * Substitui o uso de RuntimeException genérica.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public class DataAccessException extends RuntimeException {
    
    /**
     * Construtor que cria uma exceção com uma mensagem de erro e uma causa.
     * 
     * @param message A mensagem de erro descrevendo o problema de acesso a dados
     * @param cause   A causa da exceção (geralmente uma SQLException)
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
