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
    @DisplayName("Caso 4: Excluir um aluno - deve chamar repository.delete()")
    void testExcluirDeveChamarRepositorio() {
        int id = 5;
        
        alunoService.excluir(id);
        
        verify(alunoRepository, times(1)).delete(id);
    }

    @Test
    @DisplayName("Caso 5: Buscar aluno por ID - deve chamar repository.findById()")
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
}

