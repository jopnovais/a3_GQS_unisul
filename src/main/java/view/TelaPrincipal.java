package view;

import com.formdev.flatlaf.FlatDarkLaf;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tela principal do sistema SisUni.
 *
 * <p>
 * Esta interface funciona como o hub central do sistema, permitindo que o
 * usuário navegue para os módulos de gerenciamento de alunos e professores.
 * Também oferece acesso à tela "Sobre" e a opção de encerrar o programa.
 * </p>
 *
 * <p>
 * A interface utiliza o tema {@link FlatDarkLaf} para uma aparência moderna,
 * sendo aplicada no método {@code main}.
 * </p>
 */

public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Logger utilizado para registrar erros durante a inicialização da interface.
     */
    private static final Logger LOGGER = Logger.getLogger(TelaPrincipal.class.getName());

    /**
     * Construtor da tela principal.
     *
     * <p>
     * Inicializa e configura todos os componentes visuais da janela.
     * </p>
     */
    public TelaPrincipal() {
        initComponents();
    }

    /**
     * Inicializa todos os componentes gráficos da interface.
     *
     * <p>
     * Este método é gerado automaticamente pelo editor visual do NetBeans.
     * Alterações manuais não são recomendadas.
     * </p>
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bProfessores = new javax.swing.JButton();
        bAlunos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        arquivo = new javax.swing.JMenu();
        menuAlunos = new javax.swing.JMenuItem();
        menuProfessores = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuLeave = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");
        setBackground(new java.awt.Color(51, 255, 51));
        setResizable(false);

        bProfessores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bProfessores.setText("Professores");
        bProfessores.setToolTipText("CTRL+P");
        bProfessores.setAlignmentX(0.5F);
        bProfessores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProfessoresActionPerformed(evt);
            }
        });

        bAlunos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bAlunos.setText("Alunos");
        bAlunos.setToolTipText("CTRL+A");
        bAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlunosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SisUni - Sistema de Gerenciamento Universitário");

        arquivo.setText("Arquivo");

        menuAlunos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuAlunos.setText("Gerenciamento de Alunos");
        menuAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAlunosActionPerformed(evt);
            }
        });
        arquivo.add(menuAlunos);

        menuProfessores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuProfessores.setText("Gerenciamento de Professores");
        menuProfessores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProfessoresActionPerformed(evt);
            }
        });
        arquivo.add(menuProfessores);

        jMenuItem1.setText("Sobre");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        arquivo.add(jMenuItem1);

        menuLeave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuLeave.setText("Sair");
        menuLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLeaveActionPerformed(evt);
            }
        });
        arquivo.add(menuLeave);

        jMenuBar1.add(arquivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(bAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 208,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, 208,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(93, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(bAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(60, Short.MAX_VALUE)));

        bProfessores.getAccessibleContext().setAccessibleDescription("Cadastro de Professores");
        bAlunos.getAccessibleContext().setAccessibleDescription("Cadastro de Alunos");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Abre a tela de gerenciamento de alunos.
     *
     * <p>
     * Fecha a tela atual após abrir a nova.
     * </p>
     *
     * @param evt evento de clique no botão ou menu
     */

    private void bAlunosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bAlunosActionPerformed
        GerenciaAlunos tela = new GerenciaAlunos();
        tela.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_bAlunosActionPerformed

    /**
     * Abre a tela de gerenciamento de professores.
     *
     * <p>
     * Fecha a tela atual após abrir a nova.
     * </p>
     *
     * @param evt evento de clique no botão ou menu
     */
    private void bProfessoresActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bProfessoresActionPerformed
        GerenciaProfessores tela = new GerenciaProfessores();
        tela.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_bProfessoresActionPerformed

    /**
     * Abre a tela de gerenciamento de alunos.
     *
     * <p>
     * Fecha a tela atual após abrir a nova.
     * </p>
     *
     * @param evt evento de clique no botão ou menu
     */
    private void menuAlunosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuAlunosActionPerformed
        GerenciaAlunos tela = new GerenciaAlunos();
        tela.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_menuAlunosActionPerformed

    /**
     * Abre a tela de gerenciamento de professores.
     *
     * <p>
     * Fecha a tela atual após abrir a nova.
     * </p>
     *
     * @param evt evento de clique no botão ou menu
     */
    private void menuProfessoresActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuProfessoresActionPerformed
        GerenciaProfessores tela = new GerenciaProfessores();
        tela.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_menuProfessoresActionPerformed

    /**
     * Encerra o programa.
     *
     * <p>
     * Fecha a tela atual e encerra o programa.
     * </p>
     *
     * @param evt evento de clique no botão ou menu
     */
    private void menuLeaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuLeaveActionPerformed
        System.exit(0);
    }// GEN-LAST:event_menuLeaveActionPerformed

    /**
     * Abre a tela "Sobre".
     *
     * <p>
     * Fecha a tela atual após abrir a nova.
     * </p>
     *
     * @param evt evento de clique no botão ou menu
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
        Sobre tela = new Sobre();
        tela.setVisible(true);
    }// GEN-LAST:event_jMenuItem1ActionPerformed

    /**
    * Método principal que inicia a tela principal.
     *
     * <p>
     * Configura o tema FlatDarkLaf e inicia a interface gráfica.
     * </p>
     *
     * @param args argumentos de linha de comando (não utilizados).
     */
    public static void main(String args[]) {
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro inesperado na Tela Principal", e);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu arquivo;
    private javax.swing.JButton bAlunos;
    private javax.swing.JButton bProfessores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem menuAlunos;
    private javax.swing.JMenuItem menuLeave;
    private javax.swing.JMenuItem menuProfessores;
    // End of variables declaration//GEN-END:variables
}
