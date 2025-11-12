package principal;

import view.TelaPrincipal;
import com.formdev.flatlaf.FlatDarkLaf;
import db.ConnectionFactory;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Principal {

    private static final Logger LOGGER = Logger.getLogger(Principal.class.getName());

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
                    // Ignorar
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
