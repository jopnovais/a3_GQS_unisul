package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste Unitário PURO para a classe CadastroProfessor.
 * Este teste usa "Reflection" para acessar métodos privados
 * que contêm lógica de negócios pura (validação e cálculo).
 */
@DisplayName("Teste Unitário - CadastroProfessor (via Reflection)")
class CadastroProfessorTest {

    @BeforeAll
    static void setHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    private CadastroProfessor instanciaCadastroProfessor;

    @BeforeEach
    void setUp() throws java.text.ParseException {
        // Cria uma instância da classe que queremos testar
        instanciaCadastroProfessor = new CadastroProfessor();
    }

    // --- Teste para o método calculaIdade ---

    @Test
    @DisplayName("Deve calcular a idade corretamente (20 anos)")
    void deveCalcularIdadeCorretamente() throws Exception {
        // Arrange
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20); // 20 anos atrás
        Date dataNascimento = cal.getTime();

        Integer idadeCalculada = (Integer) invocarMetodoPrivado("calculaIdade",
                Date.class,
                dataNascimento);
        assertEquals(20, idadeCalculada, "A idade calculada deveria ser 20");
    }

    // --- Testes para o método validarFormatado ---

    @Test
    @DisplayName("Deve validar/limpar R$ (salário)")
    void deveValidarCampoSalario() throws Exception {
        String salarioFormatado = "R$5000"; // Como vem da JFormattedTextField

        String salarioLimpo = (String) invocarMetodoPrivado("validarFormatado",
                String.class,
                salarioFormatado);

        assertEquals("5000", salarioLimpo);
    }

    @Test
    @DisplayName("Deve validar/limpar CPF")
    void deveValidarCampoCpf() throws Exception {
        String cpfFormatado = "123.456.789-00";

        String cpfLimpo = (String) invocarMetodoPrivado("validarFormatado",
                String.class,
                cpfFormatado);

        assertEquals("12345678900", cpfLimpo);
    }

    @Test
    @DisplayName("Deve validar/limpar Contato (telefone)")
    void deveValidarCampoContato() throws Exception {
        String contatoFormatado = "(48) 9 9999-9999";

        String contatoLimpo = (String) invocarMetodoPrivado("validarFormatado",
                String.class,
                contatoFormatado);

        assertEquals("48999999999", contatoLimpo);
    }

    @Test
    @DisplayName("Deve retornar string vazia se não houver números")
    void deveRetornarVazioParaStringSemNumeros() throws Exception {
        String input = "abcde-@#$";

        String resultado = (String) invocarMetodoPrivado("validarFormatado",
                String.class,
                input);

        assertEquals("", resultado, "Deveria retornar vazio se não houver dígitos");
    }

    /**
     * Método auxiliar para simplificar o uso de Reflection nos testes.
     *
     * @param nomeMetodo O nome exato do método privado.
     * @param tipoParametro O tipo (Classe) do parâmetro.
     * @param valorParametro O valor (argumento) a ser passado.
     * @return O objeto retornado pelo método invocado.
     */
    private Object invocarMetodoPrivado(String nomeMetodo, Class<?> tipoParametro, Object valorParametro) throws Exception {
        // Encontra o método
        Method metodoPrivado = CadastroProfessor.class.getDeclaredMethod(nomeMetodo, tipoParametro);

        // Torna acessível
        metodoPrivado.setAccessible(true);

        // Invoca e retorna o resultado
        return metodoPrivado.invoke(instanciaCadastroProfessor, valorParametro);
    }
}