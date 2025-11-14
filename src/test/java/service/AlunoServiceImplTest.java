package service;

import model.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AlunoRepository;
import service.exception.ValidacaoException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes Unitários - AlunoServiceImpl")
class AlunoServiceImplTest {

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
    void testSalvarNaoDeveSalvarFaseMenorQueUm() {
        alunoValido.setFase(0); // fase inválida

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Fase deve estar entre 1 e 10.", exception.getMessage());
        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    @DisplayName("Caso 14: Tentar salvar um aluno com fase maior que 10 - deve lançar ValidacaoException")
    void testSalvarNaoDeveSalvarFaseMaiorQueDez() {
        alunoValido.setFase(11); // fase inválida

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.salvar(alunoValido);
        });

        assertEquals("Fase deve estar entre 1 e 10.", exception.getMessage());
        verify(alunoRepository, never()).save(any(Aluno.class));
    }
 
    @Test
    @DisplayName("Atualizar - Caso 1: Tentar atualizar um aluno com ID menor ou igual a zero - deve lançar ValidacaoException")
    void testAtualizar_AlunoComIdMenorOuIgualZero_DeveLancarValidacaoException() {
        alunoValido.setId(0); 

        ValidacaoException exception = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });

        assertEquals("ID do aluno é obrigatório para atualização.", exception.getMessage());
        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Atualizar - Caso 2: Atualizar um aluno válido com ID maior que zero - deve chamar repository.update()")
    void testAtualizar_AlunoValido_DeveChamarRepositoryUpdate() throws ValidacaoException {
        alunoValido.setId(1); 

        alunoService.atualizar(alunoValido);

        verify(alunoRepository, times(1)).update(alunoValido);
    }

   @Test
    @DisplayName("Atualizar - Caso 3: Tentar atualizar um aluno nulo - comportamento atual: lança NullPointerException")
    void testAtualizar_AlunoNulo_DeveLancarNullPointerException() {
    assertThrows(NullPointerException.class, () -> {
        alunoService.atualizar(null);
    });

    verify(alunoRepository, never()).update(any(Aluno.class));
    }


    @Test
    @DisplayName("Atualizar - Caso 4: Tentar atualizar um aluno com nome inválido (nulo, vazio ou com menos de 2 caracteres) - deve lançar ValidacaoException")
    void testAtualizar_AlunoComNomeInvalido_DeveLancarValidacaoException() {
        alunoValido.setId(1);

    
        alunoValido.setNome(null);
        assertThrows(ValidacaoException.class, () -> alunoService.atualizar(alunoValido));

     
        alunoValido.setNome("");
        assertThrows(ValidacaoException.class, () -> alunoService.atualizar(alunoValido));

    
        alunoValido.setNome("A");
        assertThrows(ValidacaoException.class, () -> alunoService.atualizar(alunoValido));

        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Atualizar - Caso 5: Tentar atualizar um aluno com idade menor que 11 anos - deve lançar ValidacaoException")
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
    @DisplayName("Atualizar - Caso 6: Tentar atualizar um aluno com curso inválido (nulo, vazio ou igual a '-') - deve lançar ValidacaoException")
    void testAtualizar_AlunoComCursoInvalido_DeveLancarValidacaoException() {
        alunoValido.setId(1); // ID válido


        alunoValido.setCurso(null);
        ValidacaoException ex1 = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });
        assertEquals("Curso é obrigatório.", ex1.getMessage());


        alunoValido.setCurso("");
        ValidacaoException ex2 = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });
        assertEquals("Curso é obrigatório.", ex2.getMessage());


        alunoValido.setCurso("-");
        ValidacaoException ex3 = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });
        assertEquals("Curso é obrigatório.", ex3.getMessage());

        verify(alunoRepository, never()).update(any(Aluno.class));
    }

    @Test
    @DisplayName("Atualizar - Caso 7: Tentar atualizar um aluno com fase inválida (menor que 1 ou maior que 10) - deve lançar ValidacaoException")
    void testAtualizar_AlunoComFaseInvalida_DeveLancarValidacaoException() {
        alunoValido.setId(1); // ID válido

        alunoValido.setFase(0);
        ValidacaoException ex1 = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });
        assertEquals("Fase deve estar entre 1 e 10.", ex1.getMessage());

        alunoValido.setFase(11);
        ValidacaoException ex2 = assertThrows(ValidacaoException.class, () -> {
            alunoService.atualizar(alunoValido);
        });
        assertEquals("Fase deve estar entre 1 e 10.", ex2.getMessage());

        verify(alunoRepository, never()).update(any(Aluno.class));
    }

}

