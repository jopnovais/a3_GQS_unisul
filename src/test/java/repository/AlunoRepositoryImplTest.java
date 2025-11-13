package repository;

import db.ConnectionFactory;
import model.Aluno;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Integração - AlunoRepositoryImpl")
class AlunoRepositoryImplTest {

    private AlunoRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        repository = new AlunoRepositoryImpl();
        limparTabela();
    }

    @AfterEach
    void tearDown() throws SQLException {
        limparTabela();
    }

    private void limparTabela() throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM tb_alunos");
        }
    }

    @Test
    @DisplayName("Caso 1: Salvar um aluno novo e válido - deve retornar aluno com id gerado")
    void testSave_AlunoValido_DeveRetornarAlunoComIdGerado() {
        Aluno aluno = new Aluno();
        aluno.setNome("João Silva");
        aluno.setIdade(20);
        aluno.setCurso("Ciências da Computação");
        aluno.setFase(3);

        boolean resultado = repository.save(aluno);
        
        assertTrue(resultado);
        
        int maxId = repository.getMaxId();
        assertTrue(maxId > 0, "ID gerado deve ser maior que zero");
        
        Aluno alunoSalvo = repository.findById(maxId);
        assertNotNull(alunoSalvo);
        assertEquals("João Silva", alunoSalvo.getNome());
        assertEquals(20, alunoSalvo.getIdade());
        assertEquals("Ciências da Computação", alunoSalvo.getCurso());
        assertEquals(3, alunoSalvo.getFase());
    }

    @Test
    @DisplayName("Caso 2: Tentar salvar aluno com campo nome nulo - deve salvar mas com nome null")
    void testSave_AlunoComNomeNulo_DeveSalvarComNomeNull() {
        Aluno aluno = new Aluno();
        aluno.setNome(null);
        aluno.setIdade(20);
        aluno.setCurso("Ciências da Computação");
        aluno.setFase(3);

        boolean resultado = repository.save(aluno);
        assertTrue(resultado);
        
        int maxId = repository.getMaxId();
        Aluno alunoSalvo = repository.findById(maxId);
        assertNotNull(alunoSalvo);
        assertNull(alunoSalvo.getNome());
        assertEquals(20, alunoSalvo.getIdade());
    }

    @Test
    @DisplayName("Caso 3: Salvar dois alunos diferentes - deve salvar ambos com sucesso")
    void testSave_DoisAlunosDiferentes_DeveSalvarAmbos() {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Maria Santos");
        aluno1.setIdade(22);
        aluno1.setCurso("Administração");
        aluno1.setFase(5);

        boolean resultado1 = repository.save(aluno1);
        assertTrue(resultado1);

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Maria Silva");
        aluno2.setIdade(23);
        aluno2.setCurso("Design");
        aluno2.setFase(4);

        boolean resultado2 = repository.save(aluno2);
        assertTrue(resultado2);

        List<Aluno> todos = repository.findAll();
        assertEquals(2, todos.size(), "Deve ter 2 alunos salvos");
    }

    @Test
    @DisplayName("Caso 4: Buscar aluno por ID que existe - deve retornar Optional com aluno")
    void testFindById_IdExistente_DeveRetornarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Pedro Oliveira");
        aluno.setIdade(21);
        aluno.setCurso("Sistemas de Informação");
        aluno.setFase(2);

        repository.save(aluno);
        int idGerado = repository.getMaxId();

        Aluno alunoEncontrado = repository.findById(idGerado);

        assertNotNull(alunoEncontrado);
        assertEquals(idGerado, alunoEncontrado.getId());
        assertEquals("Pedro Oliveira", alunoEncontrado.getNome());
        assertEquals(21, alunoEncontrado.getIdade());
        assertEquals("Sistemas de Informação", alunoEncontrado.getCurso());
        assertEquals(2, alunoEncontrado.getFase());
    }

    @Test
    @DisplayName("Caso 5: Buscar aluno por ID que não existe - deve retornar null")
    void testFindById_IdInexistente_DeveRetornarNull() {
        Aluno alunoEncontrado = repository.findById(999);

        assertNull(alunoEncontrado);
    }

    @Test
    @DisplayName("Caso 6: Listar todos os alunos quando banco está vazio - deve retornar lista vazia")
    void testFindAll_BancoVazio_DeveRetornarListaVazia() {
        List<Aluno> alunos = repository.findAll();

        assertNotNull(alunos);
        assertEquals(0, alunos.size());
        assertTrue(alunos.isEmpty());
    }

    @Test
    @DisplayName("Caso 7: Listar todos os alunos após salvar 3 alunos - deve retornar lista com 3 alunos")
    void testFindAll_AposSalvar3Alunos_DeveRetornarListaCom3Alunos() {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Ana Costa");
        aluno1.setIdade(19);
        aluno1.setCurso("Design");
        aluno1.setFase(1);
        repository.save(aluno1);

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Carlos Mendes");
        aluno2.setIdade(24);
        aluno2.setCurso("Arquitetura e Urbanismo");
        aluno2.setFase(6);
        repository.save(aluno2);

        Aluno aluno3 = new Aluno();
        aluno3.setNome("Fernanda Lima");
        aluno3.setIdade(20);
        aluno3.setCurso("Relações Internacionais");
        aluno3.setFase(4);
        repository.save(aluno3);

        List<Aluno> alunos = repository.findAll();

        assertNotNull(alunos);
        assertEquals(3, alunos.size());
        assertEquals("Ana Costa", alunos.get(0).getNome());
        assertEquals("Carlos Mendes", alunos.get(1).getNome());
        assertEquals("Fernanda Lima", alunos.get(2).getNome());
    }

    @Test
    @DisplayName("Caso 8: Salvar aluno, alterar nome e chamar update - deve atualizar corretamente")
    void testUpdate_AlterarNome_DeveAtualizarCorretamente() {
        Aluno aluno = new Aluno();
        aluno.setNome("Roberto Alves");
        aluno.setIdade(23);
        aluno.setCurso("Análise e Desenvolvimento de Sistemas");
        aluno.setFase(5);

        repository.save(aluno);
        int idGerado = repository.getMaxId();

        Aluno alunoParaAtualizar = repository.findById(idGerado);
        assertNotNull(alunoParaAtualizar);
        assertEquals("Roberto Alves", alunoParaAtualizar.getNome());

        alunoParaAtualizar.setNome("Roberto Alves Silva");
        boolean resultado = repository.update(alunoParaAtualizar);

        assertTrue(resultado);

        Aluno alunoAtualizado = repository.findById(idGerado);
        assertNotNull(alunoAtualizado);
        assertEquals("Roberto Alves Silva", alunoAtualizado.getNome());
        assertEquals(23, alunoAtualizado.getIdade());
        assertEquals("Análise e Desenvolvimento de Sistemas", alunoAtualizado.getCurso());
        assertEquals(5, alunoAtualizado.getFase());
    }

    @Test
    @DisplayName("Caso 9: Salvar aluno e chamar delete - deve remover do banco")
    void testDelete_SalvarEExcluir_DeveRemoverDoBanco() {
        Aluno aluno = new Aluno();
        aluno.setNome("Lucas Pereira");
        aluno.setIdade(22);
        aluno.setCurso("Ciências Contábeis");
        aluno.setFase(3);

        repository.save(aluno);
        int idGerado = repository.getMaxId();

        Aluno alunoAntes = repository.findById(idGerado);
        assertNotNull(alunoAntes);

        boolean resultado = repository.delete(idGerado);

        assertTrue(resultado);

        Aluno alunoDepois = repository.findById(idGerado);
        assertNull(alunoDepois);
    }

    @Test
    @DisplayName("Caso 10: getMaxId quando tabela está vazia - deve retornar 0")
    void testGetMaxId_TabelaVazia_DeveRetornarZero() {
        int maxId = repository.getMaxId();
        assertEquals(0, maxId);
    }

    @Test
    @DisplayName("Caso 11: getMaxId após salvar múltiplos alunos - deve retornar maior ID")
    void testGetMaxId_MultiplosAlunos_DeveRetornarMaiorId() {
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Aluno 1");
        aluno1.setIdade(20);
        aluno1.setCurso("Curso 1");
        aluno1.setFase(1);
        repository.save(aluno1);

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Aluno 2");
        aluno2.setIdade(21);
        aluno2.setCurso("Curso 2");
        aluno2.setFase(2);
        repository.save(aluno2);

        Aluno aluno3 = new Aluno();
        aluno3.setNome("Aluno 3");
        aluno3.setIdade(22);
        aluno3.setCurso("Curso 3");
        aluno3.setFase(3);
        repository.save(aluno3);

        int maxId = repository.getMaxId();
        assertTrue(maxId >= 3);
        
        Aluno alunoComMaxId = repository.findById(maxId);
        assertNotNull(alunoComMaxId);
        assertEquals("Aluno 3", alunoComMaxId.getNome());
    }
}

