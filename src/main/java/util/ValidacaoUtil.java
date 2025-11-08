package util;

/**
 * Classe utilitária para validação de dados do sistema.
 * Contém métodos estáticos para validar CPF, idade, campos obrigatórios, etc.
 */
public class ValidacaoUtil {

    private ValidacaoUtil() {
        // Classe utilitária - não deve ser instanciada
    }

    /**
     * Valida se um CPF é válido (formato e dígitos verificadores).
     * 
     * @param cpf CPF a ser validado (com ou sem formatação)
     * @return true se o CPF é válido, false caso contrário
     */
    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return false;
        }

        // Remove caracteres não numéricos
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        // Verifica se tem 11 dígitos
        if (cpfLimpo.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (CPF inválido)
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Valida os dígitos verificadores
        try {
            int[] digitos = new int[11];
            for (int i = 0; i < 11; i++) {
                digitos[i] = Integer.parseInt(cpfLimpo.substring(i, i + 1));
            }

            // Calcula o primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += digitos[i] * (10 - i);
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 >= 10) {
                digito1 = 0;
            }

            // Calcula o segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += digitos[i] * (11 - i);
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 >= 10) {
                digito2 = 0;
            }

            // Verifica se os dígitos calculados correspondem aos informados
            return (digito1 == digitos[9] && digito2 == digitos[10]);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida se uma idade está dentro do range permitido.
     * 
     * @param idade Idade a ser validada
     * @param idadeMinima Idade mínima permitida
     * @param idadeMaxima Idade máxima permitida
     * @return true se a idade é válida, false caso contrário
     */
    public static boolean validarIdade(int idade, int idadeMinima, int idadeMaxima) {
        return idade >= idadeMinima && idade <= idadeMaxima;
    }

    /**
     * Valida se uma idade de aluno é válida (mínimo 11 anos).
     * 
     * @param idade Idade a ser validada
     * @return true se a idade é válida para aluno, false caso contrário
     */
    public static boolean validarIdadeAluno(int idade) {
        return validarIdade(idade, 11, 150);
    }

    /**
     * Valida se uma idade de professor é válida (mínimo 18 anos).
     * 
     * @param idade Idade a ser validada
     * @return true se a idade é válida para professor, false caso contrário
     */
    public static boolean validarIdadeProfessor(int idade) {
        return validarIdade(idade, 18, 150);
    }

    /**
     * Valida se um nome é válido (não nulo, não vazio, e tem pelo menos o tamanho mínimo).
     * 
     * @param nome Nome a ser validado
     * @param tamanhoMinimo Tamanho mínimo permitido
     * @return true se o nome é válido, false caso contrário
     */
    public static boolean validarNome(String nome, int tamanhoMinimo) {
        if (nome == null) {
            return false;
        }
        String nomeLimpo = nome.trim();
        return nomeLimpo.length() >= tamanhoMinimo && !nomeLimpo.isEmpty();
    }

    /**
     * Valida se um nome de aluno/professor é válido (mínimo 2 caracteres).
     * 
     * @param nome Nome a ser validado
     * @return true se o nome é válido, false caso contrário
     */
    public static boolean validarNomePessoa(String nome) {
        return validarNome(nome, 2);
    }

    /**
     * Valida se um telefone/contato é válido (11 dígitos numéricos).
     * 
     * @param contato Contato a ser validado (com ou sem formatação)
     * @return true se o contato é válido, false caso contrário
     */
    public static boolean validarContato(String contato) {
        if (contato == null || contato.isEmpty()) {
            return false;
        }
        String contatoLimpo = contato.replaceAll("[^0-9]", "");
        return contatoLimpo.length() == 11;
    }

    /**
     * Valida se um salário é válido (maior que zero).
     * 
     * @param salario Salário a ser validado
     * @return true se o salário é válido, false caso contrário
     */
    public static boolean validarSalario(double salario) {
        return salario > 0;
    }

    /**
     * Valida se um curso é válido (não nulo e não vazio).
     * 
     * @param curso Curso a ser validado
     * @return true se o curso é válido, false caso contrário
     */
    public static boolean validarCurso(String curso) {
        return curso != null && !curso.trim().isEmpty() && !curso.equals("-");
    }

    /**
     * Valida se uma fase é válida (entre 1 e 10).
     * 
     * @param fase Fase a ser validada
     * @return true se a fase é válida, false caso contrário
     */
    public static boolean validarFase(int fase) {
        return fase >= 1 && fase <= 10;
    }

    /**
     * Valida se um campus é válido (não nulo e não vazio).
     * 
     * @param campus Campus a ser validado
     * @return true se o campus é válido, false caso contrário
     */
    public static boolean validarCampus(String campus) {
        return campus != null && !campus.trim().isEmpty() && !campus.equals("-");
    }

    /**
     * Valida se um título é válido (não nulo e não vazio).
     * 
     * @param titulo Título a ser validado
     * @return true se o título é válido, false caso contrário
     */
    public static boolean validarTitulo(String titulo) {
        return titulo != null && !titulo.trim().isEmpty() && !titulo.equals("-");
    }

    /**
     * Valida se um campo obrigatório não está vazio.
     * 
     * @param campo Campo a ser validado
     * @return true se o campo não está vazio, false caso contrário
     */
    public static boolean validarCampoObrigatorio(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }
}

