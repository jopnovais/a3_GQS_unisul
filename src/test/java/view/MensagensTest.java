package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste Unitário PURO para a exceção customizada Mensagens.
 * Este teste NÃO toca no banco de dados.
 */
@DisplayName("Teste Unitário - Mensagens (Exceção)")
class MensagensTest {

    @BeforeAll
    static void setHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    @DisplayName("Deve criar a exceção com a mensagem correta")
    void testMensagensException() {
        String mensagemErro = "Este é um erro de teste";
        Mensagens excecao = new Mensagens(mensagemErro);
        // Verifica se a mensagem armazenada na exceção é a correta
        assertEquals(mensagemErro, excecao.getMessage());
    }
}