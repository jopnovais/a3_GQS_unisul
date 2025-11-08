package view;

import DAO.ProfessorDAO;
import model.Professor;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EditarProfessorController {

    private EditarProfessor view;
    private ProfessorDAO professorDAO;
    private int idProfessor;

    // --- Constantes da Regra de Negócio Atualizadas ---
    private static final int IDADE_MINIMA = 18;
    private static final int IDADE_MAXIMA = 85; // ATUALIZADO
    private static final double SALARIO_MINIMO = 1000.0; // ADICIONADO
    private static final int TAMANHO_MIN_NOME = 2;
    private static final int TAMANHO_CPF = 11;
    private static final int TAMANHO_CONTATO = 11;

    public EditarProfessorController(EditarProfessor view, ProfessorDAO professorDAO, int idProfessor) {
        this.view = view;
        this.professorDAO = professorDAO;
        this.idProfessor = idProfessor;
    }

    /**
     * Carrega os dados e formata o salário para R$ XXXX,XX
     */
    public void carregarDadosIniciais() {
        Professor prof = professorDAO.carregaProfessor(this.idProfessor);

        view.setNome(prof.getNome());
        view.setIdade(String.valueOf(prof.getIdade()));
        view.setCpf(prof.getCpf());
        view.setContato(prof.getContato());

        // Formata o salário para o padrão monetário brasileiro
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String salarioFormatado = nf.format(prof.getSalario()); // Ex: "R$ 5.000,00"

        view.setSalario(salarioFormatado);

        // Lógica para selecionar ComboBox Campus
        for (int i = 0; i < view.getCampusItemCount(); i++) {
            if (view.getCampusItemAt(i).equalsIgnoreCase(prof.getCampus())) {
                view.setCampusIndex(i);
                break;
            }
        }

        // Lógica para selecionar ComboBox Título
        for (int i = 0; i < view.getTituloItemCount(); i++) {
            if (view.getTituloItemAt(i).equalsIgnoreCase(prof.getTitulo())) {
                view.setTituloIndex(i);
                break;
            }
        }
    }

    /**
     * Limpa e converte uma string de salário (ex: "R$ 4.500,20") para um double.
     * @param salarioStr A string vinda da View
     * @return um double
     * @throws NumberFormatException se o formato for inválido
     */
    private double parseSalario(String salarioStr) throws NumberFormatException {
        if (salarioStr == null || salarioStr.trim().isEmpty()) {
            throw new NumberFormatException("Salário não pode ser vazio");
        }

        String salarioLimpo = salarioStr
                .replace("R$", "")     // Remove "R$"
                .replace(".", "")     // Remove ".” (separador de milhar)
                .replace(",", ".")     // Troca "," (decimal) por "."
                .trim();               // Remove espaços

        if (salarioLimpo.isEmpty()) {
            throw new NumberFormatException("Salário não pode ser vazio");
        }

        return Double.parseDouble(salarioLimpo); // Ex: "4500.20"
    }

    /**
     * Salva as alterações, com validação de idade e salário atualizadas.
     */
    public void salvarAlteracoes() {
        try {
            // 1. Coletar dados da View
            String nome = view.getNome();
            String idadeStr = view.getIdade();
            int campusIdx = view.getCampusIndex();
            String campus = view.getCampusValor();
            String cpf = view.getCpf();
            String contato = view.getContato();
            int tituloIdx = view.getTituloIndex();
            String titulo = view.getTituloValor();
            String salarioStr = view.getSalario(); // Ex: "R$ 4.500,20"

            String cpfLimpo = validarFormatado(cpf);
            String contatoLimpo = validarFormatado(contato);

            // --- INÍCIO DA VALIDAÇÃO ---
            if (nome.length() < TAMANHO_MIN_NOME) {
                throw new Mensagens("Nome deve conter ao menos " + TAMANHO_MIN_NOME + " caracteres.");
            }
            if (campusIdx == 0) {
                throw new Mensagens("Escolha o campus");
            }
            if (cpfLimpo.length() != TAMANHO_CPF) {
                throw new Mensagens("O campo CPF deve possuir " + TAMANHO_CPF + " caracteres numéricos");
            }
            if (cpfJaCadastrado(cpf, this.idProfessor)) {
                throw new Mensagens("CPF já cadastrado no sistema");
            }
            if (contatoLimpo.length() != TAMANHO_CONTATO) {
                throw new Mensagens("O campo contato deve possuir " + TAMANHO_CONTATO + " caracteres numéricos");
            }

            int idade;
            try {
                idade = Integer.parseInt(idadeStr);
            } catch (NumberFormatException e) {
                throw new Mensagens("Idade deve ser um número.");
            }
            if (idade < IDADE_MINIMA) {
                throw new Mensagens("Idade deve ser no mínimo " + IDADE_MINIMA + " anos.");
            }
            if (idade > IDADE_MAXIMA) { // ATUALIZADO
                throw new Mensagens("Idade deve ser no máximo " + IDADE_MAXIMA + " anos.");
            }

            // --- LÓGICA DE SALÁRIO ATUALIZADA ---
            double salario;
            try {
                salario = parseSalario(salarioStr);
            } catch (NumberFormatException e) {
                throw new Mensagens("Salário deve ser um número válido (ex: 4500,20).");
            }

            if (salario < SALARIO_MINIMO) {
                throw new Mensagens("Salário deve ser no mínimo R$ " + SALARIO_MINIMO + ".");
            }
            // --- Fim da lógica de salário ---

            if (tituloIdx == 0) {
                throw new Mensagens("Defina um título");
            }
            // --- FIM DA VALIDAÇÃO ---

            Professor profAtualizado = new Professor(campus, cpf, contato, titulo, salario, this.idProfessor, nome, idade);

            if (professorDAO.UpdateProfessorBD(profAtualizado)) {
                view.exibirMensagemSucesso("Professor alterado com sucesso!");
                view.fecharJanela();
            } else {
                view.exibirMensagemErro("Erro ao atualizar professor no banco.");
            }

        } catch (Mensagens erro) {
            view.exibirMensagemErro(erro.getMessage());
        } catch (Exception e) {
            view.exibirMensagemErro("Erro inesperado: " + e.getMessage());
        }
    }

    private String validarFormatado(String input) {
        if (input == null) return "";
        return input.replaceAll("[^0-9]", "");
    }

    private boolean cpfJaCadastrado(String cpf, int idAtual) {
        ArrayList<Professor> lista = professorDAO.getMinhaLista();
        for (Professor prof : lista) {
            if (prof.getCpf().equals(cpf) && prof.getId() != idAtual) {
                return true;
            }
        }
        return false;
    }
}