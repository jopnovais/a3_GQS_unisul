package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários - Aluno")
class AlunoTest {

    @Test
    @DisplayName("Caso 1: Criar Aluno com construtor padrão - deve criar instância com valores padrão")
    void testConstrutorPadrao_DeveCriarInstanciaComValoresPadrao() {
        Aluno aluno = new Aluno();
        
        assertNull(aluno.getNome());
        assertEquals(0, aluno.getIdade());
        assertNull(aluno.getCurso());
        assertEquals(0, aluno.getFase());
    }

    @Test
    @DisplayName("Caso 2: Criar Aluno com construtor de curso e fase - deve criar instância com valores fornecidos")
    void testConstrutorCursoEFase_DeveCriarInstanciaComValoresFornecidos() {
        Aluno aluno = new Aluno("Ciências da Computação", 3);
        
        assertEquals("Ciências da Computação", aluno.getCurso());
        assertEquals(3, aluno.getFase());
    }

    @Test
    @DisplayName("Caso 3: Criar Aluno com construtor completo - deve criar instância com todos os valores")
    void testConstrutorCompleto_DeveCriarInstanciaComTodosValores() {
        Aluno aluno = new Aluno("Engenharia", 5, 1, "João Silva", 20);
        
        assertEquals(1, aluno.getId());
        assertEquals("João Silva", aluno.getNome());
        assertEquals(20, aluno.getIdade());
        assertEquals("Engenharia", aluno.getCurso());
        assertEquals(5, aluno.getFase());
    }

    @Test
    @DisplayName("Caso 4: Setter e Getter de Curso - deve definir e retornar curso corretamente")
    void testSetCursoEGetCurso_DeveDefinirERetornarCursoCorretamente() {
        Aluno aluno = new Aluno();
        
        aluno.setCurso("Administração");
        assertEquals("Administração", aluno.getCurso());
        
        aluno.setCurso("Design");
        assertEquals("Design", aluno.getCurso());
        
        aluno.setCurso(null);
        assertNull(aluno.getCurso());
    }

    @Test
    @DisplayName("Caso 5: Setter e Getter de Fase - deve definir e retornar fase corretamente")
    void testSetFaseEGetFase_DeveDefinirERetornarFaseCorretamente() {
        Aluno aluno = new Aluno();
        
        aluno.setFase(1);
        assertEquals(1, aluno.getFase());
        
        aluno.setFase(10);
        assertEquals(10, aluno.getFase());
        
        aluno.setFase(5);
        assertEquals(5, aluno.getFase());
    }

    @Test
    @DisplayName("Caso 6: toString - deve retornar string formatada corretamente")
    void testToString_DeveRetornarStringFormatadaCorretamente() {
        Aluno aluno = new Aluno("Ciências da Computação", 3, 1, "João Silva", 20);
        
        String resultado = aluno.toString();
        
        assertNotNull(resultado);
        assertTrue(resultado.contains("ID: 1"));
        assertTrue(resultado.contains("Nome: João Silva"));
        assertTrue(resultado.contains("Idade: 20"));
        assertTrue(resultado.contains("Curso: Ciências da Computação"));
        assertTrue(resultado.contains("Fase:3"));
    }

    @Test
    @DisplayName("Caso 7: Herdar métodos de Pessoa - deve funcionar corretamente")
    void testHerancaDePessoa_DeveFuncionarCorretamente() {
        Aluno aluno = new Aluno();
        
        aluno.setId(100);
        aluno.setNome("Maria Santos");
        aluno.setIdade(22);
        
        assertEquals(100, aluno.getId());
        assertEquals("Maria Santos", aluno.getNome());
        assertEquals(22, aluno.getIdade());
    }

    @Test
    @DisplayName("Caso 8: Modificar todos os atributos - deve manter valores corretos")
    void testModificarTodosAtributos_DeveManterValoresCorretos() {
        Aluno aluno = new Aluno("Curso Inicial", 1, 1, "Nome Inicial", 18);
        
        aluno.setId(200);
        aluno.setNome("Novo Nome");
        aluno.setIdade(25);
        aluno.setCurso("Novo Curso");
        aluno.setFase(8);
        
        assertEquals(200, aluno.getId());
        assertEquals("Novo Nome", aluno.getNome());
        assertEquals(25, aluno.getIdade());
        assertEquals("Novo Curso", aluno.getCurso());
        assertEquals(8, aluno.getFase());
    }
}

