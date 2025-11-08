package view;

import DAO.AlunoDAO;
import model.Aluno;

public class EditarAlunoController {

    private EditarAluno view;
    private AlunoDAO alunoDAO;
    private int idAluno;

    // --- Constantes da Regra de Negócio ---
    private static final int IDADE_MINIMA = 11;
    private static final int IDADE_MAXIMA = 90;

    public EditarAlunoController(EditarAluno view, AlunoDAO alunoDAO, int idAluno) {
        this.view = view;
        this.alunoDAO = alunoDAO;
        this.idAluno = idAluno;
    }

    public void carregarDadosIniciais() {
        Aluno aluno = alunoDAO.carregaAluno(this.idAluno);

        view.setNome(aluno.getNome());
        view.setIdade(String.valueOf(aluno.getIdade()));

        for (int i = 0; i < view.getCursoItemCount(); i++) {
            if (view.getCursoItemAt(i).equalsIgnoreCase(aluno.getCurso())) {
                view.setCursoIndex(i);
                break;
            }
        }

        view.setFaseIndex(aluno.getFase() - 1);
    }

    public void salvarAlteracoes() {
        try {
            String nome = view.getNome();
            String idadeStr = view.getIdade();
            int cursoIdx = view.getCursoIndex();
            String curso = view.getCursoValor();
            int fase = view.getFaseValor();

            // --- Lógica de Validação Atualizada ---
            if (nome.length() < 2) {
                throw new Mensagens("Nome deve conter ao menos 2 caracteres.");
            }

            int idade;
            try {
                idade = Integer.parseInt(idadeStr);
            } catch (NumberFormatException e) {
                throw new Mensagens("Idade deve ser um número.");
            }

            // LÓGICA DE IDADE ATUALIZADA (MÍNIMA E MÁXIMA)
            if (idade < IDADE_MINIMA) {
                throw new Mensagens("Idade deve ser no mínimo " + IDADE_MINIMA + " anos.");
            }
            if (idade > IDADE_MAXIMA) {
                throw new Mensagens("Idade deve ser no máximo " + IDADE_MAXIMA + " anos.");
            }
            // --- Fim da Lógica de Validação ---

            if (cursoIdx == 0) {
                throw new Mensagens("Escolha um curso");
            }

            Aluno alunoAtualizado = new Aluno(curso, fase, this.idAluno, nome, idade);

            if (alunoDAO.UpdateAlunoBD(alunoAtualizado)) {
                view.exibirMensagemSucesso("Aluno alterado com sucesso!");
                view.fecharJanela();
            } else {
                view.exibirMensagemErro("Erro ao atualizar aluno no banco.");
            }

        } catch (Mensagens erro) {
            view.exibirMensagemErro(erro.getMessage());
        } catch (Exception e) {
            view.exibirMensagemErro("Erro inesperado: " + e.getMessage());
        }
    }
}