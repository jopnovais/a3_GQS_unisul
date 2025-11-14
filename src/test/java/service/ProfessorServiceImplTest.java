package service;

import model.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.ProfessorRepository;
import service.exception.ValidacaoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Unitários - ProfessorServiceImpl")
class ProfessorServiceImplTest {

    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorServiceImpl professorService;

    private Professor professorValido;

    @BeforeEach
    void setUp() {
        professorValido = new Professor();
        professorValido.setNome("João Silva");
        professorValido.setIdade(30);
        professorValido.setCampus("Tubarão");
        professorValido.setCpf("12345678901");
        professorValido.setContato("47912345678");
        professorValido.setTitulo("Doutor");
        professorValido.setSalario(5000.0);
    }

    @Test
    @DisplayName("Caso 1: Salvar um professor perfeitamente válido - deve chamar repository.save()")
    void testSalvar_ProfessorValido_DeveChamarRepositorySave() throws ValidacaoException {
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        professorService.salvar(professorValido);
        
        verify(professorRepository, times(1)).save(professorValido);
    }

    @Test
    @DisplayName("Caso 2: Tentar salvar um professor nulo - deve lançar ValidacaoException")
    void testSalvar_ProfessorNulo_DeveLancarValidacaoException() {
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(null);
        });
        
        assertEquals("Professor não pode ser nulo.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 3: Tentar salvar um professor com nome nulo - deve lançar ValidacaoException")
    void testSalvar_ProfessorComNomeNulo_DeveLancarValidacaoException() {
        professorValido.setNome(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Nome é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 4: Tentar salvar um professor com nome vazio - deve lançar ValidacaoException")
    void testSalvar_ProfessorComNomeVazio_DeveLancarValidacaoException() {
        professorValido.setNome("");
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Nome é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 5: Tentar salvar um professor com nome contendo apenas 1 caractere - deve lançar ValidacaoException")
    void testSalvar_ProfessorComNomeComUmCaractere_DeveLancarValidacaoException() {
        professorValido.setNome("A");
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Nome deve conter ao menos 2 caracteres.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 6: Tentar salvar um professor com campus nulo - deve lançar ValidacaoException")
    void testSalvar_ProfessorComCampusNulo_DeveLancarValidacaoException() {
        professorValido.setCampus(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Campus é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 7: Tentar salvar um professor com campus vazio - deve lançar ValidacaoException")
    void testSalvar_ProfessorComCampusVazio_DeveLancarValidacaoException() {
        professorValido.setCampus("");
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Campus é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 8: Tentar salvar um professor com campus igual a '-' - deve lançar ValidacaoException")
    void testSalvar_ProfessorComCampusDefault_DeveLancarValidacaoException() {
        professorValido.setCampus("-");
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Campus é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 9: Tentar salvar um professor com CPF nulo - deve lançar ValidacaoException")
    void testSalvar_ProfessorComCpfNulo_DeveLancarValidacaoException() {
        professorValido.setCpf(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("CPF é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 10: Tentar salvar um professor com CPF vazio - deve lançar ValidacaoException")
    void testSalvar_ProfessorComCpfVazio_DeveLancarValidacaoException() {
        professorValido.setCpf("");
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("CPF é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 11: Tentar salvar um professor com CPF contendo menos de 11 dígitos numéricos - deve lançar ValidacaoException")
    void testSalvar_ProfessorComCpfMenosDeOnzeDigitos_DeveLancarValidacaoException() {
        professorValido.setCpf("1234567890"); // 10 dígitos
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("O campo CPF deve possuir 11 caracteres numéricos.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 12: Tentar salvar um professor com CPF já cadastrado no sistema (isNovo=true) - deve lançar ValidacaoException")
    void testSalvar_ProfessorComCpfJaCadastrado_DeveLancarValidacaoException() {
        Professor professorExistente = new Professor();
        professorExistente.setId(1);
        professorExistente.setCpf(professorValido.getCpf());
        
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(professorExistente);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("CPF já cadastrado no sistema.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 13: Tentar salvar um professor com contato nulo - deve lançar ValidacaoException")
    void testSalvar_ProfessorComContatoNulo_DeveLancarValidacaoException() {
        professorValido.setContato(null);
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Contato é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 14: Tentar salvar um professor com contato vazio - deve lançar ValidacaoException")
    void testSalvar_ProfessorComContatoVazio_DeveLancarValidacaoException() {
        professorValido.setContato("");
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Contato é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 15: Tentar salvar um professor com contato contendo menos de 11 dígitos numéricos - deve lançar ValidacaoException")
    void testSalvar_ProfessorComContatoMenosDeOnzeDigitos_DeveLancarValidacaoException() {
        professorValido.setContato("4791234567"); // 10 dígitos
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("O campo contato deve possuir 11 caracteres numéricos.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 16: Tentar salvar um professor com idade menor que 11 anos - deve lançar ValidacaoException")
    void testSalvar_ProfessorComIdadeMenorQueOnze_DeveLancarValidacaoException() {
        professorValido.setIdade(10);
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Idade inválida. Deve ser maior ou igual a 11 anos.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 17: Tentar salvar um professor com salário menor ou igual a zero - deve lançar ValidacaoException")
    void testSalvar_ProfessorComSalarioMenorOuIgualAZero_DeveLancarValidacaoException() {
        professorValido.setSalario(0);
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Salário deve ser maior que zero.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 18: Tentar salvar um professor com salário contendo menos de 4 dígitos numéricos - deve lançar ValidacaoException")
    void testSalvar_ProfessorComSalarioMenosDeQuatroDigitos_DeveLancarValidacaoException() {
        professorValido.setSalario(999.0); // 3 dígitos
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("O campo salário deve possuir no mínimo 4 caracteres numéricos.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 19: Tentar salvar um professor com título nulo - deve lançar ValidacaoException")
    void testSalvar_ProfessorComTituloNulo_DeveLancarValidacaoException() {
        professorValido.setTitulo(null);
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Título é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 20: Tentar salvar um professor com título vazio - deve lançar ValidacaoException")
    void testSalvar_ProfessorComTituloVazio_DeveLancarValidacaoException() {
        professorValido.setTitulo("");
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Título é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 21: Tentar salvar um professor com título igual a '-' - deve lançar ValidacaoException")
    void testSalvar_ProfessorComTituloDefault_DeveLancarValidacaoException() {
        professorValido.setTitulo("-");
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(null);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.salvar(professorValido);
        });
        
        assertEquals("Título é obrigatório.", exception.getMessage());
        verify(professorRepository, never()).save(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 1: Tentar atualizar um professor com ID menor ou igual a zero - deve lançar ValidacaoException")
    void testAtualizar_ProfessorComIdMenorOuIgualAZero_DeveLancarValidacaoException() {
        professorValido.setId(0);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.atualizar(professorValido);
        });
        
        assertEquals("ID do professor é obrigatório para atualização.", exception.getMessage());
        verify(professorRepository, never()).update(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 2: Atualizar um professor válido com ID maior que zero - deve chamar repository.update()")
    void testAtualizar_ProfessorValidoComIdMaiorQueZero_DeveChamarRepositoryUpdate() throws ValidacaoException {
        professorValido.setId(1);
        Professor professorExistente = new Professor();
        professorExistente.setId(1);
        professorExistente.setCpf(professorValido.getCpf());
        
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(professorExistente);
        
        professorService.atualizar(professorValido);
        
        verify(professorRepository, times(1)).update(professorValido);
    }

    @Test
    @DisplayName("Caso 3: Tentar atualizar um professor com CPF já cadastrado para outro professor (isNovo=false, existenteComMesmoCpf.getId() != professor.getId()) - deve lançar ValidacaoException")
    void testAtualizar_ProfessorComCpfJaCadastradoParaOutroProfessor_DeveLancarValidacaoException() {
        professorValido.setId(1);
        Professor professorExistente = new Professor();
        professorExistente.setId(2); // ID diferente
        professorExistente.setCpf(professorValido.getCpf());
        
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(professorExistente);
        
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            professorService.atualizar(professorValido);
        });
        
        assertEquals("CPF já cadastrado no sistema.", exception.getMessage());
        verify(professorRepository, never()).update(any(Professor.class));
    }

    @Test
    @DisplayName("Caso 4: Atualizar um professor com o mesmo CPF (isNovo=false, existenteComMesmoCpf.getId() == professor.getId()) - deve chamar repository.update()")
    void testAtualizar_ProfessorComMesmoCpfMesmoId_DeveChamarRepositoryUpdate() throws ValidacaoException {
        professorValido.setId(1);
        Professor professorExistente = new Professor();
        professorExistente.setId(1); // Mesmo ID
        professorExistente.setCpf(professorValido.getCpf());
        
        when(professorRepository.findByCpf(professorValido.getCpf())).thenReturn(professorExistente);
        
        professorService.atualizar(professorValido);
        
        verify(professorRepository, times(1)).update(professorValido);
    }

    @Test
    @DisplayName("Caso 1: Excluir um professor existente - deve chamar repository.delete()")
    void testExcluir_ProfessorExistente_DeveChamarRepositoryDelete() {
        int id = 5;
        
        professorService.excluir(id);
        
        verify(professorRepository, times(1)).delete(id);
    }

    @Test
    @DisplayName("Caso 1: Buscar um professor por ID existente - deve chamar repository.findById() e retornar o resultado")
    void testBuscarPorId_ProfessorExistente_DeveChamarRepositoryFindByIdERetornarResultado() {
        int id = 1;
        Professor professorMock = new Professor();
        professorMock.setId(id);
        professorMock.setNome("Professor Teste");
        professorMock.setIdade(35);
        professorMock.setCampus("Tubarão");
        professorMock.setCpf("12345678901");
        professorMock.setContato("47912345678");
        professorMock.setTitulo("Doutor");
        professorMock.setSalario(5000.0);
        
        when(professorRepository.findById(id)).thenReturn(professorMock);
        
        Professor resultado = professorService.buscarPorId(id);
        
        verify(professorRepository, times(1)).findById(id);
        assertNotNull(resultado);
        assertEquals(professorMock, resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Professor Teste", resultado.getNome());
    }

    @Test
    @DisplayName("Caso 1: Buscar um professor por CPF existente - deve chamar repository.findByCpf() e retornar o resultado")
    void testBuscarPorCpf_ProfessorExistente_DeveChamarRepositoryFindByCpfERetornarResultado() {
        String cpf = "12345678901";
        Professor professorMock = new Professor();
        professorMock.setId(1);
        professorMock.setNome("Professor Teste");
        professorMock.setIdade(35);
        professorMock.setCampus("Tubarão");
        professorMock.setCpf(cpf);
        professorMock.setContato("47912345678");
        professorMock.setTitulo("Doutor");
        professorMock.setSalario(5000.0);
        
        when(professorRepository.findByCpf(cpf)).thenReturn(professorMock);
        
        Professor resultado = professorService.buscarPorCpf(cpf);
        
        verify(professorRepository, times(1)).findByCpf(cpf);
        assertNotNull(resultado);
        assertEquals(professorMock, resultado);
        assertEquals(cpf, resultado.getCpf());
        assertEquals("Professor Teste", resultado.getNome());
    }
}

