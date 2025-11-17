package view;

import db.ConnectionFactory;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Tela de login do sistema SisUni.
 *
 * <p>
 * Esta interface permite ao usuário validar a conexão com o banco de dados
 * SQLite antes de acessar o sistema principal. Após clicar em LOGIN, o sistema
 * tenta estabelecer conexão utilizando a {@link ConnectionFactory} e, em caso
 * de sucesso, a tela principal é aberta.
 * </p>
 *
 * <p>
 * Alguns campos de usuário e senha estão marcados como {@code @Deprecated},
 * pois não são utilizados neste projeto — o login serve apenas para validar
 * o acesso ao banco de dados.
 * </p>
 */
public class TelaLogin extends javax.swing.JFrame {

    /**
     * Campo de senha (não utilizado).
     *
     * @deprecated este sistema não utiliza autenticação por usuário e senha.
     */

    @Deprecated
    public static String passwordDB = "";

    /**
     * Campo de usuário (não utilizado).
     *
     * @deprecated este sistema não utiliza autenticação por usuário e senha.
     */

    @Deprecated
    public static String userDB = "";

    /**
     * Construtor da tela de login.
     *
     * <p>
     * Inicializa os componentes gráficos e define o botão LOGIN como botão
     * padrão (acionado ao pressionar ENTER).
     * </p>
     */

    public TelaLogin() {
        initComponents();
        getRootPane().setDefaultButton(this.login);
    }

    /**
     * Inicializa os componentes da interface gráfica.
     *
     * <p>
     * Este método é gerado automaticamente pelo NetBeans e contém toda a
     * configuração dos elementos visuais da tela. Não deve ser modificado
     * manualmente.
     * </p>
     */

    @SuppressWarnings("unchecked")
    private void initComponents() {

        login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(51, 255, 51));
        setResizable(false);

        login.setFont(new java.awt.Font("Segoe UI", 0, 18)); 
        login.setText("LOGIN");
        login.setToolTipText("ENTER");
        login.setAlignmentX(0.5F);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SisUni - Sistema de Gerenciamento Universitário");

        password.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); 
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sistema SQLite - Clique em LOGIN");

        user.setFont(new java.awt.Font("Segoe UI", 0, 24)); 
        user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user.setVisible(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10)); 
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("");
        jLabel3.setVisible(false);
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(password)
                                                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addComponent(jLabel3))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(jLabel2)))
                                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(26, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
        );

        login.getAccessibleContext().setAccessibleDescription("Cadastro de Professores");

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Verifica a conexão com o banco de dados SQLite.
     *
     * <p>
     * Tenta estabelecer uma conexão e retorna true se a conexão for bem-sucedida,
     * false caso contrário. Exibe mensagens de erro apropriadas em caso de falha.
     * </p>
     *
     * @return true se a conexão for bem-sucedida, false caso contrário.
     */
    private boolean checarConexao() {
        try {
            Connection conexao = ConnectionFactory.getConnection();
            if (conexao != null) {
                conexao.close();
                JOptionPane.showMessageDialog(rootPane, "Conexão com SQLite efetuada com sucesso!");
                return true;
            } else {
                JOptionPane.showMessageDialog(rootPane, "Conexão falhou!");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao conectar: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ação executada ao clicar no botão LOGIN.
     *
     * <p>
     * Verifica a conexão com o banco de dados e abre a tela principal se a conexão
     * for bem-sucedida.
     * </p>
     *
     * @param evt evento de clique do botão LOGIN.
     */
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        if (checarConexao()) {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
            this.dispose();
        }
    }

    /**
    * Método principal que inicia a tela de login.
     *
     * @param args argumentos de linha de comando (não utilizados).
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField user;
}
