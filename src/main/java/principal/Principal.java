package principal;

import view.TelaPrincipal;
import com.formdev.flatlaf.FlatDarkLaf;
import db.ConnectionFactory;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe principal responsável por inicializar a interface gráfica da aplicação
 * e validar a conexão com o banco de dados SQLite antes de permitir o uso do sistema.
 *
 * <p>O fluxo de inicialização segue os seguintes passos:
 * <ol>
 *   <li>Configuração do FlatDarkLaf (tema escuro).</li>
 *   <li>Tentativa de conexão com o banco por meio de {@link ConnectionFactory}.</li>
 *   <li>Se a conexão for bem-sucedida, abre {@link TelaPrincipal}.</li>
 *   <li>Se houver falha, exibe uma mensagem de erro e encerra a aplicação.</li>
 * </ol>
 *
 * <p>Esta classe contém apenas o método {@code main} e atua como ponto de entrada do sistema.
 */
public class Principal {

    /** Logger da classe, utilizado para registrar erros e eventos importantes. */
    private static final Logger LOGGER = Logger.getLogger(Principal.class.getName());

    /**
     * Método principal da aplicação. Responsável por:
     * <ul>
     *   <li>Configurar o tema visual (FlatDarkLaf).</li>
     *   <li>Testar a conexão com o banco SQLite.</li>
     *   <li>Inicializar a janela principal caso a conexão seja bem-sucedida.</li>
     *   <li>Exibir mensagens de erro caso algo dê errado.</li>
     * </ul>
     *
     * @param args argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao inicializar o FlatLaf", e);
        }

        try {
            Connection conexao = ConnectionFactory.getConnection();

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (Exception e) {
                    // Ignora erro ao fechar a conexão
                }

                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new TelaPrincipal().setVisible(true);
                    }
                });
            } else {
                JOptionPane.showMessageDialog(null,
                        "Erro ao conectar com o banco de dados SQLite!\n\n"
                        + "Verifique se há permissões para criar o arquivo 'projeto_faculdade.db'",
                        "Erro de Conexão",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao conectar com o banco de dados SQLite!\n\n"
                    + "Detalhes: " + e.getMessage(),
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
