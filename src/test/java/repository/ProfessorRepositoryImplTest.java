package repository;

import db.ConnectionFactory;
import model.Professor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Integração - ProfessorRepositoryImpl")
class ProfessorRepositoryImplTest {

    private ProfessorRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        repository = new ProfessorRepositoryImpl();
        limparTabela();
    }

    @AfterEach
    void tearDown() throws SQLException {
        limparTabela();
    }

    private void limparTabela() throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM tb_professores");
        }
    }

    @Test
    @DisplayName("Caso 1: Salvar um professor novo e válido - deve retornar professor com id gerado")
    void testSave_ProfessorValido_DeveRetornarProfessorComIdGerado() {
        Professor professor = new Professor();
        professor.setNome("Carlos Mendes");
        professor.setIdade(45);
        professor.setCampus("Campus Tubarão");
        professor.setCpf("123.456.789-00");
        professor.setContato("(48) 99999-9999");
        professor.setTitulo("Doutor");
        professor.setSalario(8500.00);

        boolean resultado = repository.save(professor);
        
        assertTrue(resultado);
        
        int maxId = repository.getMaxId();
        assertTrue(maxId > 0, "ID gerado deve ser maior que zero");
        
        Professor professorSalvo = repository.findById(maxId);
        assertNotNull(professorSalvo);
        assertEquals("Carlos Mendes", professorSalvo.getNome());
        assertEquals(45, professorSalvo.getIdade());
        assertEquals("Campus Tubarão", professorSalvo.getCampus());
        assertEquals("123.456.789-00", professorSalvo.getCpf());
        assertEquals("(48) 99999-9999", professorSalvo.getContato());
        assertEquals("Doutor", professorSalvo.getTitulo());
        assertEquals(8500.00, professorSalvo.getSalario(), 0.01);
    }

    @Test
    @DisplayName("Caso 2: Tentar salvar professor com campo NOT NULL nulo - deve salvar mas com campo null")
    void testSave_ProfessorComCampoNulo_DeveSalvarComCampoNull() {
        Professor professor = new Professor();
        professor.setNome(null);
        professor.setIdade(40);
        professor.setCampus("Campus Araranguá");
        professor.setCpf("987.654.321-00");
        professor.setContato("(48) 88888-8888");
        professor.setTitulo("Mestre");
        professor.setSalario(7500.00);

        boolean resultado = repository.save(professor);
        assertTrue(resultado);
        
        int maxId = repository.getMaxId();
        Professor professorSalvo = repository.findById(maxId);
        assertNotNull(professorSalvo);
        assertNull(professorSalvo.getNome());
        assertEquals(40, professorSalvo.getIdade());
    }

    @Test
    @DisplayName("Caso 3: Buscar professor por ID que existe - deve retornar professor")
    void testFindById_IdExistente_DeveRetornarProfessor() {
        Professor professor = new Professor();
        professor.setNome("Ana Silva");
        professor.setIdade(38);
        professor.setCampus("Campus Florianópolis");
        professor.setCpf("111.222.333-44");
        professor.setContato("(48) 77777-7777");
        professor.setTitulo("Doutora");
        professor.setSalario(9200.00);

        repository.save(professor);
        int idGerado = repository.getMaxId();

        Professor professorEncontrado = repository.findById(idGerado);

        assertNotNull(professorEncontrado);
        assertEquals(idGerado, professorEncontrado.getId());
        assertEquals("Ana Silva", professorEncontrado.getNome());
        assertEquals(38, professorEncontrado.getIdade());
        assertEquals("Campus Florianópolis", professorEncontrado.getCampus());
        assertEquals("111.222.333-44", professorEncontrado.getCpf());
        assertEquals("(48) 77777-7777", professorEncontrado.getContato());
        assertEquals("Doutora", professorEncontrado.getTitulo());
        assertEquals(9200.00, professorEncontrado.getSalario(), 0.01);
    }

    @Test
    @DisplayName("Caso 4: Buscar professor por ID que não existe - deve retornar null")
    void testFindById_IdInexistente_DeveRetornarNull() {
        Professor professorEncontrado = repository.findById(999);

        assertNull(professorEncontrado);
    }

    @Test
    @DisplayName("Caso 4.5: Buscar professor por CPF existente - deve retornar professor correto")
    void testFindByCpf_CpfExistente_DeveRetornarProfessor() {
        Professor professor = new Professor();
        professor.setNome("João Santos");
        professor.setIdade(42);
        professor.setCampus("Campus Tubarão");
        professor.setCpf("123.456.789-00");
        professor.setContato("(48) 99999-9999");
        professor.setTitulo("Mestre");
        professor.setSalario(7500.00);

        repository.save(professor);
        
        Professor professorEncontrado = repository.findByCpf("123.456.789-00");
        
        assertNotNull(professorEncontrado);
        assertEquals("João Santos", professorEncontrado.getNome());
        assertEquals("123.456.789-00", professorEncontrado.getCpf());
    }

    @Test
    @DisplayName("Caso 4.6: Buscar professor por CPF inexistente - deve retornar null")
    void testFindByCpf_CpfInexistente_DeveRetornarNull() {
        Professor professorEncontrado = repository.findByCpf("999.999.999-99");
        assertNull(professorEncontrado);
    }

    @Test
    @DisplayName("Caso 5: Listar todos os professores quando banco está vazio - deve retornar lista vazia")
    void testFindAll_BancoVazio_DeveRetornarListaVazia() {
        List<Professor> professores = repository.findAll();

        assertNotNull(professores);
        assertEquals(0, professores.size());
        assertTrue(professores.isEmpty());
    }

    @Test
    @DisplayName("Caso 6: Listar todos os professores após salvar 2 professores - deve retornar lista com 2 professores")
    void testFindAll_AposSalvar2Professores_DeveRetornarListaCom2Professores() {
        Professor professor1 = new Professor();
        professor1.setNome("Roberto Santos");
        professor1.setIdade(50);
        professor1.setCampus("Campus Tubarão");
        professor1.setCpf("555.666.777-88");
        professor1.setContato("(48) 66666-6666");
        professor1.setTitulo("Doutor");
        professor1.setSalario(10000.00);
        repository.save(professor1);

        Professor professor2 = new Professor();
        professor2.setNome("Fernanda Costa");
        professor2.setIdade(35);
        professor2.setCampus("Campus Araranguá");
        professor2.setCpf("999.888.777-66");
        professor2.setContato("(48) 55555-5555");
        professor2.setTitulo("Mestre");
        professor2.setSalario(8000.00);
        repository.save(professor2);

        List<Professor> professores = repository.findAll();

        assertNotNull(professores);
        assertEquals(2, professores.size());
        assertEquals("Roberto Santos", professores.get(0).getNome());
        assertEquals("Fernanda Costa", professores.get(1).getNome());
    }

    @Test
    @DisplayName("Caso 6.5: Testar update com ID inexistente - deve retornar false")
    void testUpdate_IdInexistente_DeveRetornarFalse() {
        Professor professor = new Professor();
        professor.setId(999);
        professor.setNome("Professor Inexistente");
        professor.setIdade(40);
        professor.setCampus("Campus Teste");
        professor.setCpf("999.999.999-99");
        professor.setContato("(48) 99999-9999");
        professor.setTitulo("Mestre");
        professor.setSalario(5000.00);

        boolean resultado = repository.update(professor);
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Caso 6.6: Testar delete com ID inexistente - deve retornar false")
    void testDelete_IdInexistente_DeveRetornarFalse() {
        boolean resultado = repository.delete(999);
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Caso 7: Salvar professor, alterar contato e chamar update - deve atualizar corretamente")
    void testUpdate_AlterarContato_DeveAtualizarCorretamente() {
        Professor professor = new Professor();
        professor.setNome("Lucas Oliveira");
        professor.setIdade(42);
        professor.setCampus("Campus Florianópolis");
        professor.setCpf("444.333.222-11");
        professor.setContato("(48) 11111-1111");
        professor.setTitulo("Doutor");
        professor.setSalario(9000.00);

        repository.save(professor);
        int idGerado = repository.getMaxId();

        Professor professorParaAtualizar = repository.findById(idGerado);
        assertNotNull(professorParaAtualizar);
        assertEquals("(48) 11111-1111", professorParaAtualizar.getContato());

        professorParaAtualizar.setContato("(48) 22222-2222");
        boolean resultado = repository.update(professorParaAtualizar);

        assertTrue(resultado);

        Professor professorAtualizado = repository.findById(idGerado);
        assertNotNull(professorAtualizado);
        assertEquals("(48) 22222-2222", professorAtualizado.getContato());
        assertEquals("Lucas Oliveira", professorAtualizado.getNome());
        assertEquals(42, professorAtualizado.getIdade());
        assertEquals("Campus Florianópolis", professorAtualizado.getCampus());
        assertEquals("444.333.222-11", professorAtualizado.getCpf());
        assertEquals("Doutor", professorAtualizado.getTitulo());
        assertEquals(9000.00, professorAtualizado.getSalario(), 0.01);
    }

    @Test
    @DisplayName("Caso 8: Salvar professor e chamar delete - deve remover do banco")
    void testDelete_SalvarEExcluir_DeveRemoverDoBanco() {
        Professor professor = new Professor();
        professor.setNome("Patricia Lima");
        professor.setIdade(39);
        professor.setCampus("Campus Tubarão");
        professor.setCpf("777.888.999-00");
        professor.setContato("(48) 33333-3333");
        professor.setTitulo("Mestre");
        professor.setSalario(7800.00);

        repository.save(professor);
        int idGerado = repository.getMaxId();

        Professor professorAntes = repository.findById(idGerado);
        assertNotNull(professorAntes);

        boolean resultado = repository.delete(idGerado);

        assertTrue(resultado);

        Professor professorDepois = repository.findById(idGerado);
        assertNull(professorDepois);
    }

    @Test
    @DisplayName("Caso 9: getMaxId quando tabela está vazia - deve retornar 0")
    void testGetMaxId_TabelaVazia_DeveRetornarZero() {
        int maxId = repository.getMaxId();
        assertEquals(0, maxId);
    }

    @Test
    @DisplayName("Caso 10: getMaxId após salvar múltiplos professores - deve retornar maior ID")
    void testGetMaxId_MultiplosProfessores_DeveRetornarMaiorId() {
        Professor professor1 = new Professor();
        professor1.setNome("Professor 1");
        professor1.setIdade(40);
        professor1.setCampus("Campus 1");
        professor1.setCpf("111.111.111-11");
        professor1.setContato("(48) 11111-1111");
        professor1.setTitulo("Doutor");
        professor1.setSalario(5000.00);
        repository.save(professor1);

        Professor professor2 = new Professor();
        professor2.setNome("Professor 2");
        professor2.setIdade(41);
        professor2.setCampus("Campus 2");
        professor2.setCpf("222.222.222-22");
        professor2.setContato("(48) 22222-2222");
        professor2.setTitulo("Mestre");
        professor2.setSalario(6000.00);
        repository.save(professor2);

        int maxId = repository.getMaxId();
        assertTrue(maxId >= 2);
        
        Professor professorComMaxId = repository.findById(maxId);
        assertNotNull(professorComMaxId);
        assertEquals("Professor 2", professorComMaxId.getNome());
    }

    @Test
    @DisplayName("Caso 11: Testar findAll com múltiplos professores - deve retornar todos")
    void testFindAll_MultiplosProfessores_DeveRetornarTodos() {
        Professor professor1 = new Professor();
        professor1.setNome("Professor A");
        professor1.setIdade(40);
        professor1.setCampus("Campus A");
        professor1.setCpf("111.111.111-11");
        professor1.setContato("(48) 11111-1111");
        professor1.setTitulo("Doutor");
        professor1.setSalario(5000.00);
        repository.save(professor1);

        Professor professor2 = new Professor();
        professor2.setNome("Professor B");
        professor2.setIdade(41);
        professor2.setCampus("Campus B");
        professor2.setCpf("222.222.222-22");
        professor2.setContato("(48) 22222-2222");
        professor2.setTitulo("Mestre");
        professor2.setSalario(6000.00);
        repository.save(professor2);

        List<Professor> professores = repository.findAll();
        
        assertNotNull(professores);
        assertTrue(professores.size() >= 2);
    }
}

