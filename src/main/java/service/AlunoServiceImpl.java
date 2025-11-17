package service;

import model.Aluno;
import repository.AlunoRepository;
import service.exception.ValidacaoException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementação da interface {@link AlunoService}, responsável por aplicar
 * regras de negócio relacionadas ao gerenciamento de alunos antes de delegar
 * as operações de persistência ao {@link AlunoRepository}.
 */
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;

    /**
     * Construtor que injeta o repositório responsável pela persistência de alunos.
     *
     * @param alunoRepository instância de {@link AlunoRepository} utilizada pela camada de serviço.
     */
    public AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void salvar(Aluno aluno) throws ValidacaoException {
        validarAluno(aluno);
        alunoRepository.save(aluno);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public void excluir(int id) {
        alunoRepository.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Aluno buscarPorId(int id) {
        return alunoRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    /**
     * {@inheritDoc}
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
     * Realiza as validações das informações do aluno antes de persistir ou atualizar.
     *
     * @param aluno objeto {@link Aluno} a ser validado.
     * @throws ValidacaoException caso qualquer regra de validação seja violada.
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
