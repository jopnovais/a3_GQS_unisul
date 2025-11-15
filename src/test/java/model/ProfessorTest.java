package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários - Professor")
class ProfessorTest {

    @Test
    @DisplayName("Caso 1: Criar Professor com construtor padrão - deve criar instância com valores padrão")
    void testConstrutorPadrao_DeveCriarInstanciaComValoresPadrao() {
        Professor professor = new Professor();
        
        assertNull(professor.getNome());
        assertEquals(0, professor.getIdade());
        assertNull(professor.getCampus());
        assertNull(professor.getCpf());
        assertNull(professor.getContato());
        assertNull(professor.getTitulo());
        assertEquals(0.0, professor.getSalario(), 0.01);
    }

    @Test
    @DisplayName("Caso 2: Criar Professor com construtor de atributos específicos - deve criar instância com valores fornecidos")
    void testConstrutorAtributosEspecificos_DeveCriarInstanciaComValoresFornecidos() {
        Professor professor = new Professor("Tubarão", "12345678901", "47912345678", "Doutor", 5000.0);
        
        assertEquals("Tubarão", professor.getCampus());
        assertEquals("12345678901", professor.getCpf());
        assertEquals("47912345678", professor.getContato());
        assertEquals("Doutor", professor.getTitulo());
        assertEquals(5000.0, professor.getSalario(), 0.01);
    }

    @Test
    @DisplayName("Caso 3: Criar Professor com construtor completo - deve criar instância com todos os valores")
    void testConstrutorCompleto_DeveCriarInstanciaComTodosValores() {
        Professor professor = new Professor("Tubarão", "12345678901", "47912345678", "Doutor", 5000.0, 1, "João Silva", 30);
        
        assertEquals(1, professor.getId());
        assertEquals("João Silva", professor.getNome());
        assertEquals(30, professor.getIdade());
        assertEquals("Tubarão", professor.getCampus());
        assertEquals("12345678901", professor.getCpf());
        assertEquals("47912345678", professor.getContato());
        assertEquals("Doutor", professor.getTitulo());
        assertEquals(5000.0, professor.getSalario(), 0.01);
    }

    @Test
    @DisplayName("Caso 4: Setter e Getter de Campus - deve definir e retornar campus corretamente")
    void testSetCampusEGetCampus_DeveDefinirERetornarCampusCorretamente() {
        Professor professor = new Professor();
        
        professor.setCampus("Tubarão");
        assertEquals("Tubarão", professor.getCampus());
        
        professor.setCampus("Florianópolis");
        assertEquals("Florianópolis", professor.getCampus());
        
        professor.setCampus(null);
        assertNull(professor.getCampus());
    }

    @Test
    @DisplayName("Caso 5: Setter e Getter de CPF - deve definir e retornar CPF corretamente")
    void testSetCpfEGetCpf_DeveDefinirERetornarCpfCorretamente() {
        Professor professor = new Professor();
        
        professor.setCpf("12345678901");
        assertEquals("12345678901", professor.getCpf());
        
        professor.setCpf("98765432109");
        assertEquals("98765432109", professor.getCpf());
        
        professor.setCpf(null);
        assertNull(professor.getCpf());
    }

    @Test
    @DisplayName("Caso 6: Setter e Getter de Contato - deve definir e retornar contato corretamente")
    void testSetContatoEGetContato_DeveDefinirERetornarContatoCorretamente() {
        Professor professor = new Professor();
        
        professor.setContato("47912345678");
        assertEquals("47912345678", professor.getContato());
        
        professor.setContato("47987654321");
        assertEquals("47987654321", professor.getContato());
        
        professor.setContato(null);
        assertNull(professor.getContato());
    }

    @Test
    @DisplayName("Caso 7: Setter e Getter de Título - deve definir e retornar título corretamente")
    void testSetTituloEGetTitulo_DeveDefinirERetornarTituloCorretamente() {
        Professor professor = new Professor();
        
        professor.setTitulo("Doutor");
        assertEquals("Doutor", professor.getTitulo());
        
        professor.setTitulo("Mestre");
        assertEquals("Mestre", professor.getTitulo());
        
        professor.setTitulo(null);
        assertNull(professor.getTitulo());
    }

    @Test
    @DisplayName("Caso 8: Setter e Getter de Salário - deve definir e retornar salário corretamente")
    void testSetSalarioEGetSalario_DeveDefinirERetornarSalarioCorretamente() {
        Professor professor = new Professor();
        
        professor.setSalario(5000.0);
        assertEquals(5000.0, professor.getSalario(), 0.01);
        
        professor.setSalario(7500.50);
        assertEquals(7500.50, professor.getSalario(), 0.01);
        
        professor.setSalario(0.0);
        assertEquals(0.0, professor.getSalario(), 0.01);
    }

    @Test
    @DisplayName("Caso 9: toString - deve retornar string formatada corretamente")
    void testToString_DeveRetornarStringFormatadaCorretamente() {
        Professor professor = new Professor("Tubarão", "12345678901", "47912345678", "Doutor", 5000.0, 1, "João Silva", 30);
        
        String resultado = professor.toString();
        
        assertNotNull(resultado);
        assertTrue(resultado.contains("ID: 1"));
        assertTrue(resultado.contains("Nome: João Silva"));
        assertTrue(resultado.contains("Idade: 30"));
        assertTrue(resultado.contains("Campus: Tubarão"));
        assertTrue(resultado.contains("CPF:12345678901"));
        assertTrue(resultado.contains("Contato:47912345678"));
        assertTrue(resultado.contains("Título:Doutor"));
        assertTrue(resultado.contains("Salário:5000.0"));
    }

    @Test
    @DisplayName("Caso 10: Herdar métodos de Pessoa - deve funcionar corretamente")
    void testHerancaDePessoa_DeveFuncionarCorretamente() {
        Professor professor = new Professor();
        
        professor.setId(100);
        professor.setNome("Maria Santos");
        professor.setIdade(35);
        
        assertEquals(100, professor.getId());
        assertEquals("Maria Santos", professor.getNome());
        assertEquals(35, professor.getIdade());
    }

    @Test
    @DisplayName("Caso 11: Modificar todos os atributos - deve manter valores corretos")
    void testModificarTodosAtributos_DeveManterValoresCorretos() {
        Professor professor = new Professor("Campus Inicial", "11111111111", "47911111111", "Título Inicial", 1000.0, 1, "Nome Inicial", 25);
        
        professor.setId(200);
        professor.setNome("Novo Nome");
        professor.setIdade(40);
        professor.setCampus("Novo Campus");
        professor.setCpf("22222222222");
        professor.setContato("47922222222");
        professor.setTitulo("Novo Título");
        professor.setSalario(8000.0);
        
        assertEquals(200, professor.getId());
        assertEquals("Novo Nome", professor.getNome());
        assertEquals(40, professor.getIdade());
        assertEquals("Novo Campus", professor.getCampus());
        assertEquals("22222222222", professor.getCpf());
        assertEquals("47922222222", professor.getContato());
        assertEquals("Novo Título", professor.getTitulo());
        assertEquals(8000.0, professor.getSalario(), 0.01);
    }
}

