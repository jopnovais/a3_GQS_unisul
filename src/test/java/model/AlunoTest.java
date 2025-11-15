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

@DisplayName("Testes Unitários - Aluno")
class AlunoTest {

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
            stmt.execute("DELETE FROM tb_alunos");
        }
    }

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

    @Test
    @DisplayName("Caso 9: Setter de Curso com string vazia - deve aceitar e armazenar")
    void testSetCurso_StringVazia_DeveAceitarEArmazenar() {
        Aluno aluno = new Aluno();
        
        aluno.setCurso("");
        assertEquals("", aluno.getCurso());
    }

    @Test
    @DisplayName("Caso 10: Setter de Fase com valor negativo - deve aceitar e armazenar")
    void testSetFase_ValorNegativo_DeveAceitarEArmazenar() {
        Aluno aluno = new Aluno();
        
        aluno.setFase(-1);
        assertEquals(-1, aluno.getFase());
    }

    @Test
    @DisplayName("Caso 11: Setter de Fase com valor zero - deve aceitar e armazenar")
    void testSetFase_ValorZero_DeveAceitarEArmazenar() {
        Aluno aluno = new Aluno("Teste", 1);
        
        aluno.setFase(0);
        assertEquals(0, aluno.getFase());
    }

    @Test
    @DisplayName("Caso 12: Setter de Fase com valor maior que 10 - deve aceitar e armazenar")
    void testSetFase_ValorMaiorQueDez_DeveAceitarEArmazenar() {
        Aluno aluno = new Aluno();
        
        aluno.setFase(11);
        assertEquals(11, aluno.getFase());
        
        aluno.setFase(100);
        assertEquals(100, aluno.getFase());
    }

    @Test
    @DisplayName("Caso 13: Construtor com valores limites - deve criar instância corretamente")
    void testConstrutor_ValoresLimites_DeveCriarInstanciaCorretamente() {
        Aluno aluno1 = new Aluno("", 0, 0, "", 0);
        assertEquals(0, aluno1.getId());
        assertEquals("", aluno1.getNome());
        assertEquals(0, aluno1.getIdade());
        assertEquals("", aluno1.getCurso());
        assertEquals(0, aluno1.getFase());
        
        Aluno aluno2 = new Aluno("Curso", 10, Integer.MAX_VALUE, "Nome", Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, aluno2.getId());
        assertEquals("Nome", aluno2.getNome());
        assertEquals(Integer.MAX_VALUE, aluno2.getIdade());
        assertEquals("Curso", aluno2.getCurso());
        assertEquals(10, aluno2.getFase());
    }

    @Test
    @DisplayName("Caso 14: Construtor com curso nulo - deve criar instância com curso nulo")
    void testConstrutor_CursoNulo_DeveCriarInstanciaComCursoNulo() {
        Aluno aluno = new Aluno(null, 3, 1, "João", 20);
        
        assertEquals(1, aluno.getId());
        assertEquals("João", aluno.getNome());
        assertEquals(20, aluno.getIdade());
        assertNull(aluno.getCurso());
        assertEquals(3, aluno.getFase());
    }

    @Test
    @DisplayName("Caso 15: Múltiplas alterações sequenciais - deve manter último valor")
    void testMultiplasAlteracoesSequenciais_DeveManterUltimoValor() {
        Aluno aluno = new Aluno();
        
        aluno.setCurso("Curso1");
        aluno.setCurso("Curso2");
        aluno.setCurso("Curso3");
        assertEquals("Curso3", aluno.getCurso());
        
        aluno.setFase(1);
        aluno.setFase(2);
        aluno.setFase(3);
        assertEquals(3, aluno.getFase());
    }

    @Test
    @DisplayName("Caso 16: Verificar independência entre instâncias - cada instância deve manter seus próprios valores")
    void testIndependenciaEntreInstancias_CadaInstanciaDeveManterPropriosValores() {
        Aluno aluno1 = new Aluno("Curso 1", 1, 1, "Aluno 1", 20);
        Aluno aluno2 = new Aluno("Curso 2", 2, 2, "Aluno 2", 21);
        
        assertEquals("Curso 1", aluno1.getCurso());
        assertEquals(1, aluno1.getFase());
        assertEquals("Aluno 1", aluno1.getNome());
        
        assertEquals("Curso 2", aluno2.getCurso());
        assertEquals(2, aluno2.getFase());
        assertEquals("Aluno 2", aluno2.getNome());
        
        aluno1.setCurso("Novo Curso 1");
        aluno2.setCurso("Novo Curso 2");
        
        assertEquals("Novo Curso 1", aluno1.getCurso());
        assertEquals("Novo Curso 2", aluno2.getCurso());
    }

    @Test
    @DisplayName("Caso 17: getMinhaLista - deve retornar ArrayList de alunos")
    void testGetMinhaLista_DeveRetornarArrayListDeAlunos() {
        Aluno aluno = new Aluno();
        
        @SuppressWarnings("rawtypes")
        ArrayList resultado = aluno.getMinhaLista();
        
        assertNotNull(resultado);
        assertTrue(resultado instanceof ArrayList);
    }

    @Test
    @DisplayName("Caso 18: InsertAlunoBD - deve inserir aluno no banco de dados")
    void testInsertAlunoBD_DeveInserirAlunoNoBanco() throws SQLException {
        Aluno aluno = new Aluno();
        
        boolean resultado = aluno.InsertAlunoBD(
            "Ciências da Computação",
            3,
            "João Silva",
            20
        );
        
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Caso 19: InsertAlunoBD com dados válidos - deve criar objeto Aluno corretamente e salvar")
    void testInsertAlunoBD_DadosValidos_DeveCriarObjetoESalvar() throws SQLException {
        Aluno aluno = new Aluno();
        
        boolean resultado = aluno.InsertAlunoBD(
            "Engenharia",
            5,
            "Maria Santos",
            22
        );
        
        assertTrue(resultado);
        
        // Verificar se o aluno foi salvo buscando pelo maior ID
        int maiorId = aluno.maiorID();
        assertTrue(maiorId > 0);
        
        Aluno alunoSalvo = aluno.carregaAluno(maiorId);
        assertNotNull(alunoSalvo);
        assertEquals("Maria Santos", alunoSalvo.getNome());
        assertEquals(22, alunoSalvo.getIdade());
        assertEquals("Engenharia", alunoSalvo.getCurso());
        assertEquals(5, alunoSalvo.getFase());
    }

    @Test
    @DisplayName("Caso 20: DeleteAlunoBD - deve deletar aluno do banco de dados")
    void testDeleteAlunoBD_DeveDeletarAlunoDoBanco() throws SQLException {
        Aluno aluno = new Aluno();
        
        // Primeiro inserir um aluno
        aluno.InsertAlunoBD(
            "Administração",
            2,
            "Teste Delete",
            19
        );
        
        int id = aluno.maiorID();
        
        // Deletar o aluno
        boolean resultado = aluno.DeleteAlunoBD(id);
        
        assertTrue(resultado);
        
        // Verificar se foi deletado
        Aluno alunoDeletado = aluno.carregaAluno(id);
        assertNull(alunoDeletado);
    }

    @Test
    @DisplayName("Caso 21: DeleteAlunoBD com ID inexistente - deve retornar false")
    void testDeleteAlunoBD_IdInexistente_DeveRetornarFalse() {
        Aluno aluno = new Aluno();
        
        boolean resultado = aluno.DeleteAlunoBD(99999);
        
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Caso 22: UpdateAlunoBD - deve atualizar aluno no banco de dados")
    void testUpdateAlunoBD_DeveAtualizarAlunoNoBanco() throws SQLException {
        Aluno aluno = new Aluno();
        
        // Primeiro inserir um aluno
        aluno.InsertAlunoBD(
            "Design",
            1,
            "Teste Update",
            18
        );
        
        int id = aluno.maiorID();
        
        // Atualizar o aluno
        boolean resultado = aluno.UpdateAlunoBD(
            "Design Gráfico",
            4,
            id,
            "Teste Update Modificado",
            20
        );
        
        assertTrue(resultado);
        
        // Verificar se foi atualizado
        Aluno alunoAtualizado = aluno.carregaAluno(id);
        assertNotNull(alunoAtualizado);
        assertEquals("Teste Update Modificado", alunoAtualizado.getNome());
        assertEquals(20, alunoAtualizado.getIdade());
        assertEquals("Design Gráfico", alunoAtualizado.getCurso());
        assertEquals(4, alunoAtualizado.getFase());
    }

    @Test
    @DisplayName("Caso 23: UpdateAlunoBD com ID inexistente - deve retornar false")
    void testUpdateAlunoBD_IdInexistente_DeveRetornarFalse() {
        Aluno aluno = new Aluno();
        
        boolean resultado = aluno.UpdateAlunoBD(
            "Teste",
            1,
            99999,
            "Nome Teste",
            20
        );
        
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Caso 24: carregaAluno - deve retornar aluno existente")
    void testCarregaAluno_AlunoExistente_DeveRetornarAluno() throws SQLException {
        Aluno aluno = new Aluno();
        
        // Primeiro inserir um aluno
        aluno.InsertAlunoBD(
            "Sistemas de Informação",
            6,
            "Teste Carrega",
            23
        );
        
        int id = aluno.maiorID();
        
        // Carregar o aluno
        Aluno alunoCarregado = aluno.carregaAluno(id);
        
        assertNotNull(alunoCarregado);
        assertEquals(id, alunoCarregado.getId());
        assertEquals("Teste Carrega", alunoCarregado.getNome());
        assertEquals(23, alunoCarregado.getIdade());
        assertEquals("Sistemas de Informação", alunoCarregado.getCurso());
    }

    @Test
    @DisplayName("Caso 25: carregaAluno com ID inexistente - deve retornar null")
    void testCarregaAluno_IdInexistente_DeveRetornarNull() {
        Aluno aluno = new Aluno();
        
        Aluno resultado = aluno.carregaAluno(99999);
        
        assertNull(resultado);
    }

    @Test
    @DisplayName("Caso 26: maiorID - deve retornar o maior ID do banco de dados")
    void testMaiorID_DeveRetornarMaiorId() throws SQLException {
        Aluno aluno = new Aluno();
        
        // Limpar tabela primeiro
        limparTabela();
        
        // Inserir alguns alunos
        aluno.InsertAlunoBD("Curso 1", 1, "Aluno 1", 18);
        aluno.InsertAlunoBD("Curso 2", 2, "Aluno 2", 19);
        aluno.InsertAlunoBD("Curso 3", 3, "Aluno 3", 20);
        
        int maiorId = aluno.maiorID();
        
        assertTrue(maiorId > 0);
        
        // Verificar se o último aluno inserido tem esse ID
        Aluno ultimoAluno = aluno.carregaAluno(maiorId);
        assertNotNull(ultimoAluno);
        assertEquals("Aluno 3", ultimoAluno.getNome());
    }

    @Test
    @DisplayName("Caso 27: maiorID com tabela vazia - deve retornar 0")
    void testMaiorID_TabelaVazia_DeveRetornarZero() throws SQLException {
        Aluno aluno = new Aluno();
        
        // Garantir que a tabela está vazia
        limparTabela();
        
        int maiorId = aluno.maiorID();
        
        assertEquals(0, maiorId);
    }

    @Test
    @DisplayName("Caso 28: Fluxo completo - inserir, carregar, atualizar e deletar")
    void testFluxoCompleto_InserirCarregarAtualizarDeletar() throws SQLException {
        Aluno aluno = new Aluno();
        
        // 1. Inserir
        boolean inserido = aluno.InsertAlunoBD(
            "Ciências da Computação",
            3,
            "Fluxo Completo",
            20
        );
        assertTrue(inserido);
        
        // 2. Carregar
        int id = aluno.maiorID();
        Aluno carregado = aluno.carregaAluno(id);
        assertNotNull(carregado);
        assertEquals("Fluxo Completo", carregado.getNome());
        
        // 3. Atualizar
        boolean atualizado = aluno.UpdateAlunoBD(
            "Engenharia de Software",
            7,
            id,
            "Fluxo Completo Atualizado",
            22
        );
        assertTrue(atualizado);
        
        // Verificar atualização
        Aluno atualizadoObj = aluno.carregaAluno(id);
        assertEquals("Fluxo Completo Atualizado", atualizadoObj.getNome());
        assertEquals("Engenharia de Software", atualizadoObj.getCurso());
        assertEquals(7, atualizadoObj.getFase());
        
        // 4. Deletar
        boolean deletado = aluno.DeleteAlunoBD(id);
        assertTrue(deletado);
        
        // Verificar deleção
        Aluno deletadoObj = aluno.carregaAluno(id);
        assertNull(deletadoObj);
    }
}

