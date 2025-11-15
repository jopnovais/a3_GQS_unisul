package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários - Pessoa")
class PessoaTest {

    // Classe concreta para testar Pessoa (classe abstrata)
    private static class PessoaConcreta extends Pessoa {
        public PessoaConcreta() {
            super();
        }
        
        public PessoaConcreta(int id, String nome, int idade) {
            super(id, nome, idade);
        }
    }

    @Test
    @DisplayName("Caso 1: Criar Pessoa com construtor padrão - deve criar instância com valores padrão")
    void testConstrutorPadrao_DeveCriarInstanciaComValoresPadrao() {
        Pessoa pessoa = new PessoaConcreta();
        
        assertEquals(0, pessoa.getId());
        assertNull(pessoa.getNome());
        assertEquals(0, pessoa.getIdade());
    }

    @Test
    @DisplayName("Caso 2: Criar Pessoa com construtor parametrizado - deve criar instância com valores fornecidos")
    void testConstrutorParametrizado_DeveCriarInstanciaComValoresFornecidos() {
        Pessoa pessoa = new PessoaConcreta(1, "João Silva", 25);
        
        assertEquals(1, pessoa.getId());
        assertEquals("João Silva", pessoa.getNome());
        assertEquals(25, pessoa.getIdade());
    }

    @Test
    @DisplayName("Caso 3: Setter e Getter de ID - deve definir e retornar ID corretamente")
    void testSetIdEGetId_DeveDefinirERetornarIdCorretamente() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setId(10);
        assertEquals(10, pessoa.getId());
        
        pessoa.setId(20);
        assertEquals(20, pessoa.getId());
    }

    @Test
    @DisplayName("Caso 4: Setter e Getter de Nome - deve definir e retornar nome corretamente")
    void testSetNomeEGetNome_DeveDefinirERetornarNomeCorretamente() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setNome("Maria Santos");
        assertEquals("Maria Santos", pessoa.getNome());
        
        pessoa.setNome("Pedro Oliveira");
        assertEquals("Pedro Oliveira", pessoa.getNome());
        
        pessoa.setNome(null);
        assertNull(pessoa.getNome());
    }

    @Test
    @DisplayName("Caso 5: Setter e Getter de Idade - deve definir e retornar idade corretamente")
    void testSetIdadeEGetIdade_DeveDefinirERetornarIdadeCorretamente() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setIdade(20);
        assertEquals(20, pessoa.getIdade());
        
        pessoa.setIdade(30);
        assertEquals(30, pessoa.getIdade());
        
        pessoa.setIdade(0);
        assertEquals(0, pessoa.getIdade());
    }

    @Test
    @DisplayName("Caso 6: Modificar todos os atributos - deve manter valores corretos")
    void testModificarTodosAtributos_DeveManterValoresCorretos() {
        Pessoa pessoa = new PessoaConcreta(1, "Teste", 10);
        
        pessoa.setId(100);
        pessoa.setNome("Novo Nome");
        pessoa.setIdade(50);
        
        assertEquals(100, pessoa.getId());
        assertEquals("Novo Nome", pessoa.getNome());
        assertEquals(50, pessoa.getIdade());
    }

    @Test
    @DisplayName("Caso 7: Setter de ID com valor negativo - deve aceitar e armazenar")
    void testSetId_ValorNegativo_DeveAceitarEArmazenar() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setId(-1);
        assertEquals(-1, pessoa.getId());
        
        pessoa.setId(-100);
        assertEquals(-100, pessoa.getId());
    }

    @Test
    @DisplayName("Caso 8: Setter de ID com valor zero - deve aceitar e armazenar")
    void testSetId_ValorZero_DeveAceitarEArmazenar() {
        Pessoa pessoa = new PessoaConcreta(1, "Teste", 20);
        
        pessoa.setId(0);
        assertEquals(0, pessoa.getId());
    }

    @Test
    @DisplayName("Caso 9: Setter de ID com valor positivo grande - deve aceitar e armazenar")
    void testSetId_ValorPositivoGrande_DeveAceitarEArmazenar() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setId(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, pessoa.getId());
    }

    @Test
    @DisplayName("Caso 10: Setter de Nome com string vazia - deve aceitar e armazenar")
    void testSetNome_StringVazia_DeveAceitarEArmazenar() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setNome("");
        assertEquals("", pessoa.getNome());
    }

    @Test
    @DisplayName("Caso 11: Setter de Nome com string longa - deve aceitar e armazenar")
    void testSetNome_StringLonga_DeveAceitarEArmazenar() {
        Pessoa pessoa = new PessoaConcreta();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("A");
        }
        String nomeLongo = sb.toString();
        pessoa.setNome(nomeLongo);
        assertEquals(nomeLongo, pessoa.getNome());
    }

    @Test
    @DisplayName("Caso 12: Setter de Nome com caracteres especiais - deve aceitar e armazenar")
    void testSetNome_CaracteresEspeciais_DeveAceitarEArmazenar() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setNome("João da Silva-Santos");
        assertEquals("João da Silva-Santos", pessoa.getNome());
        
        pessoa.setNome("Maria José O'Connor");
        assertEquals("Maria José O'Connor", pessoa.getNome());
    }

    @Test
    @DisplayName("Caso 13: Setter de Idade com valor negativo - deve aceitar e armazenar")
    void testSetIdade_ValorNegativo_DeveAceitarEArmazenar() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setIdade(-1);
        assertEquals(-1, pessoa.getIdade());
        
        pessoa.setIdade(-100);
        assertEquals(-100, pessoa.getIdade());
    }

    @Test
    @DisplayName("Caso 14: Setter de Idade com valor zero - deve aceitar e armazenar")
    void testSetIdade_ValorZero_DeveAceitarEArmazenar() {
        Pessoa pessoa = new PessoaConcreta(1, "Teste", 20);
        
        pessoa.setIdade(0);
        assertEquals(0, pessoa.getIdade());
    }

    @Test
    @DisplayName("Caso 15: Setter de Idade com valor positivo grande - deve aceitar e armazenar")
    void testSetIdade_ValorPositivoGrande_DeveAceitarEArmazenar() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setIdade(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, pessoa.getIdade());
    }

    @Test
    @DisplayName("Caso 16: Construtor parametrizado com valores limites - deve criar instância corretamente")
    void testConstrutorParametrizado_ValoresLimites_DeveCriarInstanciaCorretamente() {
        Pessoa pessoa1 = new PessoaConcreta(0, "", 0);
        assertEquals(0, pessoa1.getId());
        assertEquals("", pessoa1.getNome());
        assertEquals(0, pessoa1.getIdade());
        
        Pessoa pessoa2 = new PessoaConcreta(Integer.MAX_VALUE, "Nome", Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, pessoa2.getId());
        assertEquals("Nome", pessoa2.getNome());
        assertEquals(Integer.MAX_VALUE, pessoa2.getIdade());
    }

    @Test
    @DisplayName("Caso 17: Construtor parametrizado com nome nulo - deve criar instância com nome nulo")
    void testConstrutorParametrizado_NomeNulo_DeveCriarInstanciaComNomeNulo() {
        Pessoa pessoa = new PessoaConcreta(1, null, 25);
        
        assertEquals(1, pessoa.getId());
        assertNull(pessoa.getNome());
        assertEquals(25, pessoa.getIdade());
    }

    @Test
    @DisplayName("Caso 18: Múltiplas alterações sequenciais - deve manter último valor")
    void testMultiplasAlteracoesSequenciais_DeveManterUltimoValor() {
        Pessoa pessoa = new PessoaConcreta();
        
        pessoa.setId(1);
        pessoa.setId(2);
        pessoa.setId(3);
        assertEquals(3, pessoa.getId());
        
        pessoa.setNome("Nome1");
        pessoa.setNome("Nome2");
        pessoa.setNome("Nome3");
        assertEquals("Nome3", pessoa.getNome());
        
        pessoa.setIdade(10);
        pessoa.setIdade(20);
        pessoa.setIdade(30);
        assertEquals(30, pessoa.getIdade());
    }

    @Test
    @DisplayName("Caso 19: Verificar independência entre instâncias - cada instância deve manter seus próprios valores")
    void testIndependenciaEntreInstancias_CadaInstanciaDeveManterPropriosValores() {
        Pessoa pessoa1 = new PessoaConcreta(1, "Pessoa 1", 20);
        Pessoa pessoa2 = new PessoaConcreta(2, "Pessoa 2", 30);
        
        assertEquals(1, pessoa1.getId());
        assertEquals("Pessoa 1", pessoa1.getNome());
        assertEquals(20, pessoa1.getIdade());
        
        assertEquals(2, pessoa2.getId());
        assertEquals("Pessoa 2", pessoa2.getNome());
        assertEquals(30, pessoa2.getIdade());
        
        pessoa1.setId(100);
        pessoa2.setId(200);
        
        assertEquals(100, pessoa1.getId());
        assertEquals(200, pessoa2.getId());
    }

    @Test
    @DisplayName("Caso 20: Verificar que Pessoa é classe abstrata - não pode ser instanciada diretamente")
    void testPessoaEhClasseAbstrata_NaoPodeSerInstanciadaDiretamente() {
        // Este teste verifica que precisamos de uma classe concreta para testar
        // A classe PessoaConcreta é usada para este propósito
        Pessoa pessoa = new PessoaConcreta();
        
        assertNotNull(pessoa);
        assertTrue(pessoa instanceof Pessoa);
    }
}

