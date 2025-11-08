package DAO;

import model.Professor;
import view.TelaLogin;

import java.sql.SQLException;
import java.util.ArrayList;

// Imports do JUnit 5 (Jupiter)
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de INTEGRAÇÃO.
 * Usa a mesma estratégia de AlunoDAOTest, com um ID de teste seguro.
 * NOTA: Estes testes requerem que o MySQL esteja rodando e o banco 'db_escola' esteja criado.
 * Configure as credenciais antes de executar os testes.
 */
@DisplayName("Testes de Integração Seguros do ProfessorDAO (JUnit 5)")
@TestMethodOrder(MethodOrderer.DisplayName.class) // Garante a ordem
class ProfessorDAOTest {

    // IMPORTANTE: Configure com suas credenciais de admin do MySQL
    private static final String TEST_USER = "admin";
    private static final String TEST_PASSWORD = "admin"; // Mude para a sua senha

    private ProfessorDAO professorDAO;
    private static final int ID_TESTE = 8888888; // ID de teste seguro (diferente do Aluno)

    @BeforeAll
    static void configurarCredenciaisGlobais() {
        // Seta as credenciais estáticas que o DAO usa
        TelaLogin.userDB = TEST_USER;
        TelaLogin.passwordDB = TEST_PASSWORD;
    }

    @BeforeEach
    void setUp() {
        professorDAO = new ProfessorDAO();
    }

    @Test
    @DisplayName("1. Deve INSERIR um professor de teste")
    void deveInserirProfessor() throws SQLException {
        Professor profNovo = new Professor(
                "Campus Teste", "123.456.789-00", "9999-9999",
                "Doutorado", 5000.0, ID_TESTE, "Professor Teste JUnit", 45
        );
        boolean inserido = professorDAO.InsertProfessorBD(profNovo);

        assertTrue(inserido, "Falha ao inserir o professor de teste");

        // Verifica se realmente foi salvo
        Professor profCarregado = professorDAO.carregaProfessor(ID_TESTE);
        assertEquals("Professor Teste JUnit", profCarregado.getNome(), "Professor não foi salvo corretamente.");
    }

    @Test
    @DisplayName("2. Deve ATUALIZAR um professor de teste")
    void deveAtualizarProfessor() throws SQLException {
        Professor profAtualizado = new Professor(
                "Campus ATUALIZADO", "000.000.000-00", "1111-1111",
                "Mestrado", 6500.0, ID_TESTE, "Professor Teste Atualizado", 46
        );
        boolean atualizado = professorDAO.UpdateProfessorBD(profAtualizado);

        assertTrue(atualizado, "Falha ao atualizar o professor de teste");

        // Verifica se foi atualizado
        Professor profCarregado = professorDAO.carregaProfessor(ID_TESTE);
        assertEquals("Professor Teste Atualizado", profCarregado.getNome(), "Nome não foi atualizado.");
        assertEquals("Campus ATUALIZADO", profCarregado.getCampus(), "Campus não foi atualizado.");
        assertEquals(6500.0, profCarregado.getSalario(), "Salário não foi atualizado.");
    }

    @Test
    @DisplayName("3. Deve DELETAR um professor de teste")
    void deveDeletarProfessor() throws SQLException {

        boolean deletado = professorDAO.DeleteProfessorBD(ID_TESTE);

        assertTrue(deletado, "Falha ao deletar o professor de teste");

        // Verifica se foi deletado
        Professor profCarregado = professorDAO.carregaProfessor(ID_TESTE);
        assertNull(profCarregado.getNome(), "Professor não foi deletado (ainda existe no banco).");
    }

    @Test
    @DisplayName("4. Deve retornar a lista de professores (sem falhar)")
    void deveRetornarListaDeProfessores() {
        // Apenas verifica se o método roda sem lançar exceção
        ArrayList<Professor> lista = professorDAO.getMinhaLista();
        assertNotNull(lista, "A lista de professores não pode ser nula");
        System.out.println("Lista de professores carregada com " + lista.size() + " registros.");
    }

    @Test
    @DisplayName("5. Deve retornar o maiorID de professor (sem falhar)")
    void deveRetornarMaiorID() throws SQLException {
        // Apenas verifica se o método roda sem lançar exceção
        int maiorID = professorDAO.maiorID();
        assertTrue(maiorID >= 0, "Maior ID deve ser zero ou positivo");
    }
}