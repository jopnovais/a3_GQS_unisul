package principal;

import view.TelaLogin;
import view.TelaPrincipal;
import com.formdev.flatlaf.FlatDarkLaf;
import DAO.AlunoDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {
        // Configurar tema padrão (claro)
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar credenciais do MySQL diretamente
        // Altere aqui com suas credenciais do MySQL
        TelaLogin.userDB = "admin";
        TelaLogin.passwordDB = "admin";

        // Verificar conexão com o banco de dados
        AlunoDAO teste = new AlunoDAO();
        Connection conexao = teste.getConexao();

        if (conexao != null) {
            try {
                conexao.close(); // Fechar conexão de teste
            } catch (Exception e) {
                // Ignorar erro ao fechar conexão de teste
            }

            // Conexão bem-sucedida, abrir tela principal
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new TelaPrincipal().setVisible(true);
                }
            });
        } else {
            // Erro na conexão
            JOptionPane.showMessageDialog(null,
                    "Erro ao conectar com o banco de dados MySQL!\n\n"
                    + "Verifique se:\n"
                    + "- O MySQL está rodando\n"
                    + "- O banco 'db_escola' foi criado\n"
                    + "- As credenciais estão corretas no arquivo Principal.java\n"
                    + "- A porta 3306 está acessível",
                    "Erro de Conexão",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
