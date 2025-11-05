package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste Unitário PURO para a classe CadastroAluno.
 * Este teste usa "Reflection" para acessar um método privado (calculaIdade),
 * pois esta é a única lógica de negócios pura e testável na classe.
 */
@DisplayName("Teste Unitário - CadastroAluno (via Reflection)")
class CadastroAlunoTest {

    private CadastroAluno instanciaCadastroAluno;

    @BeforeEach
    void setUp() {
        // Cria uma instância da classe que queremos testar
        instanciaCadastroAluno = new CadastroAluno();
    }

    @Test
    @DisplayName("Deve calcular a idade corretamente (testando método privado)")
    void deveCalcularIdadeCorretamente() throws Exception {
        // --- Arrange ---

        // Criar uma data de 20 anos atrás
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20); // 20 anos atrás
        Date dataNascimento = cal.getTime();

        // Usar Reflection para encontrar o método privado
        // "calculaIdade" é o nome do método
        // "Date.class" é o tipo do parâmetro (java.util.Date)
        Method metodoPrivado = CadastroAluno.class.getDeclaredMethod("calculaIdade", Date.class);

        // Tornar o método acessível
        metodoPrivado.setAccessible(true);

        // --- Act ---

        // Invocar o método privado
        // "instanciaCadastroAluno" é o objeto onde o método será executado
        // "dataNascimento" é o argumento que estamos passando
        Integer idadeCalculada = (Integer) metodoPrivado.invoke(instanciaCadastroAluno, dataNascimento);

        // --- Assert ---

        // Verificar o resultado
        assertEquals(20, idadeCalculada, "A idade calculada deveria ser 20");
    }

    @Test
    @DisplayName("Deve calcular a idade de alguém que faz aniversário amanhã")
    void deveCalcularIdadeComAniversarioAmanha() throws Exception {
        // --- Arrange ---

        // Criar uma data de 20 anos atrás + 1 dia (faz aniversário amanhã)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20); // 20 anos atrás
        cal.add(Calendar.DAY_OF_YEAR, 1); // Amanhã
        Date dataNascimento = cal.getTime();

        // Acessar o método privado
        Method metodoPrivado = CadastroAluno.class.getDeclaredMethod("calculaIdade", Date.class);
        metodoPrivado.setAccessible(true);

        // --- Act ---
        Integer idadeCalculada = (Integer) metodoPrivado.invoke(instanciaCadastroAluno, dataNascimento);

        // --- Assert ---
        // A pessoa ainda tem 19, pois o aniversário é amanhã
        assertEquals(19, idadeCalculada, "A idade calculada deveria ser 19");
    }
}