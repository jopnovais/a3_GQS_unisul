package service;

import model.Professor;
import repository.ProfessorRepository;
import service.exception.ValidacaoException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementação da interface {@link ProfessorService}, responsável por aplicar
 * regras de negócio, validações e delegar operações ao repositório de professores.
 */
public class ProfessorServiceImpl implements ProfessorService {
    
    private final ProfessorRepository professorRepository;
    
    /**
     * Construtor que recebe uma instância de {@link ProfessorRepository}.
     *
     * @param professorRepository repositório responsável pelo acesso aos dados.
     */
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    
    /**
     * Salva um novo professor após validação completa dos dados.
     *
     * @param professor instância a ser persistida.
     * @throws ValidacaoException caso alguma validação seja violada.
     */
    @Override
    public void salvar(Professor professor) throws ValidacaoException {
        validarProfessor(professor, true);
        professorRepository.save(professor);
    }
    
    /**
     * Atualiza os dados de um professor já existente.
     *
     * @param professor professor contendo os dados atualizados.
     * @throws ValidacaoException caso os dados sejam inválidos ou o ID esteja ausente.
     */
    @Override
    public void atualizar(Professor professor) throws ValidacaoException {
        validarProfessor(professor, false);
        if (professor.getId() <= 0) {
            throw new ValidacaoException("ID do professor é obrigatório para atualização.");
        }
        professorRepository.update(professor);
    }
    
    /**
     * Exclui um professor com base no seu ID.
     *
     * @param id identificador do professor.
     */
    @Override
    public void excluir(int id) {
        professorRepository.delete(id);
    }
    
    /**
     * Busca um professor pelo seu identificador.
     *
     * @param id ID do professor.
     * @return professor encontrado ou {@code null} caso não exista.
     */
    @Override
    public Professor buscarPorId(int id) {
        return professorRepository.findById(id);
    }
    
    /**
     * Pesquisa um professor pelo seu CPF.
     *
     * @param cpf CPF informado.
     * @return professor correspondente ou {@code null} caso não exista.
     */
    @Override
    public Professor buscarPorCpf(String cpf) {
        return professorRepository.findByCpf(cpf);
    }
    
    /**
     * Retorna todos os professores cadastrados.
     *
     * @return lista contendo todos os professores.
     */
    @Override
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }
    
    /**
     * Calcula a idade com base na data de nascimento fornecida.
     *
     * @param dataNascimento data de nascimento.
     * @return idade calculada.
     * @throws ValidacaoException caso a data seja nula.
     */
    @Override
    public int calcularIdade(java.util.Date dataNascimento) {
        if (dataNascimento == null) {
            throw new ValidacaoException("Data de nascimento não pode ser nula.");
        }
        
        Calendar dataNasc = new GregorianCalendar();
        dataNasc.setTime(dataNascimento);
        
        Calendar hoje = Calendar.getInstance();
        
        int idade = hoje.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);
        dataNasc.add(Calendar.YEAR, idade);
        
        if (hoje.before(dataNasc)) {
            idade--;
        }
        
        return idade;
    }
    
    /**
     * Remove todos os caracteres não numéricos de uma string.
     * Útil para campos como CPF, telefone e outros formatados.
     *
     * @param input string original.
     * @return string contendo apenas dígitos.
     */
    @Override
    public String validarFormatado(String input) {
        if (input == null) {
            return "";
        }
        
        StringBuilder str = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ("0123456789".indexOf(c) >= 0) {
                str.append(c);
            }
        }
        
        return str.toString();
    }
    
    /**
     * Valida todos os campos obrigatórios e regras específicas de um professor.
     *
     * @param professor professor a validar.
     * @param isNovo indica se é um cadastro novo (true) ou atualização (false).
     * @throws ValidacaoException caso qualquer regra seja violada.
     */
    private void validarProfessor(Professor professor, boolean isNovo) throws ValidacaoException {
        if (professor == null) {
            throw new ValidacaoException("Professor não pode ser nulo.");
        }
        
        if (professor.getNome() == null || professor.getNome().trim().isEmpty()) {
            throw new ValidacaoException("Nome é obrigatório.");
        }
        
        if (professor.getNome().length() < 2) {
            throw new ValidacaoException("Nome deve conter ao menos 2 caracteres.");
        }
        
        if (professor.getCampus() == null || professor.getCampus().trim().isEmpty() || professor.getCampus().equals("-")) {
            throw new ValidacaoException("Campus é obrigatório.");
        }
        
        if (professor.getCpf() == null || professor.getCpf().trim().isEmpty()) {
            throw new ValidacaoException("CPF é obrigatório.");
        }
        
        String cpfFormatado = validarFormatado(professor.getCpf());
        if (cpfFormatado.length() != 11) {
            throw new ValidacaoException("O campo CPF deve possuir 11 caracteres numéricos.");
        }
        
        if (isNovo) {
            Professor existente = professorRepository.findByCpf(professor.getCpf());
            if (existente != null) {
                throw new ValidacaoException("CPF já cadastrado no sistema.");
            }
        } else {
            Professor existenteComMesmoCpf = professorRepository.findByCpf(professor.getCpf());
            if (existenteComMesmoCpf != null && existenteComMesmoCpf.getId() != professor.getId()) {
                throw new ValidacaoException("CPF já cadastrado no sistema.");
            }
        }
        
        if (professor.getContato() == null || professor.getContato().trim().isEmpty()) {
            throw new ValidacaoException("Contato é obrigatório.");
        }
        
        String contatoFormatado = validarFormatado(professor.getContato());
        if (contatoFormatado.length() != 11) {
            throw new ValidacaoException("O campo contato deve possuir 11 caracteres numéricos.");
        }
        
        if (professor.getIdade() < 11) {
            throw new ValidacaoException("Idade inválida. Deve ser maior ou igual a 11 anos.");
        }
        
        if (professor.getSalario() <= 0) {
            throw new ValidacaoException("Salário deve ser maior que zero.");
        }
        
        String salarioFormatado = validarFormatado(String.valueOf((int) professor.getSalario()));
        if (salarioFormatado.length() < 4) {
            throw new ValidacaoException("O campo salário deve possuir no mínimo 4 caracteres numéricos.");
        }
        
        if (professor.getTitulo() == null || professor.getTitulo().trim().isEmpty() || professor.getTitulo().equals("-")) {
            throw new ValidacaoException("Título é obrigatório.");
        }
    }
}
