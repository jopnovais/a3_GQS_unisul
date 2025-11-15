package service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import model.Aluno;
import repository.AlunoRepository;
import service.exception.ValidacaoException;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Unitários - AlunoServiceImpl")
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoServiceImpl alunoService;

    private Aluno alunoValido;

    @BeforeEach
    void setUp() {
        alunoValido = new Aluno();
        alunoValido.setNome("João Silva");
        alunoValido.setIdade(20);
        alunoValido.setCurso("Ciências da Computação");
        alunoValido.setFase(3);
    }

    @Test
    @DisplayName("Caso 1: Salvar um aluno perfeitamente válido - deve chamar repository.save()")
    void testSalvar_AlunoValido_DeveChamarRepositorySave() throws ValidacaoException {
        alunoService.salvar(alunoValido);

        verify(alunoRepository, times(1)).save(alunoValido);
    }

    @Test
    @DisplayName("Caso 2: Tentar salvar um aluno com nome nulo - deve lançar ValidacaoException")
    void testSalvar_AlunoComNomeNulo_DeveLancarValidacaoException() {
        alunoValido.setNome(null);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Nome é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 3: Tentar salvar um aluno com nome vazio - deve lançar ValidacaoException")
    void testSalvar_AlunoComNomeVazio_DeveLancarValidacaoException() {
        alunoValido.setNome("");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Nome é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 4: Tentar salvar um aluno nulo - deve lançar ValidacaoException")
    void testSalvarNaoDeveSalvarAlunoNulo() {
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(null);
        });

        assertEquals("Aluno não pode ser nulo.", exception.getMessage());
        verify(alunoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Caso 5: Tentar salvar um aluno com nome contendo apenas espaços - deve lançar ValidacaoException")
    void testSalvarNaoDeveSalvarNomeComEspacos() {
        alunoValido.setNome("   ");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Nome é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Caso 6: Tentar salvar um aluno com nome de 1 caractere - deve lançar ValidacaoException")
    void testSalvarNaoDeveSalvarNomeComUmCaractere() {
        alunoValido.setNome("A");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Nome deve conter ao menos 2 caracteres.", exception.getMessage());
        verify(alunoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Caso 7: Tentar salvar um aluno com idade menor que 11 - deve lançar ValidacaoException")
    void testSalvarNaoDeveSalvarIdadeMenorQueOnze() {
        alunoValido.setIdade(10);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Idade inválida. Deve ser maior ou igual a 11 anos.", exception.getMessage());
        verify(alunoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Caso 8: Tentar salvar um aluno com curso nulo - deve lançar ValidacaoException")
    void testSalvarNaoDeveSalvarCursoNulo() {
        alunoValido.setCurso(null);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Curso é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Caso 9: Tentar salvar um aluno com curso vazio - deve lançar ValidacaoException")
    void testSalvarNaoDeveSalvarCursoVazio() {
        alunoValido.setCurso("");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Curso é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Caso 10: Tentar salvar um aluno com curso igual a '-' - deve lançar ValidacaoException")
    void testSalvarNaoDeveSalvarCursoDefault() {
        alunoValido.setCurso("-");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Curso é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Caso 11: Excluir um aluno - deve chamar repository.delete()")
    void testExcluirDeveChamarRepositorio() {
        int id = 5;

        alunoService.excluir(id);

        verify(alunoRepository, times(1)).delete(id);
    }

    @Test
    @DisplayName("Caso 12: Buscar aluno por ID - deve chamar repository.findById()")
    void testBuscarPorIdDeveChamarRepositorio() {
        Aluno alunoMock = new Aluno();
        alunoMock.setId(1);
        alunoMock.setNome("Aluno Teste");
        alunoMock.setIdade(20);
        alunoMock.setCurso("Teste");
        alunoMock.setFase(1);

        when(alunoRepository.findById(1)).thenReturn(alunoMock);

        Aluno resultado = alunoService.buscarPorId(1);

        verify(alunoRepository, times(1)).findById(1);
        assertNotNull(resultado);
        assertEquals(alunoMock, resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Aluno Teste", resultado.getNome());
    }

    @Test
    @DisplayName("Caso 13: Tentar salvar um aluno com fase menor que 1 - deve lançar ValidacaoException")
    void testSalvar_AlunoComFaseMenorQueUm_DeveLancarValidacaoException() {
        alunoValido.setFase(0);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Fase deve estar entre 1 e 10.", exception.getMessage());
        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 14: Tentar salvar um aluno com fase maior que 10 - deve lançar ValidacaoException")
    void testSalvar_AlunoComFaseMaiorQueDez_DeveLancarValidacaoException() {
        alunoValido.setFase(11);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Fase deve estar entre 1 e 10.", exception.getMessage());
        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 20: Tentar atualizar um aluno com ID menor ou igual a zero - deve lançar ValidacaoException")
    void testAtualizar_AlunoComIdMenorOuIgualZero_DeveLancarValidacaoException() {
        alunoValido.setId(0);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("ID do aluno é obrigatório para atualização.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 21: Atualizar um aluno válido com ID maior que zero - deve chamar repository.update()")
    void testAtualizar_AlunoValido_DeveChamarRepositoryUpdate() throws ValidacaoException {
        alunoValido.setId(1);

        alunoService.atualizar(alunoValido);

        verify(alunoRepository, times(1)).update(alunoValido);
    }

    @Test
    @DisplayName("Caso 22: Tentar atualizar um aluno nulo - deve lançar ValidacaoException")
    void testAtualizar_AlunoNulo_DeveLancarValidacaoException() {
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(null);
        });

        assertEquals("Aluno não pode ser nulo.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 23: Tentar atualizar um aluno com nome nulo - deve lançar ValidacaoException")
    void testAtualizar_AlunoComNomeNulo_DeveLancarValidacaoException() {
        alunoValido.setId(1);
        alunoValido.setNome(null);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("Nome é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 24: Tentar atualizar um aluno com nome vazio - deve lançar ValidacaoException")
    void testAtualizar_AlunoComNomeVazio_DeveLancarValidacaoException() {
        alunoValido.setId(1);
        alunoValido.setNome("");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("Nome é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 25: Tentar atualizar um aluno com nome de 1 caractere - deve lançar ValidacaoException")
    void testAtualizar_AlunoComNomeUmCaractere_DeveLancarValidacaoException() {
        alunoValido.setId(1);
        alunoValido.setNome("A");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("Nome deve conter ao menos 2 caracteres.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 26: Tentar atualizar um aluno com idade menor que 11 anos - deve lançar ValidacaoException")
    void testAtualizar_AlunoComIdadeMenorQueOnze_DeveLancarValidacaoException() {
        alunoValido.setId(1);
        alunoValido.setIdade(10);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("Idade inválida. Deve ser maior ou igual a 11 anos.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 27: Tentar atualizar um aluno com curso nulo - deve lançar ValidacaoException")
    void testAtualizar_AlunoComCursoNulo_DeveLancarValidacaoException() {
        alunoValido.setId(1);
        alunoValido.setCurso(null);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("Curso é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 28: Tentar atualizar um aluno com curso vazio - deve lançar ValidacaoException")
    void testAtualizar_AlunoComCursoVazio_DeveLancarValidacaoException() {
        alunoValido.setId(1);
        alunoValido.setCurso("");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("Curso é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 29: Tentar atualizar um aluno com curso igual a '-' - deve lançar ValidacaoException")
    void testAtualizar_AlunoComCursoDefault_DeveLancarValidacaoException() {
        alunoValido.setId(1);
        alunoValido.setCurso("-");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("Curso é obrigatório.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 30: Tentar atualizar um aluno com fase menor que 1 - deve lançar ValidacaoException")
    void testAtualizar_AlunoComFaseMenorQueUm_DeveLancarValidacaoException() {
        alunoValido.setId(1);
        alunoValido.setFase(0);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("Fase deve estar entre 1 e 10.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 31: Tentar atualizar um aluno com fase maior que 10 - deve lançar ValidacaoException")
    void testAtualizar_AlunoComFaseMaiorQueDez_DeveLancarValidacaoException() {
        alunoValido.setId(1);
        alunoValido.setFase(11);

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("Fase deve estar entre 1 e 10.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 32: Tentar calcular idade com data de nascimento nula - deve lançar ValidacaoException")
    void testCalcularIdade_DataNascimentoNula_DeveLancarValidacaoException() {
        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.calcularIdade(null);
        });

        assertEquals("Data de nascimento não pode ser nula.", exception.getMessage());
    }

    @Test
    @DisplayName("Caso 33: Calcular idade quando o aniversário ainda não ocorreu no ano atual - deve subtrair 1")
    void testCalcularIdade_AniversarioAindaNaoOcorreu_DeveSubtrairUm() {
        Calendar dataNasc = Calendar.getInstance();
        dataNasc.add(Calendar.DAY_OF_YEAR, 1);
        dataNasc.add(Calendar.YEAR, -20);
        Date dataNascimento = dataNasc.getTime();

        int idade = alunoService.calcularIdade(dataNascimento);

        assertEquals(19, idade);
    }

    @Test
    @DisplayName("Caso 34: Calcular idade quando o aniversário já ocorreu no ano atual - deve retornar idade exata")
    void testCalcularIdade_AniversarioJaOcorreu_DeveRetornarIdadeExata() {
        Calendar dataNasc = Calendar.getInstance();
        dataNasc.add(Calendar.DAY_OF_YEAR, -1);
        dataNasc.add(Calendar.YEAR, -20);
        Date dataNascimento = dataNasc.getTime();

        int idade = alunoService.calcularIdade(dataNascimento);

        assertEquals(20, idade);
    }

    @Test
    @DisplayName("Caso 35: Listar todos os alunos - deve chamar repository.findAll() e retornar a lista")
    void testListarTodos_DeveChamarFindAllERetornarLista() {
        List<Aluno> listaMock = Arrays.asList(new Aluno(), new Aluno());
        when(alunoRepository.findAll()).thenReturn(listaMock);

        List<Aluno> resultado = alunoService.listarTodos();

        verify(alunoRepository, times(1)).findAll();
        assertNotNull(resultado);
        assertEquals(listaMock, resultado);
        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Caso 17: Tentar salvar um aluno com nome contendo apenas números - deve lançar ValidacaoException")
    void testSalvar_AlunoComNomeApenasNumeros_DeveLancarValidacaoException() {
        alunoValido.setNome("123456");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Nome é inválido (deve conter ao menos uma letra).", exception.getMessage());
        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 18: Tentar salvar um aluno com nome contendo apenas caracteres especiais - deve lançar ValidacaoException")
    void testSalvar_AlunoComNomeApenasCaracteresEspeciais_DeveLancarValidacaoException() {
        alunoValido.setNome("!@#$ %^&*");

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Nome é inválido (deve conter ao menos uma letra).", exception.getMessage());
        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 19: Salvar um aluno com nome misto (letras e números) - deve salvar corretamente")
    void testSalvar_AlunoComNomeMistoValido_DeveSalvarCorretamente() throws ValidacaoException {
        alunoValido.setNome("Davi 123");

        alunoService.salvar(alunoValido);

        verify(alunoRepository, times(1)).save(alunoValido);
    }
}
