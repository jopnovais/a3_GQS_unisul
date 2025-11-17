package repository;

import db.ConnectionFactory;
import repository.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe base para repositórios de acesso a dados. <br><br>
 * 
 * Fornece métodos utilitários comuns, como:
 * <ul>
 *   <li>Obter conexão com o banco;</li>
 *   <li>Executar consultas de maior ID;</li>
 *   <li>Executar operações de exclusão;</li>
 *   <li>Validação de nomes de tabelas;</li>
 *   <li>Construção dinâmica de queries simples.</li>
 * </ul>
 * 
 * Essa classe centraliza comportamentos repetitivos, reduz duplicação
 * e melhora a consistência dos repositórios concretos.
 */
public abstract class AbstractRepository {

    /**
     * Obtém uma conexão ativa com o banco de dados utilizando a
     * {@link ConnectionFactory}.
     *
     * @return uma instância de {@link Connection}
     * @throws SQLException caso a conexão não possa ser estabelecida
     */
    protected Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }

    /**
     * Executa uma consulta SQL para buscar o maior valor de ID em uma tabela.
     * O nome da tabela é validado previamente para garantir segurança.
     *
     * @param tableName nome da tabela onde a consulta será realizada
     * @return o maior ID encontrado ou 0 caso a tabela esteja vazia
     * @throws IllegalArgumentException se o nome da tabela for inválido
     * @throws DataAccessException se ocorrer um erro ao executar a consulta
     */
    protected int executeMaxIdQuery(String tableName) {
        if (!isValidTableName(tableName)) {
            throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
        }

        String sql = buildMaxIdQuery(tableName);

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             java.sql.ResultSet res = stmt.executeQuery(sql)) {

            if (res.next()) {
                return res.getInt("max_id");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar maior ID: " + e.getMessage(), e);
        }

        return 0;
    }

    /**
     * Constrói dinamicamente a query SQL para busca de maior ID, de acordo com
     * a tabela informada.
     *
     * @param tableName nome da tabela
     * @return string com a query SQL completa
     * @throws IllegalArgumentException caso a tabela seja desconhecida
     */
    private String buildMaxIdQuery(String tableName) {
        if ("tb_alunos".equals(tableName)) {
            return "SELECT MAX(id) as max_id FROM tb_alunos";
        } else if ("tb_professores".equals(tableName)) {
            return "SELECT MAX(id) as max_id FROM tb_professores";
        }
        throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
    }

    /**
     * Verifica se o nome informado corresponde a uma tabela válida
     * do banco de dados.
     *
     * @param tableName nome da tabela
     * @return true se for válida, false caso contrário
     */
    private boolean isValidTableName(String tableName) {
        return "tb_alunos".equals(tableName) || "tb_professores".equals(tableName);
    }

    /**
     * Executa a exclusão de um registro pelo ID em uma tabela específica.
     * O nome da tabela é validado antes da execução.
     *
     * @param tableName nome da tabela
     * @param id identificador do registro a ser excluído
     * @return true se ao menos um registro foi removido, false caso contrário
     * @throws IllegalArgumentException se o nome da tabela for inválido
     * @throws DataAccessException se ocorrer erro no banco durante a exclusão
     */
    protected boolean executeDelete(String tableName, int id) {
        if (!isValidTableName(tableName)) {
            throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
        }

        String sql = buildDeleteQuery(tableName);

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DataAccessException("Erro ao deletar registro: " + e.getMessage(), e);
        }
    }

    /**
     * Constroi a query SQL de DELETE para a tabela informada.
     *
     * @param tableName nome da tabela
     * @return instrução SQL para remoção
     * @throws IllegalArgumentException caso a tabela seja desconhecida
     */
    private String buildDeleteQuery(String tableName) {
        if ("tb_alunos".equals(tableName)) {
            return "DELETE FROM tb_alunos WHERE id = ?";
        } else if ("tb_professores".equals(tableName)) {
            return "DELETE FROM tb_professores WHERE id = ?";
        }
        throw new IllegalArgumentException("Nome de tabela inválido: " + tableName);
    }
}
