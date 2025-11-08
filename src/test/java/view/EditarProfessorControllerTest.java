package view;

import DAO.ProfessorDAO;
import model.Professor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EditarProfessorControllerTest {

    @Mock
    private EditarProfessor view;
    @Mock
    private ProfessorDAO dao;

    private EditarProfessorController controller;

    @Captor
    private ArgumentCaptor<Professor> professorCaptor;

    private Professor professorDoBanco;
    private final int ID_PROFESSOR = 20;

    @BeforeEach
    void setUp() {
        Locale.setDefault(new Locale("pt", "BR"));
        professorDoBanco = new Professor("Pedra Branca", "111.222.333-44", "(48) 9 1111-1111", "Mestrado", 5000.20, ID_PROFESSOR, "Professor Antigo", 45);
        controller = new EditarProfessorController(view, dao, ID_PROFESSOR);
    }

    @Test
    void testCarregarDadosIniciais_DevePreencherViewCorretamenteComVirgula() {
        // 1. Setup
        when(dao.carregaProfessor(ID_PROFESSOR)).thenReturn(professorDoBanco);

        // Stubs do Campus (Corretos)
        when(view.getCampusItemCount()).thenReturn(3);
        when(view.getCampusItemAt(0)).thenReturn("-");
        when(view.getCampusItemAt(1)).thenReturn("Pedra Branca"); // Match

        // Stubs do Título (Corrigidos)
        when(view.getTituloItemCount()).thenReturn(3);
        when(view.getTituloItemAt(0)).thenReturn("-");
        when(view.getTituloItemAt(1)).thenReturn("Especialização"); // <-- LINHA ADICIONADA DE VOLTA
        when(view.getTituloItemAt(2)).thenReturn("Mestrado");       // Match

        // 2. Ação
        controller.carregarDadosIniciais();

        // 3. Verificação
        verify(dao).carregaProfessor(ID_PROFESSOR);
        verify(view).setNome("Professor Antigo");
        verify(view).setIdade("45");
        verify(view).setCpf("111.222.333-44");
        verify(view).setContato("(48) 9 1111-1111");
        verify(view).setSalario(contains("5.000,20"));
        verify(view).setCampusIndex(1);
        verify(view).setTituloIndex(2);
    }

    @Test
    void testSalvarAlteracoes_ComDadosValidosESalarioComVirgula_DeveAtualizarEFechar() {
        // 1. Setup
        when(view.getNome()).thenReturn("Professor Novo");
        when(view.getIdade()).thenReturn("30");
        when(view.getCampusIndex()).thenReturn(1);
        when(view.getCampusValor()).thenReturn("Ilha");
        when(view.getCpf()).thenReturn("999.888.777-66");
        when(view.getContato()).thenReturn("(48) 9 2222-2222");
        when(view.getTituloIndex()).thenReturn(1);
        when(view.getTituloValor()).thenReturn("Doutorado");
        when(view.getSalario()).thenReturn("R$ 7.000,50");
        when(dao.getMinhaLista()).thenReturn(new ArrayList<>());
        when(dao.UpdateProfessorBD(any(Professor.class))).thenReturn(true);

        // 2. Ação
        controller.salvarAlteracoes();

        // 3. Verificação
        verify(dao).UpdateProfessorBD(professorCaptor.capture());
        Professor profEnviado = professorCaptor.getValue();
        assertEquals(7000.50, profEnviado.getSalario());
        verify(view).exibirMensagemSucesso("Professor alterado com sucesso!");
        verify(view).fecharJanela();
        verify(view, never()).exibirMensagemErro(anyString());
    }

    @Test
    void testSalvarAlteracoes_NomeCurto_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("A");
        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("Nome deve conter ao menos 2 caracteres.");
    }

    @Test
    void testSalvarAlteracoes_CampusNaoSelecionado_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Válido");
        when(view.getCampusIndex()).thenReturn(0);
        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("Escolha o campus");
    }

    @Test
    void testSalvarAlteracoes_CpfInvalido_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Válido");
        when(view.getCampusIndex()).thenReturn(1);
        when(view.getCpf()).thenReturn("123.456");
        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("O campo CPF deve possuir 11 caracteres numéricos");
    }

    @Test
    void testSalvarAlteracoes_CpfDuplicado_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Válido");
        when(view.getCampusIndex()).thenReturn(1);
        when(view.getCpf()).thenReturn("111.222.333-44");
        when(view.getContato()).thenReturn("(48) 9 2222-2222");

        Professor outroProfessor = new Professor();
        outroProfessor.setId(99);
        outroProfessor.setCpf("111.222.333-44");
        when(dao.getMinhaLista()).thenReturn(new ArrayList<>(Arrays.asList(outroProfessor)));

        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("CPF já cadastrado no sistema");
    }

    @Test
    void testSalvarAlteracoes_ContatoInvalido_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Válido");
        when(view.getCampusIndex()).thenReturn(1);
        when(view.getCpf()).thenReturn("111.222.333-44");
        when(view.getContato()).thenReturn("(48) 9 123");
        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("O campo contato deve possuir 11 caracteres numéricos");
    }

    @Test
    void testSalvarAlteracoes_IdadeAbaixoDoMinimo_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Válido");
        when(view.getCampusIndex()).thenReturn(1);
        when(view.getCpf()).thenReturn("111.222.333-44");
        when(view.getContato()).thenReturn("(48) 9 2222-2222");
        when(view.getIdade()).thenReturn("17");
        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("Idade deve ser no mínimo 18 anos.");
    }

    @Test
    void testSalvarAlteracoes_IdadeAcimaDoMaximo_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Válido");
        when(view.getCampusIndex()).thenReturn(1);
        when(view.getCpf()).thenReturn("111.222.333-44");
        when(view.getContato()).thenReturn("(48) 9 2222-2222");
        when(view.getIdade()).thenReturn("86");
        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("Idade deve ser no máximo 85 anos.");
    }

    @Test
    void testSalvarAlteracoes_SalarioAbaixoDoMinimo_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Válido");
        when(view.getCampusIndex()).thenReturn(1);
        when(view.getCpf()).thenReturn("111.222.333-44");
        when(view.getContato()).thenReturn("(48) 9 2222-2222");
        when(view.getIdade()).thenReturn("30");
        when(view.getSalario()).thenReturn("R$ 999,99");
        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("Salário deve ser no mínimo R$ 1000.0.");
    }

    @Test
    void testSalvarAlteracoes_SalarioFormatoInvalido_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Válido");
        when(view.getCampusIndex()).thenReturn(1);
        when(view.getCpf()).thenReturn("111.222.333-44");
        when(view.getContato()).thenReturn("(48) 9 2222-2222");
        when(view.getIdade()).thenReturn("30");
        when(view.getSalario()).thenReturn("R$ 100a,b20");
        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("Salário deve ser um número válido (ex: 4500,20).");
    }

    @Test
    void testSalvarAlteracoes_TituloNaoSelecionado_NaoDeveChamarDAO() {
        when(view.getNome()).thenReturn("Nome Válido");
        when(view.getCampusIndex()).thenReturn(1);
        when(view.getCpf()).thenReturn("111.222.333-44");
        when(view.getContato()).thenReturn("(48) 9 2222-2222");
        when(view.getIdade()).thenReturn("30");
        when(view.getSalario()).thenReturn("R$5000");
        when(view.getTituloIndex()).thenReturn(0);
        controller.salvarAlteracoes();
        verify(dao, never()).UpdateProfessorBD(any(Professor.class));
        verify(view).exibirMensagemErro("Defina um título");
    }
}