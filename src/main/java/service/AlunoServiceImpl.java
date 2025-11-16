package service;

import model.Aluno;
import repository.AlunoRepository;
import service.exception.ValidacaoException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementação da interface AlunoService.
 * Fornece serviços de negócio para operações com alunos, incluindo validações e regras de negócio.
 * 
 * @author Sistema GQS
 * @version 1.0
 */
public class AlunoServiceImpl implements AlunoService {

    /**
     * Repositório responsável pelas operações de persistência de alunos.
     */
    private final AlunoRepository alunoRepository;

    /**
     * Construtor que recebe o repositório de alunos via injeção de dependência.
     * 
     * @param alunoRepository O repositório de alunos a ser utilizado
     */
    public AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    /**
     * Salva um novo aluno no sistema após validar seus dados.
     * 
     * @param aluno O aluno a ser salvo
     * @throws ValidacaoException Se os dados do aluno forem inválidos
     */
    @Override
    public void salvar(Aluno aluno) throws ValidacaoException {
        validarAluno(aluno);
        alunoRepository.save(aluno);
    }

    /**
     * Atualiza os dados de um aluno existente no sistema após validar seus dados.
     * 
     * @param aluno O aluno com os dados atualizados
     * @throws ValidacaoException Se os dados do aluno forem inválidos ou o ID não for informado
     */
    @Override
    public void atualizar(Aluno aluno) throws ValidacaoException {
        validarAluno(aluno);
        if (aluno.getId() <= 0) {
            throw new ValidacaoException("ID do aluno é obrigatório para atualização.");
        }
        alunoRepository.update(aluno);
    }

    /**
     * Exclui um aluno do sistema pelo ID.
     * 
     * @param id O identificador único do aluno a ser excluído
     */
    @Override
    public void excluir(int id) {
        alunoRepository.delete(id);
    }

    /**
     * Busca um aluno pelo ID.
     * 
     * @param id O identificador único do aluno
     * @return O aluno encontrado, ou null se não encontrado
     */
    @Override
    public Aluno buscarPorId(int id) {
        return alunoRepository.findById(id);
    }

    /**
     * Lista todos os alunos cadastrados no sistema.
     * 
     * @return Lista contendo todos os alunos cadastrados
     */
    @Override
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
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
     * Valida os dados de um aluno de acordo com as regras de negócio.
     * 
     * @param aluno O aluno a ser validado
     * @throws ValidacaoException Se algum dado do aluno for inválido
     */
    private void validarAluno(Aluno aluno) throws ValidacaoException {
        if (aluno == null) {
            throw new ValidacaoException("Aluno não pode ser nulo.");
        }

        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            throw new ValidacaoException("Nome é obrigatório.");
        }

        if (aluno.getNome().length() < 2) {
            throw new ValidacaoException("Nome deve conter ao menos 2 caracteres.");
        }

        if (!aluno.getNome().matches(".*\\p{L}.*")) {
            throw new ValidacaoException("Nome é inválido (deve conter ao menos uma letra).");
        }

        if (aluno.getIdade() < 11) {
            throw new ValidacaoException("Idade inválida. Deve ser maior ou igual a 11 anos.");
        }

        if (aluno.getCurso() == null || aluno.getCurso().trim().isEmpty() || aluno.getCurso().equals("-")) {
            throw new ValidacaoException("Curso é obrigatório.");
        }

        if (aluno.getFase() < 1 || aluno.getFase() > 10) {
            throw new ValidacaoException("Fase deve estar entre 1 e 10.");
        }
    }
}
