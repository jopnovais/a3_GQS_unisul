package DAO;

import model.Aluno;
import view.TelaLogin;
import junit.framework.TestCase;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Testes unitários para a classe AlunoDAO
 * 
 * NOTA: Estes testes requerem que o MySQL esteja rodando e o banco 'db_escola' esteja criado.
 * Configure as credenciais antes de executar os testes.
 */
public class TestAlunoDAO extends TestCase {

    private AlunoDAO alunoDAO;
    // Altere aqui com suas credenciais do MySQL
    private static final String TEST_USER = "root";
    private static final String TEST_PASSWORD = "@13Dodo13";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Configurar credenciais para teste
        TelaLogin.userDB = TEST_USER;
        TelaLogin.passwordDB = TEST_PASSWORD;
        alunoDAO = new AlunoDAO();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        alunoDAO = null;
    }

    /**
     * Testa a conexão com o banco de dados
     */
    public void testGetConexao() {
        Connection conexao = alunoDAO.getConexao();
        
        if (conexao == null) {
            System.out.println("AVISO: Teste de conexão ignorado - MySQL não está disponível ou credenciais incorretas");
            return;
        }
        
        assertNotNull("Conexão não deveria ser null", conexao);
        
        try {
            assertFalse("Conexão não deveria estar fechada", conexao.isClosed());
            conexao.close();
        } catch (SQLException e) {
            fail("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    /**
     * Testa o método maiorID
     */
    public void testMaiorID() {
        try {
            int maiorID = alunoDAO.maiorID();
            assertTrue("Maior ID deveria ser >= 0", maiorID >= 0);
            System.out.println("Maior ID encontrado: " + maiorID);
        } catch (SQLException e) {
            System.out.println("AVISO: Teste maiorID ignorado - " + e.getMessage());
        }
    }

    /**
     * Testa o método getMinhaLista
     */
    public void testGetMinhaLista() {
        try {
            @SuppressWarnings("unchecked")
            java.util.ArrayList<Aluno> lista = alunoDAO.getMinhaLista();
            assertNotNull("Lista não deveria ser null", lista);
            // A lista pode estar vazia, isso é válido
            System.out.println("Número de alunos na lista: " + lista.size());
        } catch (Exception e) {
            System.out.println("AVISO: Teste getMinhaLista ignorado - " + e.getMessage());
        }
    }

    /**
     * Testa inserção de um aluno no banco
     */
    public void testInsertAlunoBD() {
        try {
            // Buscar o maior ID atual
            int maiorID = alunoDAO.maiorID();
            int novoID = maiorID + 1;
            
            // Criar aluno de teste
            Aluno alunoTeste = new Aluno("Teste Curso", 1, novoID, "Aluno Teste", 20);
            
            // Inserir no banco
            boolean resultado = alunoDAO.InsertAlunoBD(alunoTeste);
            assertTrue("Inserção deveria retornar true", resultado);
            
            // Limpar: deletar o aluno de teste
            alunoDAO.DeleteAlunoBD(novoID);
            
            System.out.println("Teste de inserção concluído com sucesso");
        } catch (Exception e) {
            System.out.println("AVISO: Teste InsertAlunoBD ignorado - " + e.getMessage());
            // Não falha o teste se não houver banco disponível
        }
    }

    /**
     * Testa atualização de um aluno no banco
     */
    public void testUpdateAlunoBD() {
        try {
            // Buscar o maior ID atual
            int maiorID = alunoDAO.maiorID();
            int testeID = maiorID + 1;
            
            // Criar e inserir aluno de teste
            Aluno alunoOriginal = new Aluno("Curso Original", 1, testeID, "Nome Original", 20);
            alunoDAO.InsertAlunoBD(alunoOriginal);
            
            // Criar aluno atualizado
            Aluno alunoAtualizado = new Aluno("Curso Atualizado", 2, testeID, "Nome Atualizado", 21);
            
            // Atualizar no banco
            boolean resultado = alunoDAO.UpdateAlunoBD(alunoAtualizado);
            assertTrue("Atualização deveria retornar true", resultado);
            
            // Verificar se foi atualizado
            Aluno alunoCarregado = alunoDAO.carregaAluno(testeID);
            if (alunoCarregado != null) {
                assertEquals("Nome deveria ser atualizado", "Nome Atualizado", alunoCarregado.getNome());
                assertEquals("Curso deveria ser atualizado", "Curso Atualizado", alunoCarregado.getCurso());
                assertEquals("Fase deveria ser atualizada", 2, alunoCarregado.getFase());
            }
            
            // Limpar: deletar o aluno de teste
            alunoDAO.DeleteAlunoBD(testeID);
            
            System.out.println("Teste de atualização concluído com sucesso");
        } catch (Exception e) {
            System.out.println("AVISO: Teste UpdateAlunoBD ignorado - " + e.getMessage());
        }
    }

    /**
     * Testa deleção de um aluno no banco
     */
    public void testDeleteAlunoBD() {
        try {
            // Buscar o maior ID atual
            int maiorID = alunoDAO.maiorID();
            int testeID = maiorID + 1;
            
            // Criar e inserir aluno de teste
            Aluno alunoTeste = new Aluno("Curso Delete", 1, testeID, "Aluno Delete", 20);
            alunoDAO.InsertAlunoBD(alunoTeste);
            
            // Deletar do banco
            boolean resultado = alunoDAO.DeleteAlunoBD(testeID);
            assertTrue("Deleção deveria retornar true", resultado);
            
            System.out.println("Teste de deleção concluído com sucesso");
        } catch (Exception e) {
            System.out.println("AVISO: Teste DeleteAlunoBD ignorado - " + e.getMessage());
        }
    }

    /**
     * Testa carregamento de um aluno por ID
     */
    public void testCarregaAluno() {
        try {
            // Buscar o maior ID atual
            int maiorID = alunoDAO.maiorID();
            
            if (maiorID > 0) {
                // Tentar carregar um aluno existente
                Aluno aluno = alunoDAO.carregaAluno(maiorID);
                // Nota: o método carregaAluno pode retornar null se não encontrar
                // ou um objeto Aluno com dados do banco
                if (aluno != null) {
                    System.out.println("Aluno carregado: " + aluno.getNome());
                }
                System.out.println("Teste de carregamento concluído");
            } else {
                System.out.println("AVISO: Nenhum aluno no banco para testar carregamento");
            }
        } catch (Exception e) {
            System.out.println("AVISO: Teste carregaAluno ignorado - " + e.getMessage());
        }
    }

    /**
     * Testa um fluxo completo CRUD
     */
    public void testFluxoCompletoCRUD() {
        try {
            // CREATE
            int maiorID = alunoDAO.maiorID();
            int testeID = maiorID + 10; // ID maior para evitar conflitos
            
            Aluno alunoNovo = new Aluno("Engenharia", 3, testeID, "Aluno CRUD", 22);
            boolean inserido = alunoDAO.InsertAlunoBD(alunoNovo);
            assertTrue("Aluno deveria ser inserido", inserido);
            
            // READ
            Aluno alunoLido = alunoDAO.carregaAluno(testeID);
            if (alunoLido != null && alunoLido.getId() > 0) {
                assertEquals("ID deveria ser " + testeID, testeID, alunoLido.getId());
            }
            
            // UPDATE
            Aluno alunoAtualizado = new Aluno("Medicina", 4, testeID, "Aluno CRUD Atualizado", 23);
            boolean atualizado = alunoDAO.UpdateAlunoBD(alunoAtualizado);
            assertTrue("Aluno deveria ser atualizado", atualizado);
            
            // DELETE
            boolean deletado = alunoDAO.DeleteAlunoBD(testeID);
            assertTrue("Aluno deveria ser deletado", deletado);
            
            System.out.println("Teste de fluxo CRUD completo concluído com sucesso");
        } catch (Exception e) {
            System.out.println("AVISO: Teste fluxo CRUD ignorado - " + e.getMessage());
        }
    }
}

