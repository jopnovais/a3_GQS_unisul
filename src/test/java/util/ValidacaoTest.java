package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de Validação Unitários para a classe ValidacaoUtil.
 * Estes testes validam as regras de negócio do sistema sem depender do banco de dados.
 */
@DisplayName("Testes de Validação - ValidacaoUtil")
class ValidacaoTest {

    // ========== TESTES DE VALIDAÇÃO DE CPF ==========

    @Test
    @DisplayName("Deve validar CPF válido com formatação")
    void deveValidarCPFComFormatacao() {
        assertTrue(ValidacaoUtil.validarCPF("123.456.789-09"));
    }

    @Test
    @DisplayName("Deve validar CPF válido sem formatação")
    void deveValidarCPFSemFormatacao() {
        assertTrue(ValidacaoUtil.validarCPF("12345678909"));
    }

    @Test
    @DisplayName("Deve rejeitar CPF inválido (dígitos verificadores incorretos)")
    void deveRejeitarCPFInvalido() {
        assertFalse(ValidacaoUtil.validarCPF("123.456.789-00"));
        assertFalse(ValidacaoUtil.validarCPF("11111111111"));
    }

    @Test
    @DisplayName("Deve rejeitar CPF com todos os dígitos iguais")
    void deveRejeitarCPFComDigitosIguais() {
        assertFalse(ValidacaoUtil.validarCPF("111.111.111-11"));
        assertFalse(ValidacaoUtil.validarCPF("22222222222"));
    }

    @Test
    @DisplayName("Deve rejeitar CPF com tamanho incorreto")
    void deveRejeitarCPFComTamanhoIncorreto() {
        assertFalse(ValidacaoUtil.validarCPF("123456789")); // Muito curto
        assertFalse(ValidacaoUtil.validarCPF("123456789012")); // Muito longo
    }

    @Test
    @DisplayName("Deve rejeitar CPF nulo ou vazio")
    void deveRejeitarCPFNuloOuVazio() {
        assertFalse(ValidacaoUtil.validarCPF(null));
        assertFalse(ValidacaoUtil.validarCPF(""));
    }

    // ========== TESTES DE VALIDAÇÃO DE IDADE ==========

    @Test
    @DisplayName("Deve validar idade de aluno válida (mínimo 11 anos)")
    void deveValidarIdadeAlunoValida() {
        assertTrue(ValidacaoUtil.validarIdadeAluno(11));
        assertTrue(ValidacaoUtil.validarIdadeAluno(20));
        assertTrue(ValidacaoUtil.validarIdadeAluno(50));
    }

    @Test
    @DisplayName("Deve rejeitar idade de aluno inválida (menor que 11)")
    void deveRejeitarIdadeAlunoInvalida() {
        assertFalse(ValidacaoUtil.validarIdadeAluno(10));
        assertFalse(ValidacaoUtil.validarIdadeAluno(0));
        assertFalse(ValidacaoUtil.validarIdadeAluno(-5));
    }

    @Test
    @DisplayName("Deve validar idade de professor válida (mínimo 18 anos)")
    void deveValidarIdadeProfessorValida() {
        assertTrue(ValidacaoUtil.validarIdadeProfessor(18));
        assertTrue(ValidacaoUtil.validarIdadeProfessor(30));
        assertTrue(ValidacaoUtil.validarIdadeProfessor(65));
    }

    @Test
    @DisplayName("Deve rejeitar idade de professor inválida (menor que 18)")
    void deveRejeitarIdadeProfessorInvalida() {
        assertFalse(ValidacaoUtil.validarIdadeProfessor(17));
        assertFalse(ValidacaoUtil.validarIdadeProfessor(10));
    }

    @Test
    @DisplayName("Deve validar idade com range customizado")
    void deveValidarIdadeComRangeCustomizado() {
        assertTrue(ValidacaoUtil.validarIdade(25, 18, 65));
        assertFalse(ValidacaoUtil.validarIdade(17, 18, 65));
        assertFalse(ValidacaoUtil.validarIdade(66, 18, 65));
    }

    // ========== TESTES DE VALIDAÇÃO DE NOME ==========

    @Test
    @DisplayName("Deve validar nome válido (mínimo 2 caracteres)")
    void deveValidarNomeValido() {
        assertTrue(ValidacaoUtil.validarNomePessoa("João"));
        assertTrue(ValidacaoUtil.validarNomePessoa("Maria Silva"));
        assertTrue(ValidacaoUtil.validarNomePessoa("Ana"));
    }

    @Test
    @DisplayName("Deve rejeitar nome inválido (muito curto)")
    void deveRejeitarNomeMuitoCurto() {
        assertFalse(ValidacaoUtil.validarNomePessoa("A"));
        assertFalse(ValidacaoUtil.validarNomePessoa(""));
    }

    @Test
    @DisplayName("Deve rejeitar nome nulo")
    void deveRejeitarNomeNulo() {
        assertFalse(ValidacaoUtil.validarNomePessoa(null));
    }

    @Test
    @DisplayName("Deve validar nome com tamanho mínimo customizado")
    void deveValidarNomeComTamanhoMinimoCustomizado() {
        assertTrue(ValidacaoUtil.validarNome("João Silva", 3));
        assertFalse(ValidacaoUtil.validarNome("Jo", 3));
    }

    // ========== TESTES DE VALIDAÇÃO DE CONTATO ==========

    @Test
    @DisplayName("Deve validar contato válido (11 dígitos)")
    void deveValidarContatoValido() {
        assertTrue(ValidacaoUtil.validarContato("(48) 99999-9999"));
        assertTrue(ValidacaoUtil.validarContato("48999999999"));
    }

    @Test
    @DisplayName("Deve rejeitar contato inválido")
    void deveRejeitarContatoInvalido() {
        assertFalse(ValidacaoUtil.validarContato("4899999999")); // 10 dígitos
        assertFalse(ValidacaoUtil.validarContato("489999999999")); // 12 dígitos
        assertFalse(ValidacaoUtil.validarContato(null));
        assertFalse(ValidacaoUtil.validarContato(""));
    }

    // ========== TESTES DE VALIDAÇÃO DE SALÁRIO ==========

    @Test
    @DisplayName("Deve validar salário válido (maior que zero)")
    void deveValidarSalarioValido() {
        assertTrue(ValidacaoUtil.validarSalario(1000.0));
        assertTrue(ValidacaoUtil.validarSalario(5000.50));
        assertTrue(ValidacaoUtil.validarSalario(0.01));
    }

    @Test
    @DisplayName("Deve rejeitar salário inválido (zero ou negativo)")
    void deveRejeitarSalarioInvalido() {
        assertFalse(ValidacaoUtil.validarSalario(0));
        assertFalse(ValidacaoUtil.validarSalario(-100));
    }

    // ========== TESTES DE VALIDAÇÃO DE CURSO ==========

    @Test
    @DisplayName("Deve validar curso válido")
    void deveValidarCursoValido() {
        assertTrue(ValidacaoUtil.validarCurso("Administração"));
        assertTrue(ValidacaoUtil.validarCurso("Ciências da Computação"));
    }

    @Test
    @DisplayName("Deve rejeitar curso inválido")
    void deveRejeitarCursoInvalido() {
        assertFalse(ValidacaoUtil.validarCurso(null));
        assertFalse(ValidacaoUtil.validarCurso(""));
        assertFalse(ValidacaoUtil.validarCurso("-"));
        assertFalse(ValidacaoUtil.validarCurso("   "));
    }

    // ========== TESTES DE VALIDAÇÃO DE FASE ==========

    @Test
    @DisplayName("Deve validar fase válida (1 a 10)")
    void deveValidarFaseValida() {
        assertTrue(ValidacaoUtil.validarFase(1));
        assertTrue(ValidacaoUtil.validarFase(5));
        assertTrue(ValidacaoUtil.validarFase(10));
    }

    @Test
    @DisplayName("Deve rejeitar fase inválida")
    void deveRejeitarFaseInvalida() {
        assertFalse(ValidacaoUtil.validarFase(0));
        assertFalse(ValidacaoUtil.validarFase(11));
        assertFalse(ValidacaoUtil.validarFase(-1));
    }

    // ========== TESTES DE VALIDAÇÃO DE CAMPUS ==========

    @Test
    @DisplayName("Deve validar campus válido")
    void deveValidarCampusValido() {
        assertTrue(ValidacaoUtil.validarCampus("Pedra Branca"));
        assertTrue(ValidacaoUtil.validarCampus("Tubarão"));
    }

    @Test
    @DisplayName("Deve rejeitar campus inválido")
    void deveRejeitarCampusInvalido() {
        assertFalse(ValidacaoUtil.validarCampus(null));
        assertFalse(ValidacaoUtil.validarCampus(""));
        assertFalse(ValidacaoUtil.validarCampus("-"));
    }

    // ========== TESTES DE VALIDAÇÃO DE TÍTULO ==========

    @Test
    @DisplayName("Deve validar título válido")
    void deveValidarTituloValido() {
        assertTrue(ValidacaoUtil.validarTitulo("Mestrado"));
        assertTrue(ValidacaoUtil.validarTitulo("Doutorado"));
    }

    @Test
    @DisplayName("Deve rejeitar título inválido")
    void deveRejeitarTituloInvalido() {
        assertFalse(ValidacaoUtil.validarTitulo(null));
        assertFalse(ValidacaoUtil.validarTitulo(""));
        assertFalse(ValidacaoUtil.validarTitulo("-"));
    }

    // ========== TESTES DE VALIDAÇÃO DE CAMPO OBRIGATÓRIO ==========

    @Test
    @DisplayName("Deve validar campo obrigatório preenchido")
    void deveValidarCampoObrigatorioPreenchido() {
        assertTrue(ValidacaoUtil.validarCampoObrigatorio("Valor"));
        assertTrue(ValidacaoUtil.validarCampoObrigatorio("  Valor  "));
    }

    @Test
    @DisplayName("Deve rejeitar campo obrigatório vazio")
    void deveRejeitarCampoObrigatorioVazio() {
        assertFalse(ValidacaoUtil.validarCampoObrigatorio(null));
        assertFalse(ValidacaoUtil.validarCampoObrigatorio(""));
        assertFalse(ValidacaoUtil.validarCampoObrigatorio("   "));
    }
}

