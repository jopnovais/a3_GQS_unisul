package repository.exception;

/**
 * Exceção customizada para erros na camada de acesso a dados (Repositório).
 * Substitui o uso de RuntimeException genérica.
 */
public class DataAccessException extends RuntimeException {
    
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

