package view;

import DAO.AlunoDAO;
import model.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EditarAlunoControllerTest {

    @Mock
    private EditarAluno view;
    @Mock
    private AlunoDAO alunoDAO;

    private EditarAlunoController controller;

    @Captor
    private ArgumentCaptor<Aluno> alunoCaptor;

    private Aluno alunoDoBanco;
    private final int ID_ALUNO = 10;

    @BeforeEach
    void setUp() {
        alunoDoBanco = new Aluno("Ciências da Computação", 5, ID_ALUNO, "Nome Antigo", 20);
        controller = new EditarAlunoController(view, alunoDAO, ID_ALUNO);
    }

    @Test
    void testCarregarDadosIniciais_DevePreencherViewCorretamente() {
        // 1. Setup
        when(alunoDAO.carregaAluno(ID_ALUNO)).thenReturn(alunoDoBanco);
        when(view.getCursoItemCount()).thenReturn(3);
        when(view.getCursoItemAt(0)).thenReturn("-");
        when(view.getCursoItemAt(1)).thenReturn("Ciências da Computação"); // Match

        // 2. Ação
        controller.carregarDadosIniciais();

        // 3. Verificação
        verify(alunoDAO).carregaAluno(ID_ALUNO);
        verify(view).setNome("Nome Antigo");
        verify(view).setIdade("20");
        verify(view).setCursoIndex(1);
        verify(view).setFaseIndex(4);
    }

    @Test
    void testSalvarAlteracoes_ComDadosValidos_DeveAtualizarEFechar() {
        // 1. Setup
        when(view.getNome()).thenReturn("Nome Novo Valido");
        when(view.getIdade()).thenReturn("22");
        when(view.getCursoIndex()).thenReturn(1);
        when(view.getCursoValor()).thenReturn("Administração");
        when(view.getFaseValor()).thenReturn(3);
        when(alunoDAO.UpdateAlunoBD(any(Aluno.class))).thenReturn(true);

        // 2. Ação
        controller.salvarAlteracoes();

        // 3. Verificação
        verify(alunoDAO).UpdateAlunoBD(alunoCaptor.capture());
        Aluno alunoEnviado = alunoCaptor.getValue();
        assertEquals(ID_ALUNO, alunoEnviado.getId());
        assertEquals("Nome Novo Valido", alunoEnviado.getNome());

        // --- CORREÇÃO AQUI ---
        // A mensagem correta é "alterado", não "salvo".
        verify(view).exibirMensagemSucesso("Aluno alterado com sucesso!");

        verify(view).fecharJanela();
        verify(view, never()).exibirMensagemErro(anyString());
    }

    @Test
    void testSalvarAlteracoes_NomeCurto_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("A");
        controller.salvarAlteracoes();
        verify(alunoDAO, never()).UpdateAlunoBD(any(Aluno.class));
        verify(view).exibirMensagemErro("Nome deve conter ao menos 2 caracteres.");
    }

    @Test
    void testSalvarAlteracoes_IdadeAbaixoDoMinimo_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Valido");
        when(view.getIdade()).thenReturn("10"); // Mínimo é 11
        controller.salvarAlteracoes();
        verify(alunoDAO, never()).UpdateAlunoBD(any(Aluno.class));
        verify(view).exibirMensagemErro("Idade deve ser no mínimo 11 anos.");
    }

    @Test
    void testSalvarAlteracoes_IdadeAcimaDoMaximo_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Valido");
        when(view.getIdade()).thenReturn("91"); // Máximo é 90
        when(view.getCursoIndex()).thenReturn(1);
        controller.salvarAlteracoes();
        verify(alunoDAO, never()).UpdateAlunoBD(any(Aluno.class));
        verify(view).exibirMensagemErro("Idade deve ser no máximo 90 anos.");
    }

    @Test
    void testSalvarAlteracoes_IdadeNaoNumerica_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Valido");
        when(view.getIdade()).thenReturn("abc");
        controller.salvarAlteracoes();
        verify(alunoDAO, never()).UpdateAlunoBD(any(Aluno.class));
        verify(view).exibirMensagemErro("Idade deve ser um número.");
    }

    @Test
    void testSalvarAlteracoes_CursoNaoSelecionado_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Valido");
        when(view.getIdade()).thenReturn("25");
        when(view.getCursoIndex()).thenReturn(0);
        controller.salvarAlteracoes();
        verify(alunoDAO, never()).UpdateAlunoBD(any(Aluno.class));
        verify(view).exibirMensagemErro("Escolha um curso");
    }
}