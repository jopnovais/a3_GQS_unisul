package repository.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários - DataAccessException")
class DataAccessExceptionTest {

    @Test
    @DisplayName("Caso 1: Criar DataAccessException com mensagem e causa - deve criar exceção com mensagem e causa corretas")
    void testConstrutorComMensagemECausa_DeveCriarExcecaoComMensagemECausaCorretas() {
        String mensagem = "Erro ao acessar dados";
        Throwable causa = new RuntimeException("Causa original");
        DataAccessException exception = new DataAccessException(mensagem, causa);
        
        assertEquals(mensagem, exception.getMessage());
        assertEquals(causa, exception.getCause());
    }

    @Test
    @DisplayName("Caso 2: DataAccessException com mensagem vazia - deve criar exceção com mensagem vazia")
    void testConstrutorComMensagemVazia_DeveCriarExcecaoComMensagemVazia() {
        Throwable causa = new RuntimeException("Causa");
        DataAccessException exception = new DataAccessException("", causa);
        
        assertEquals("", exception.getMessage());
        assertEquals(causa, exception.getCause());
    }

    @Test
    @DisplayName("Caso 3: DataAccessException com causa nula - deve criar exceção com causa nula")
    void testConstrutorComCausaNula_DeveCriarExcecaoComCausaNula() {
        DataAccessException exception = new DataAccessException("Mensagem", null);
        
        assertEquals("Mensagem", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("Caso 4: DataAccessException é RuntimeException - deve ser instância de RuntimeException")
    void testDataAccessExceptionERuntimeException_DeveSerInstanciaDeRuntimeException() {
        DataAccessException exception = new DataAccessException("Teste", null);
        
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Caso 5: Lançar e capturar DataAccessException - deve funcionar corretamente")
    void testLancarECapturarDataAccessException_DeveFuncionarCorretamente() {
        String mensagemEsperada = "Erro ao acessar dados";
        Throwable causa = new RuntimeException("Causa");
        
        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            throw new DataAccessException(mensagemEsperada, causa);
        });
        
        assertEquals(mensagemEsperada, exception.getMessage());
        assertEquals(causa, exception.getCause());
    }

    @Test
    @DisplayName("Caso 6: DataAccessException com SQLException como causa - deve preservar causa SQLException")
    void testDataAccessExceptionComSQLException_DevePreservarCausaSQLException() {
        java.sql.SQLException sqlException = new java.sql.SQLException("Erro SQL");
        DataAccessException exception = new DataAccessException("Erro ao acessar dados", sqlException);
        
        assertTrue(exception.getCause() instanceof java.sql.SQLException);
        assertEquals("Erro SQL", exception.getCause().getMessage());
    }
}

