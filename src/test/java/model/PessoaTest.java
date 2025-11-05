package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste Unitário PURO para a classe Pessoa.
 * Este teste NÃO toca no banco de dados.
 *
 * NOTA: Como Pessoa é abstrata, instanciamos Aluno para testar a lógica herdada.
 */
@DisplayName("Teste Unitário - Pessoa")
class PessoaTest {

    @Test
    @DisplayName("Deve criar Pessoa com construtor completo")
    void testConstrutorCompleto() {
        // Usa Aluno (que é uma Pessoa) para instanciar a classe abstrata
        Pessoa pessoa = new Aluno("", 0, 10, "Nome Teste", 30);

        assertEquals(10, pessoa.getId());
        assertEquals("Nome Teste", pessoa.getNome());
        assertEquals(30, pessoa.getIdade());
    }

    @Test
    @DisplayName("Deve setar e obter atributos (Getters/Setters)")
    void testGettersSetters() {
        Pessoa pessoa = new Aluno(); // Instancia um Aluno (que é uma Pessoa)

        pessoa.setId(99);
        pessoa.setNome("Usuario");
        pessoa.setIdade(25);

        assertEquals(99, pessoa.getId());
        assertEquals("Usuario", pessoa.getNome());
        assertEquals(25, pessoa.getIdade());
    }
}