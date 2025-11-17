package repository.exception;

/**
 * Exceção customizada utilizada para representar erros na camada de acesso
 * a dados (Repositório). <br><br>
 * 
 * Esta classe permite encapsular exceções de banco de dados ou de operações
 * relacionadas a armazenamento, fornecendo uma forma mais clara e específica
 * de tratamento de erros, substituindo o uso de {@link RuntimeException}
 * genérica.
 */
public class DataAccessException extends RuntimeException {

    /**
     * Construtor que cria uma nova DataAccessException com uma mensagem
     * descritiva e a causa original da exceção.
     *
     * @param message mensagem explicando o erro ocorrido
     * @param cause exceção original que gerou este erro
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
