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
}

