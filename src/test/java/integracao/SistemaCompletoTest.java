package integracao;

import model.Aluno;
import model.Professor;
import DAO.AlunoDAO;
import DAO.ProfessorDAO;
import view.TelaLogin;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Testes de Sistema Completo (End-to-End).
 * Estes testes validam o fluxo completo do sistema, desde o cadastro até a exclusão,
 * passando por consulta e atualização, simulando o uso real da aplicação.
 * 
 * NOTA: Estes testes requerem que o MySQL esteja rodando e o banco 'db_escola' esteja criado.
 */
@DisplayName("Testes de Sistema Completo - Fluxo End-to-End")
@TestMethodOrder(MethodOrderer.DisplayName.class)
class SistemaCompletoTest {

    // IMPORTANTE: Configure com suas credenciais do MySQL
    private static final String TEST_USER = "admin";
    private static final String TEST_PASSWORD = "admin";

    // IDs de teste seguros (não devem conflitar com dados reais)
    private static final int ID_ALUNO_TESTE = 9999998;
    private static final int ID_PROFESSOR_TESTE = 9999998;

    private AlunoDAO alunoDAO;
    private ProfessorDAO professorDAO;

    @BeforeAll
    static void configurarCredenciaisGlobais() {
        TelaLogin.userDB = TEST_USER;
        TelaLogin.passwordDB = TEST_PASSWORD;
    }

    @BeforeEach
    void setUp() {
        alunoDAO = new AlunoDAO();
        professorDAO = new ProfessorDAO();
    }

    // ========== TESTES DE SISTEMA COMPLETO - ALUNOS ==========

    @Test
    @DisplayName("1. Sistema Completo: Cadastrar, Consultar, Atualizar e Deletar Aluno")
    void sistemaCompletoAluno() throws SQLException {
        System.out.println("\n=== TESTE DE SISTEMA COMPLETO - ALUNO ===");

        // ETAPA 1: CADASTRAR ALUNO
        System.out.println("Etapa 1: Cadastrando aluno...");
        Aluno alunoNovo = new Aluno("Ciências da Computação", 3, ID_ALUNO_TESTE, "Aluno Teste Sistema", 22);
        boolean inserido = alunoDAO.InsertAlunoBD(alunoNovo);
        assertTrue(inserido, "Falha ao inserir aluno no sistema");
        System.out.println("✓ Aluno cadastrado com sucesso");

        // ETAPA 2: CONSULTAR ALUNO CADASTRADO
        System.out.println("Etapa 2: Consultando aluno cadastrado...");
        Aluno alunoConsultado = alunoDAO.carregaAluno(ID_ALUNO_TESTE);
        assertNotNull(alunoConsultado, "Aluno não deveria ser nulo");
        assertEquals("Aluno Teste Sistema", alunoConsultado.getNome(), "Nome do aluno não corresponde");
        assertEquals("Ciências da Computação", alunoConsultado.getCurso(), "Curso do aluno não corresponde");
        assertEquals(3, alunoConsultado.getFase(), "Fase do aluno não corresponde");
        assertEquals(22, alunoConsultado.getIdade(), "Idade do aluno não corresponde");
        System.out.println("✓ Aluno consultado com sucesso");

        // ETAPA 3: LISTAR TODOS OS ALUNOS
        System.out.println("Etapa 3: Listando todos os alunos...");
        @SuppressWarnings("unchecked")
        ArrayList<Aluno> listaAlunos = alunoDAO.getMinhaLista();
        assertNotNull(listaAlunos, "Lista de alunos não deveria ser nula");
        assertTrue(listaAlunos.size() > 0, "Lista de alunos deveria conter pelo menos o aluno cadastrado");
        
        // Verifica se o aluno cadastrado está na lista
        boolean alunoEncontrado = false;
        for (Aluno a : listaAlunos) {
            if (a.getId() == ID_ALUNO_TESTE) {
                alunoEncontrado = true;
                break;
            }
        }
        assertTrue(alunoEncontrado, "Aluno cadastrado deveria estar na lista");
        System.out.println("✓ Lista de alunos obtida com sucesso (" + listaAlunos.size() + " alunos)");

        // ETAPA 4: ATUALIZAR ALUNO
        System.out.println("Etapa 4: Atualizando aluno...");
        Aluno alunoAtualizado = new Aluno("Sistemas de Informação", 4, ID_ALUNO_TESTE, "Aluno Teste Atualizado", 23);
        boolean atualizado = alunoDAO.UpdateAlunoBD(alunoAtualizado);
        assertTrue(atualizado, "Falha ao atualizar aluno no sistema");
        
        // Verifica se foi atualizado
        Aluno alunoAposAtualizacao = alunoDAO.carregaAluno(ID_ALUNO_TESTE);
        assertEquals("Aluno Teste Atualizado", alunoAposAtualizacao.getNome(), "Nome não foi atualizado");
        assertEquals("Sistemas de Informação", alunoAposAtualizacao.getCurso(), "Curso não foi atualizado");
        assertEquals(4, alunoAposAtualizacao.getFase(), "Fase não foi atualizada");
        assertEquals(23, alunoAposAtualizacao.getIdade(), "Idade não foi atualizada");
        System.out.println("✓ Aluno atualizado com sucesso");

        // ETAPA 5: DELETAR ALUNO
        System.out.println("Etapa 5: Deletando aluno...");
        boolean deletado = alunoDAO.DeleteAlunoBD(ID_ALUNO_TESTE);
        assertTrue(deletado, "Falha ao deletar aluno do sistema");
        
        // Verifica se foi deletado (carregaAluno retorna objeto vazio se não encontrar)
        Aluno alunoAposDelecao = alunoDAO.carregaAluno(ID_ALUNO_TESTE);
        // Se o nome for null ou vazio, significa que foi deletado
        assertTrue(alunoAposDelecao.getNome() == null || alunoAposDelecao.getNome().isEmpty(), 
                   "Aluno não foi deletado (ainda existe no banco)");
        System.out.println("✓ Aluno deletado com sucesso");

        System.out.println("=== TESTE DE SISTEMA COMPLETO - ALUNO: CONCLUÍDO ===\n");
    }

    // ========== TESTES DE SISTEMA COMPLETO - PROFESSORES ==========

    @Test
    @DisplayName("2. Sistema Completo: Cadastrar, Consultar, Atualizar e Deletar Professor")
    void sistemaCompletoProfessor() throws SQLException {
        System.out.println("\n=== TESTE DE SISTEMA COMPLETO - PROFESSOR ===");

        // ETAPA 1: CADASTRAR PROFESSOR
        System.out.println("Etapa 1: Cadastrando professor...");
        Professor professorNovo = new Professor(
            "Pedra Branca", 
            "123.456.789-09", 
            "(48) 99999-9999", 
            "Mestrado", 
            5000.0, 
            ID_PROFESSOR_TESTE, 
            "Professor Teste Sistema", 
            35
        );
        boolean inserido = professorDAO.InsertProfessorBD(professorNovo);
        assertTrue(inserido, "Falha ao inserir professor no sistema");
        System.out.println("✓ Professor cadastrado com sucesso");

        // ETAPA 2: CONSULTAR PROFESSOR CADASTRADO
        System.out.println("Etapa 2: Consultando professor cadastrado...");
        Professor professorConsultado = professorDAO.carregaProfessor(ID_PROFESSOR_TESTE);
        assertNotNull(professorConsultado, "Professor não deveria ser nulo");
        assertEquals("Professor Teste Sistema", professorConsultado.getNome(), "Nome do professor não corresponde");
        assertEquals("Pedra Branca", professorConsultado.getCampus(), "Campus do professor não corresponde");
        assertEquals("123.456.789-09", professorConsultado.getCpf(), "CPF do professor não corresponde");
        assertEquals(35, professorConsultado.getIdade(), "Idade do professor não corresponde");
        assertEquals(5000.0, professorConsultado.getSalario(), 0.01, "Salário do professor não corresponde");
        System.out.println("✓ Professor consultado com sucesso");

        // ETAPA 3: LISTAR TODOS OS PROFESSORES
        System.out.println("Etapa 3: Listando todos os professores...");
        @SuppressWarnings("unchecked")
        ArrayList<Professor> listaProfessores = professorDAO.getMinhaLista();
        assertNotNull(listaProfessores, "Lista de professores não deveria ser nula");
        assertTrue(listaProfessores.size() > 0, "Lista de professores deveria conter pelo menos o professor cadastrado");
        
        // Verifica se o professor cadastrado está na lista
        boolean professorEncontrado = false;
        for (Professor p : listaProfessores) {
            if (p.getId() == ID_PROFESSOR_TESTE) {
                professorEncontrado = true;
                break;
            }
        }
        assertTrue(professorEncontrado, "Professor cadastrado deveria estar na lista");
        System.out.println("✓ Lista de professores obtida com sucesso (" + listaProfessores.size() + " professores)");

        // ETAPA 4: ATUALIZAR PROFESSOR
        System.out.println("Etapa 4: Atualizando professor...");
        Professor professorAtualizado = new Professor(
            "Tubarão", 
            "123.456.789-09", 
            "(48) 88888-8888", 
            "Doutorado", 
            6000.0, 
            ID_PROFESSOR_TESTE, 
            "Professor Teste Atualizado", 
            36
        );
        boolean atualizado = professorDAO.UpdateProfessorBD(professorAtualizado);
        assertTrue(atualizado, "Falha ao atualizar professor no sistema");
        
        // Verifica se foi atualizado
        Professor professorAposAtualizacao = professorDAO.carregaProfessor(ID_PROFESSOR_TESTE);
        assertEquals("Professor Teste Atualizado", professorAposAtualizacao.getNome(), "Nome não foi atualizado");
        assertEquals("Tubarão", professorAposAtualizacao.getCampus(), "Campus não foi atualizado");
        assertEquals("Doutorado", professorAposAtualizacao.getTitulo(), "Título não foi atualizado");
        assertEquals(6000.0, professorAposAtualizacao.getSalario(), 0.01, "Salário não foi atualizado");
        System.out.println("✓ Professor atualizado com sucesso");

        // ETAPA 5: DELETAR PROFESSOR
        System.out.println("Etapa 5: Deletando professor...");
        boolean deletado = professorDAO.DeleteProfessorBD(ID_PROFESSOR_TESTE);
        assertTrue(deletado, "Falha ao deletar professor do sistema");
        
        // Verifica se foi deletado (carregaProfessor retorna objeto vazio se não encontrar)
        Professor professorAposDelecao = professorDAO.carregaProfessor(ID_PROFESSOR_TESTE);
        // Se o nome for null ou vazio, significa que foi deletado
        assertTrue(professorAposDelecao.getNome() == null || professorAposDelecao.getNome().isEmpty(), 
                   "Professor não foi deletado (ainda existe no banco)");
        System.out.println("✓ Professor deletado com sucesso");

        System.out.println("=== TESTE DE SISTEMA COMPLETO - PROFESSOR: CONCLUÍDO ===\n");
    }

    // ========== TESTES DE INTEGRAÇÃO ENTRE MÓDULOS ==========

    @Test
    @DisplayName("3. Integração: Cadastrar múltiplos alunos e verificar consistência")
    void integracaoMultiplosAlunos() throws SQLException {
        System.out.println("\n=== TESTE DE INTEGRAÇÃO - MÚLTIPLOS ALUNOS ===");

        int[] idsTeste = {9999990, 9999991, 9999992};

        // Cadastra múltiplos alunos
        for (int i = 0; i < idsTeste.length; i++) {
            Aluno aluno = new Aluno("Curso Teste " + i, i + 1, idsTeste[i], "Aluno " + i, 20 + i);
            assertTrue(alunoDAO.InsertAlunoBD(aluno), "Falha ao inserir aluno " + i);
        }

        // Verifica se todos foram cadastrados
        @SuppressWarnings("unchecked")
        ArrayList<Aluno> lista = alunoDAO.getMinhaLista();
        assertTrue(lista.size() >= idsTeste.length, "Todos os alunos deveriam estar na lista");
        for (int id : idsTeste) {
            Aluno aluno = alunoDAO.carregaAluno(id);
            assertNotNull(aluno.getNome(), "Aluno com ID " + id + " deveria existir");
        }

        // Limpa os dados de teste
        for (int id : idsTeste) {
            alunoDAO.DeleteAlunoBD(id);
        }

        System.out.println("✓ Integração de múltiplos alunos testada com sucesso\n");
    }

    @Test
    @DisplayName("4. Integração: Verificar maior ID após inserções")
    void integracaoMaiorID() throws SQLException {
        System.out.println("\n=== TESTE DE INTEGRAÇÃO - MAIOR ID ===");

        int maiorIDInicial = alunoDAO.maiorID();
        System.out.println("Maior ID inicial: " + maiorIDInicial);

        // Insere um aluno de teste
        Aluno aluno = new Aluno("Curso Teste", 1, ID_ALUNO_TESTE, "Aluno Teste ID", 25);
        alunoDAO.InsertAlunoBD(aluno);

        // Verifica se o maior ID foi atualizado
        int maiorIDFinal = alunoDAO.maiorID();
        assertTrue(maiorIDFinal >= ID_ALUNO_TESTE, "Maior ID deveria ser pelo menos " + ID_ALUNO_TESTE);

        // Limpa
        alunoDAO.DeleteAlunoBD(ID_ALUNO_TESTE);

        System.out.println("✓ Maior ID verificado com sucesso\n");
    }

    @Test
    @DisplayName("5. Integração: Testar operações CRUD completas em sequência")
    void integracaoCRUDCompleto() throws SQLException {
        System.out.println("\n=== TESTE DE INTEGRAÇÃO - CRUD COMPLETO ===");

        // CREATE
        Aluno aluno = new Aluno("Administração", 2, ID_ALUNO_TESTE, "Aluno CRUD", 21);
        assertTrue(alunoDAO.InsertAlunoBD(aluno), "CREATE falhou");

        // READ
        Aluno alunoLido = alunoDAO.carregaAluno(ID_ALUNO_TESTE);
        assertNotNull(alunoLido.getNome(), "READ falhou");

        // UPDATE
        Aluno alunoAtualizado = new Aluno("Design", 3, ID_ALUNO_TESTE, "Aluno CRUD Atualizado", 22);
        assertTrue(alunoDAO.UpdateAlunoBD(alunoAtualizado), "UPDATE falhou");

        // Verifica UPDATE
        Aluno alunoAposUpdate = alunoDAO.carregaAluno(ID_ALUNO_TESTE);
        assertEquals("Aluno CRUD Atualizado", alunoAposUpdate.getNome(), "UPDATE não funcionou corretamente");

        // DELETE
        assertTrue(alunoDAO.DeleteAlunoBD(ID_ALUNO_TESTE), "DELETE falhou");

        // Verifica DELETE
        Aluno alunoAposDelete = alunoDAO.carregaAluno(ID_ALUNO_TESTE);
        assertTrue(alunoAposDelete.getNome() == null || alunoAposDelete.getNome().isEmpty(), 
                   "DELETE não funcionou corretamente");

        System.out.println("✓ CRUD completo testado com sucesso\n");
    }

    @Test
    @DisplayName("6. Integração: Testar persistência de dados entre conexões")
    void integracaoPersistenciaDados() throws SQLException {
        System.out.println("\n=== TESTE DE INTEGRAÇÃO - PERSISTÊNCIA ===");

        // Cria um aluno
        Aluno aluno = new Aluno("Arquitetura", 5, ID_ALUNO_TESTE, "Aluno Persistência", 24);
        alunoDAO.InsertAlunoBD(aluno);

        // Cria uma nova instância do DAO (simula nova conexão)
        AlunoDAO novoDAO = new AlunoDAO();

        // Verifica se o aluno persiste
        Aluno alunoPersistido = novoDAO.carregaAluno(ID_ALUNO_TESTE);
        assertNotNull(alunoPersistido.getNome(), "Dados não persistiram entre conexões");
        assertEquals("Aluno Persistência", alunoPersistido.getNome(), "Nome não persistiu corretamente");

        // Limpa
        novoDAO.DeleteAlunoBD(ID_ALUNO_TESTE);

        System.out.println("✓ Persistência de dados testada com sucesso\n");
    }
}

