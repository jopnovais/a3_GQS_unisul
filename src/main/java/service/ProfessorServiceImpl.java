package service;

import model.Professor;
import repository.ProfessorRepository;
import service.exception.ValidacaoException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementação da interface ProfessorService.
 * Fornece serviços de negócio para operações com professores, incluindo validações e regras de negócio.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public class ProfessorServiceImpl implements ProfessorService {
    
    /**
     * Repositório responsável pelas operações de persistência de professores.
     */
    private final ProfessorRepository professorRepository;
    
    /**
     * Construtor que recebe o repositório de professores via injeção de dependência.
     * 
     * @param professorRepository O repositório de professores a ser utilizado
     */
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    
    /**
     * Salva um novo professor no sistema após validar seus dados.
     * 
     * @param professor O professor a ser salvo
     * @throws ValidacaoException Se os dados do professor forem inválidos
     */
    @Override
    public void salvar(Professor professor) throws ValidacaoException {
        validarProfessor(professor, true);
        professorRepository.save(professor);
    }
    
    /**
     * Atualiza os dados de um professor existente no sistema após validar seus dados.
     * 
     * @param professor O professor com os dados atualizados
     * @throws ValidacaoException Se os dados do professor forem inválidos ou o ID não for informado
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
     * Exclui um professor do sistema pelo ID.
     * 
     * @param id O identificador único do professor a ser excluído
     */
    @Override
    public void excluir(int id) {
        professorRepository.delete(id);
    }
    
    /**
     * Busca um professor pelo ID.
     * 
     * @param id O identificador único do professor
     * @return O professor encontrado, ou null se não encontrado
     */
    @Override
    public Professor buscarPorId(int id) {
        return professorRepository.findById(id);
    }
    
    /**
     * Busca um professor pelo CPF.
     * 
     * @param cpf O CPF do professor (11 dígitos numéricos)
     * @return O professor encontrado, ou null se não encontrado
     */
    @Override
    public Professor buscarPorCpf(String cpf) {
        return professorRepository.findByCpf(cpf);
    }
    
    /**
     * Lista todos os professores cadastrados no sistema.
     * 
     * @return Lista contendo todos os professores cadastrados
     */
    @Override
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }
    
    /**
     * Calcula a idade com base na data de nascimento fornecida.
     * Considera se o aniversário já ocorreu no ano atual.
     * 
     * @param dataNascimento A data de nascimento
     * @return A idade calculada em anos
     * @throws ValidacaoException Se a data de nascimento for nula
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
     * Valida e formata uma string, removendo caracteres não numéricos.
     * 
     * @param input A string a ser formatada
     * @return String contendo apenas os caracteres numéricos, ou string vazia se input for null
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
     * Valida os dados de um professor de acordo com as regras de negócio.
     * 
     * @param professor O professor a ser validado
     * @param isNovo    Indica se é um novo professor (true) ou uma atualização (false)
     * @throws ValidacaoException Se algum dado do professor for inválido
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
