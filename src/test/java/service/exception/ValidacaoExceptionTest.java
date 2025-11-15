package service.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários - ValidacaoException")
class ValidacaoExceptionTest {

    @Test
    @DisplayName("Caso 1: Criar ValidacaoException com mensagem - deve criar exceção com mensagem correta")
    void testConstrutorComMensagem_DeveCriarExcecaoComMensagemCorreta() {
        String mensagem = "Erro de validação";
        ValidacaoException exception = new ValidacaoException(mensagem);
        
        assertEquals(mensagem, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    @DisplayName("Caso 2: Criar ValidacaoException com mensagem e causa - deve criar exceção com mensagem e causa corretas")
    void testConstrutorComMensagemECausa_DeveCriarExcecaoComMensagemECausaCorretas() {
        String mensagem = "Erro de validação";
        Throwable causa = new IllegalArgumentException("Causa original");
        ValidacaoException exception = new ValidacaoException(mensagem, causa);
        
        assertEquals(mensagem, exception.getMessage());
        assertEquals(causa, exception.getCause());
    }

    @Test
    @DisplayName("Caso 3: ValidacaoException com mensagem vazia - deve criar exceção com mensagem vazia")
    void testConstrutorComMensagemVazia_DeveCriarExcecaoComMensagemVazia() {
        ValidacaoException exception = new ValidacaoException("");
        
        assertEquals("", exception.getMessage());
    }

    @Test
    @DisplayName("Caso 4: ValidacaoException com mensagem nula - deve criar exceção com mensagem nula")
    void testConstrutorComMensagemNula_DeveCriarExcecaoComMensagemNula() {
        ValidacaoException exception = new ValidacaoException((String) null);
        
        assertNull(exception.getMessage());
    }

    @Test
    @DisplayName("Caso 5: ValidacaoException é RuntimeException - deve ser instância de RuntimeException")
    void testValidacaoExceptionERuntimeException_DeveSerInstanciaDeRuntimeException() {
        ValidacaoException exception = new ValidacaoException("Teste");
        
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    @DisplayName("Caso 6: Lançar e capturar ValidacaoException - deve funcionar corretamente")
    void testLancarECapturarValidacaoException_DeveFuncionarCorretamente() {
        String mensagemEsperada = "Erro de validação";
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            throw new ValidacaoException(mensagemEsperada);
        });
        
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}

