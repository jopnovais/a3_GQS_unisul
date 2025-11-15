package repository;

import db.ConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Integração - AbstractRepository")
class AbstractRepositoryTest {

    private TestRepository repository;

    /**
     * Classe concreta para testar os métodos protegidos do AbstractRepository.
     * Expõe os métodos protegidos como públicos para fins de teste.
     */
    private static class TestRepository extends AbstractRepository {
        public int testExecuteMaxIdQuery(String tableName) {
            return executeMaxIdQuery(tableName);
        }
        
        public boolean testExecuteDelete(String tableName, int id) {
            return executeDelete(tableName, id);
        }
    }

    @BeforeEach
    void setUp() throws SQLException {
        repository = new TestRepository();
        criarTabelaTeste();
        limparTabelaAlunos();
    }

    @AfterEach
    void tearDown() throws SQLException {
        limparTabelaTeste();
    }

    private void criarTabelaTeste() throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS tb_teste (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nome TEXT)");
            stmt.execute("DELETE FROM tb_teste");
        }
    }

    private void limparTabelaTeste() throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS tb_teste");
        }
    }

    private void limparTabelaAlunos() throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM tb_alunos");
        }
    }

    @Test
    @DisplayName("Caso 1: executeMaxIdQuery com tabela inválida - deve lançar IllegalArgumentException")
    void testExecuteMaxIdQueryDeveLancarExcecaoComTabelaInvalida() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.testExecuteMaxIdQuery("tabela_que_nao_existe");
        });
        
        assertEquals("Nome de tabela inválido: tabela_que_nao_existe", exception.getMessage());
    }

    @Test
    @DisplayName("Caso 2: executeMaxIdQuery com ResultSet vazio - deve retornar 0")
    void testExecuteMaxIdQueryDeveRetornarZeroParaTabelaVazia() {
        // A tabela tb_alunos está vazia (limpa no setUp)
        int resultado = repository.testExecuteMaxIdQuery("tb_alunos");
        
        assertEquals(0, resultado);
    }

    @Test
    @DisplayName("Caso 3: executeDelete com tabela inválida - deve lançar IllegalArgumentException")
    void testExecuteDeleteDeveLancarExcecaoComTabelaInvalida() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.testExecuteDelete("tabela_invalida", 1);
        });
        
        assertEquals("Nome de tabela inválido: tabela_invalida", exception.getMessage());
    }

    @Test
    @DisplayName("Caso 4: executeMaxIdQuery com tabela tb_professores vazia - deve retornar 0")
    void testExecuteMaxIdQuery_TabelaProfessoresVazia_DeveRetornarZero() throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM tb_professores");
        }
        
        int resultado = repository.testExecuteMaxIdQuery("tb_professores");
        assertEquals(0, resultado);
    }

    @Test
    @DisplayName("Caso 5: executeDelete com ID inexistente - deve retornar false")
    void testExecuteDelete_IdInexistente_DeveRetornarFalse() {
        boolean resultado = repository.testExecuteDelete("tb_alunos", 99999);
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Caso 6: executeMaxIdQuery com buildMaxIdQuery para tabela inválida - deve lançar IllegalArgumentException")
    void testBuildMaxIdQuery_TabelaInvalida_DeveLancarExcecao() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.testExecuteMaxIdQuery("tabela_invalida_2");
        });
        
        assertTrue(exception.getMessage().contains("Nome de tabela inválido"));
    }
}

