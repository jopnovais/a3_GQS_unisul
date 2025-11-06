package DAO;

import model.Aluno;
import view.TelaLogin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

// Imports do JUnit 5 (Jupiter)
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de INTEGRAÇÃO para a classe AlunoDAO.
 * Estes testes rodam contra o banco de dados de produção (db_escola)
 *
 *
 * NOTA: Estes testes requerem que o MySQL esteja rodando e o banco 'db_escola' esteja criado.
 * Configure as credenciais antes de executar os testes.
 */
@DisplayName("Testes de Integração Seguros do AlunoDAO (JUnit 5)")
@TestMethodOrder(MethodOrderer.DisplayName.class) // Garante a ordem dos testes
class AlunoDAOTest {

    // IMPORTANTE: Configure com suas credenciais de admin do MySQL
    private static final String TEST_USER = "root";
    private static final String TEST_PASSWORD = "password"; // Mude para a sua senha

    private AlunoDAO alunoDAO;
    private static final int ID_TESTE = 9999999; // ID de teste seguro

    @BeforeAll
    static void configurarCredenciaisGlobais() {
        // Seta as credenciais estáticas que o DAO usa
        TelaLogin.userDB = TEST_USER;
        TelaLogin.passwordDB = TEST_PASSWORD;
    }

    @BeforeEach
    void setUp() {
        // Instancia um novo DAO para cada teste
        alunoDAO = new AlunoDAO();
    }

    @Test
    @DisplayName("1. Deve conectar ao banco de dados com sucesso")
    void deveConectarComSucesso() throws SQLException {
        // Tenta obter uma conexão
        try (Connection conexao = alunoDAO.getConexao()) {
            assertNotNull(conexao, "Conexão não deveria ser nula");
            assertFalse(conexao.isClosed(), "Conexão não deveria estar fechada");
            System.out.println("Status: Conexão com o banco de teste bem-sucedida.");
        }
    }

    @Test
    @DisplayName("2. Deve INSERIR um aluno de teste")
    void deveInserirAluno() throws SQLException {
        Aluno alunoNovo = new Aluno("Curso Teste", 1, ID_TESTE, "Aluno Teste JUnit", 25);

        boolean inserido = alunoDAO.InsertAlunoBD(alunoNovo);

        assertTrue(inserido, "Falha ao inserir o aluno de teste");

        // Verifica se realmente foi salvo
        Aluno alunoCarregado = alunoDAO.carregaAluno(ID_TESTE);
        assertEquals("Aluno Teste JUnit", alunoCarregado.getNome(), "Aluno não foi salvo corretamente.");
    }

    @Test
    @DisplayName("3. Deve ATUALIZAR um aluno de teste")
    void deveAtualizarAluno() throws SQLException {

        Aluno alunoAtualizado = new Aluno("Curso Teste Atualizado", 2, ID_TESTE, "Aluno Teste Atualizado", 26);

        boolean atualizado = alunoDAO.UpdateAlunoBD(alunoAtualizado);

        assertTrue(atualizado, "Falha ao atualizar o aluno de teste");

        // Verifica se foi atualizado
        Aluno alunoCarregado = alunoDAO.carregaAluno(ID_TESTE);
        assertEquals("Aluno Teste Atualizado", alunoCarregado.getNome(), "Nome não foi atualizado.");
        assertEquals(2, alunoCarregado.getFase(), "Fase não foi atualizada.");
    }

    @Test
    @DisplayName("4. Deve DELETAR um aluno de teste")
    void deveDeletarAluno() throws SQLException {
        boolean deletado = alunoDAO.DeleteAlunoBD(ID_TESTE);

        assertTrue(deletado, "Falha ao deletar o aluno de teste");

        // Verifica se foi deletado
        Aluno alunoCarregado = alunoDAO.carregaAluno(ID_TESTE);
        assertNull(alunoCarregado.getNome(), "Aluno não foi deletado (ainda existe no banco).");
    }

    @Test
    @DisplayName("5. Deve retornar a lista de alunos (sem falhar)")
    void deveRetornarListaDeAlunos() {
        ArrayList<Aluno> lista = alunoDAO.getMinhaLista();
        assertNotNull(lista, "A lista de alunos não pode ser nula");
        System.out.println("Lista de alunos carregada com " + lista.size() + " registros.");
    }

    @Test
    @DisplayName("6. Deve retornar o maiorID (sem falhar)")
    void deveRetornarMaiorID() throws SQLException {
        // Apenas verifica se o método roda sem lançar exceção
        int maiorID = alunoDAO.maiorID();
        assertTrue(maiorID >= 0, "Maior ID deve ser zero ou positivo");
    }
}