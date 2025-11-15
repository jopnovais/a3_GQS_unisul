package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Unitários - Professor")
class ProfessorTest {

    @BeforeEach
    void setUp() throws SQLException {
        limparTabela();
    }

    @AfterEach
    void tearDown() throws SQLException {
        limparTabela();
    }

    private void limparTabela() throws SQLException {
        try (Connection conn = db.ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM tb_professores");
        }
    }

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

    @Test
    @DisplayName("Caso 12: getMinhaLista - deve retornar ArrayList de professores")
    void testGetMinhaLista_DeveRetornarArrayListDeProfessores() {
        Professor professor = new Professor();
        
        @SuppressWarnings("rawtypes")
        ArrayList resultado = professor.getMinhaLista();
        
        assertNotNull(resultado);
        assertTrue(resultado instanceof ArrayList);
    }

    @Test
    @DisplayName("Caso 13: InsertProfessorBD - deve inserir professor no banco de dados")
    void testInsertProfessorBD_DeveInserirProfessorNoBanco() throws SQLException {
        Professor professor = new Professor();
        
        boolean resultado = professor.InsertProfessorBD(
            "Tubarão", 
            "12345678901", 
            "47912345678", 
            "Doutor", 
            5000.0, 
            "João Silva", 
            30
        );
        
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Caso 14: InsertProfessorBD com dados válidos - deve criar objeto Professor corretamente e salvar")
    void testInsertProfessorBD_DadosValidos_DeveCriarObjetoESalvar() throws SQLException {
        Professor professor = new Professor();
        
        boolean resultado = professor.InsertProfessorBD(
            "Florianópolis",
            "98765432109",
            "47987654321",
            "Mestre",
            6000.0,
            "Maria Santos",
            35
        );
        
        assertTrue(resultado);
        
        // Verificar se o professor foi salvo buscando pelo maior ID
        int maiorId = professor.maiorID();
        assertTrue(maiorId > 0);
        
        Professor professorSalvo = professor.carregaProfessor(maiorId);
        assertNotNull(professorSalvo);
        assertEquals("Maria Santos", professorSalvo.getNome());
        assertEquals(35, professorSalvo.getIdade());
        assertEquals("Florianópolis", professorSalvo.getCampus());
        assertEquals("98765432109", professorSalvo.getCpf());
    }

    @Test
    @DisplayName("Caso 15: DeleteProfessorBD - deve deletar professor do banco de dados")
    void testDeleteProfessorBD_DeveDeletarProfessorDoBanco() throws SQLException {
        Professor professor = new Professor();
        
        // Primeiro inserir um professor
        professor.InsertProfessorBD(
            "Tubarão",
            "11111111111",
            "47911111111",
            "Doutor",
            5000.0,
            "Teste Delete",
            25
        );
        
        int id = professor.maiorID();
        
        // Deletar o professor
        boolean resultado = professor.DeleteProfessorBD(id);
        
        assertTrue(resultado);
        
        // Verificar se foi deletado
        Professor professorDeletado = professor.carregaProfessor(id);
        assertNull(professorDeletado);
    }

    @Test
    @DisplayName("Caso 16: DeleteProfessorBD com ID inexistente - deve retornar false")
    void testDeleteProfessorBD_IdInexistente_DeveRetornarFalse() {
        Professor professor = new Professor();
        
        boolean resultado = professor.DeleteProfessorBD(99999);
        
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Caso 17: UpdateProfessorBD - deve atualizar professor no banco de dados")
    void testUpdateProfessorBD_DeveAtualizarProfessorNoBanco() throws SQLException {
        Professor professor = new Professor();
        
        // Primeiro inserir um professor
        professor.InsertProfessorBD(
            "Tubarão",
            "22222222222",
            "47922222222",
            "Doutor",
            5000.0,
            "Teste Update",
            30
        );
        
        int id = professor.maiorID();
        
        // Atualizar o professor
        boolean resultado = professor.UpdateProfessorBD(
            "Florianópolis",
            "22222222222",
            "47933333333",
            "Mestre",
            7000.0,
            id,
            "Teste Update Modificado",
            35
        );
        
        assertTrue(resultado);
        
        // Verificar se foi atualizado
        Professor professorAtualizado = professor.carregaProfessor(id);
        assertNotNull(professorAtualizado);
        assertEquals("Teste Update Modificado", professorAtualizado.getNome());
        assertEquals(35, professorAtualizado.getIdade());
        assertEquals("Florianópolis", professorAtualizado.getCampus());
        assertEquals(7000.0, professorAtualizado.getSalario(), 0.01);
    }

    @Test
    @DisplayName("Caso 18: UpdateProfessorBD com ID inexistente - deve retornar false")
    void testUpdateProfessorBD_IdInexistente_DeveRetornarFalse() {
        Professor professor = new Professor();
        
        boolean resultado = professor.UpdateProfessorBD(
            "Tubarão",
            "33333333333",
            "47944444444",
            "Doutor",
            5000.0,
            99999,
            "Nome Teste",
            30
        );
        
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Caso 19: carregaProfessor - deve retornar professor existente")
    void testCarregaProfessor_ProfessorExistente_DeveRetornarProfessor() throws SQLException {
        Professor professor = new Professor();
        
        // Primeiro inserir um professor
        professor.InsertProfessorBD(
            "Tubarão",
            "44444444444",
            "47955555555",
            "Doutor",
            5000.0,
            "Teste Carrega",
            28
        );
        
        int id = professor.maiorID();
        
        // Carregar o professor
        Professor professorCarregado = professor.carregaProfessor(id);
        
        assertNotNull(professorCarregado);
        assertEquals(id, professorCarregado.getId());
        assertEquals("Teste Carrega", professorCarregado.getNome());
        assertEquals(28, professorCarregado.getIdade());
        assertEquals("Tubarão", professorCarregado.getCampus());
    }

    @Test
    @DisplayName("Caso 20: carregaProfessor com ID inexistente - deve retornar null")
    void testCarregaProfessor_IdInexistente_DeveRetornarNull() {
        Professor professor = new Professor();
        
        Professor resultado = professor.carregaProfessor(99999);
        
        assertNull(resultado);
    }

    @Test
    @DisplayName("Caso 21: maiorID - deve retornar o maior ID do banco de dados")
    void testMaiorID_DeveRetornarMaiorId() throws SQLException {
        Professor professor = new Professor();
        
        // Limpar tabela primeiro
        limparTabela();
        
        // Inserir alguns professores
        professor.InsertProfessorBD("Tubarão", "55555555555", "47966666666", "Doutor", 5000.0, "Professor 1", 30);
        professor.InsertProfessorBD("Florianópolis", "66666666666", "47977777777", "Mestre", 6000.0, "Professor 2", 35);
        professor.InsertProfessorBD("Araranguá", "77777777777", "47988888888", "Doutor", 7000.0, "Professor 3", 40);
        
        int maiorId = professor.maiorID();
        
        assertTrue(maiorId > 0);
        
        // Verificar se o último professor inserido tem esse ID
        Professor ultimoProfessor = professor.carregaProfessor(maiorId);
        assertNotNull(ultimoProfessor);
        assertEquals("Professor 3", ultimoProfessor.getNome());
    }

    @Test
    @DisplayName("Caso 22: maiorID com tabela vazia - deve retornar 0")
    void testMaiorID_TabelaVazia_DeveRetornarZero() throws SQLException {
        Professor professor = new Professor();
        
        // Garantir que a tabela está vazia
        limparTabela();
        
        int maiorId = professor.maiorID();
        
        assertEquals(0, maiorId);
    }

    @Test
    @DisplayName("Caso 23: Fluxo completo - inserir, carregar, atualizar e deletar")
    void testFluxoCompleto_InserirCarregarAtualizarDeletar() throws SQLException {
        Professor professor = new Professor();
        
        // 1. Inserir
        boolean inserido = professor.InsertProfessorBD(
            "Tubarão",
            "88888888888",
            "47999999999",
            "Doutor",
            5000.0,
            "Fluxo Completo",
            30
        );
        assertTrue(inserido);
        
        // 2. Carregar
        int id = professor.maiorID();
        Professor carregado = professor.carregaProfessor(id);
        assertNotNull(carregado);
        assertEquals("Fluxo Completo", carregado.getNome());
        
        // 3. Atualizar
        boolean atualizado = professor.UpdateProfessorBD(
            "Florianópolis",
            "88888888888",
            "47999999999",
            "Mestre",
            8000.0,
            id,
            "Fluxo Completo Atualizado",
            35
        );
        assertTrue(atualizado);
        
        // Verificar atualização
        Professor atualizadoObj = professor.carregaProfessor(id);
        assertEquals("Fluxo Completo Atualizado", atualizadoObj.getNome());
        assertEquals(8000.0, atualizadoObj.getSalario(), 0.01);
        
        // 4. Deletar
        boolean deletado = professor.DeleteProfessorBD(id);
        assertTrue(deletado);
        
        // Verificar deleção
        Professor deletadoObj = professor.carregaProfessor(id);
        assertNull(deletadoObj);
    }
}

