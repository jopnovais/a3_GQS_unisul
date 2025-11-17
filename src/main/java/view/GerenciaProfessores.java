package view;

import model.Professor;
import repository.ProfessorRepository;
import repository.ProfessorRepositoryImpl;
import service.ProfessorService;
import service.ProfessorServiceImpl;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Tela de gerenciamento de professores.
 * <p>
 * Esta interface gráfica lista todos os professores cadastrados no sistema
 * e permite realizar operações como cadastrar, editar e excluir registros.
 * Também disponibiliza atalhos no menu para atualizar a tabela,
 * acessar a gerência de alunos e visualizar informações do sistema.
 * </p>
 *
 * <p>
 * Todas as operações de consulta, alteração e exclusão são realizadas por
 * meio de um {@link ProfessorService}, que encapsula as regras de negócio.
 * </p>
 */
public class GerenciaProfessores extends javax.swing.JFrame {

    private final ProfessorService professorService;

    /**
     * Constrói a tela de gerenciamento de professores.
     * <p>
     * Inicializa repositório, serviço, componentes gráficos e
     * carrega automaticamente a tabela com todos os professores
     * cadastrados no sistema.
     * </p>
     */
    public GerenciaProfessores() {
        ProfessorRepository professorRepository = new ProfessorRepositoryImpl();
        this.professorService = new ProfessorServiceImpl(professorRepository);
        
        initComponents();
        this.carregaTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents

    /**
     * Inicializa os componentes gráficos da interface.
     *
     * <p>
     * Este método é gerado automaticamente pelo editor visual do NetBeans.
     * Alterações manuais não são recomendadas.
     * </p>
     */
    private void initComponents() {

        bCadastro = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();
        bDeletar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProfessores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        menuGerenciaAluno = new javax.swing.JMenuItem();
        menuRefresh = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuLeave = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerência de Professores");
        setBackground(new java.awt.Color(80, 80, 80));
        setResizable(false);

        bCadastro.setText("Cadastrar novo");
        bCadastro.setToolTipText("");
        bCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCadastroActionPerformed(evt);
            }
        });

        bEditar.setText("Editar");
        bEditar.setToolTipText("");
        bEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarActionPerformed(evt);
            }
        });

        bDeletar.setText("Deletar");
        bDeletar.setToolTipText("");
        bDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeletarActionPerformed(evt);
            }
        });

        jTableProfessores.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null}
                },
                new String[]{
                    "ID", "Nome", "Idade", "Campus", "CPF", "Contato", "Título", "Salário"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTableProfessores.setSelectionForeground(new java.awt.Color(239, 239, 239));
        jTableProfessores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProfessoresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableProfessores);
        if (jTableProfessores.getColumnModel().getColumnCount() > 0) {
            jTableProfessores.getColumnModel().getColumn(0).setMinWidth(40);
            jTableProfessores.getColumnModel().getColumn(0).setMaxWidth(40);
            jTableProfessores.getColumnModel().getColumn(2).setMinWidth(40);
            jTableProfessores.getColumnModel().getColumn(2).setMaxWidth(40);
            jTableProfessores.getColumnModel().getColumn(7).setMinWidth(75);
            jTableProfessores.getColumnModel().getColumn(7).setMaxWidth(75);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Professores");

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/refresh.png"))); // NOI18N
        refresh.setText("  Atualizar tabela");
        refresh.setToolTipText("CTRL+R");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        menu.setForeground(new java.awt.Color(239, 239, 239));
        menu.setText("Arquivo");

        menuGerenciaAluno.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuGerenciaAluno.setText("Gerência de Alunos");
        menuGerenciaAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGerenciaAlunoActionPerformed(evt);
            }
        });
        menu.add(menuGerenciaAluno);

        menuRefresh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuRefresh.setText("Atualizar tabela");
        menuRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRefreshActionPerformed(evt);
            }
        });
        menu.add(menuRefresh);

        jMenuItem1.setText("Sobre");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu.add(jMenuItem1);

        menuLeave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuLeave.setText("Sair");
        menuLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLeaveActionPerformed(evt);
            }
        });
        menu.add(menuLeave);

        jMenuBar1.add(menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(65, 65, 65)
                                                .addComponent(bCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bCadastro)
                                        .addComponent(bEditar)
                                        .addComponent(bDeletar)
                                        .addComponent(refresh))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Abre a tela de gerenciamento de alunos e fecha a tela atual.
     *
     * @param evt evento de clique do item de menu.
     */
    private void menuGerenciaAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGerenciaAlunoActionPerformed
        GerenciaAlunos tela = new GerenciaAlunos();
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuGerenciaAlunoActionPerformed

    /**
     * Encerra completamente o sistema.
     *
     * @param evt evento de clique do item de menu Sair.
     */
    private void menuLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLeaveActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuLeaveActionPerformed

    /**
     * Abre a tela de cadastro de um novo professor.
     *
     * @param evt evento de clique do botão "Cadastrar novo".
     */
    private void bCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCadastroActionPerformed
        try {
            CadastroProfessor tela = new CadastroProfessor();
            tela.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(GerenciaProfessores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_bCadastroActionPerformed

    /**
     * Abre a tela de edição de professor para o registro selecionado.
     * <p>
     * Caso nenhuma linha esteja selecionada na tabela, uma mensagem de
     * aviso é exibida ao usuário.
     * </p>
     *
     * @param evt evento de clique do botão "Editar".
     */
    private void bEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarActionPerformed
        try {
            if (this.jTableProfessores.getSelectedRow() != -1) {
                EditarProfessor tela = new EditarProfessor();
                tela.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um cadastro para alterar");
            }
        } catch (ParseException ex) {
            Logger.getLogger(GerenciaProfessores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_bEditarActionPerformed

    public static String listaDados[] = new String[8];

    /**
     * Captura os dados do professor selecionado na tabela e os armazena
     * no vetor estático {@code listaDados}, permitindo que a tela
     * de edição carregue essas informações.
     *
     * @param evt evento de clique na tabela.
     */
    private void jTableProfessoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProfessoresMouseClicked
        if (this.jTableProfessores.getSelectedRow() != -1) {

            String nome = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 1).toString();
            String idade = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 2).toString();
            String campus = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 3).toString();
            String cpf = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 4).toString();
            String contato = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 5).toString();
            String titulo = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 6).toString();
            String salario = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 7).toString();
            String id = this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 0).toString();

            String salarioFormatado = professorService.validarFormatado(salario);

            listaDados[0] = nome;
            listaDados[1] = idade;
            listaDados[2] = campus;
            listaDados[3] = cpf;
            listaDados[4] = contato;
            listaDados[5] = titulo;
            listaDados[6] = salarioFormatado;
            listaDados[7] = id;
        }
    }//GEN-LAST:event_jTableProfessoresMouseClicked

    /**
     * Exclui o professor selecionado da lista.
     * <p>
     * Antes da exclusão, o usuário deve confirmar a operação. Caso não
     * haja linha selecionada ou ocorra algum erro inesperado, mensagens
     * apropriadas são exibidas.
     * </p>
     *
     * @param evt evento de clique do botão "Deletar".
     */
    private void bDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeletarActionPerformed
        try {
            if (this.jTableProfessores.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(rootPane, "Selecione um cadastro para deletar", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(this.jTableProfessores.getValueAt(this.jTableProfessores.getSelectedRow(), 0).toString());

            String[] options = {"Sim", "Não"};
            int respostaUsuario = JOptionPane.showOptionDialog(rootPane, "Tem certeza que deseja apagar este cadastro?", "Confirmar exclusão",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

            if (respostaUsuario == 0) {
                professorService.excluir(id);
                JOptionPane.showMessageDialog(rootPane, "Cadastro apagado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregaTabela();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao obter ID do professor selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro inesperado. Contate o suporte.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bDeletarActionPerformed

    /**
     * Atualiza a tabela de professores exibida no formulário.
     *
     * @param evt evento de clique do botão Atualizar.
     */
    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        this.carregaTabela();
    }//GEN-LAST:event_refreshActionPerformed

    /**
     * Atualiza os dados da tabela através do menu.
     *
     * @param evt evento de clique no item de menu Atualizar tabela.
     */
    private void menuRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRefreshActionPerformed
        this.carregaTabela();
    }//GEN-LAST:event_menuRefreshActionPerformed

    /**
     * Abre a tela "Sobre" com informações do sistema.
     *
     * @param evt evento de clique no item de menu Sobre.
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Sobre tela = new Sobre();
        tela.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    @SuppressWarnings("unchecked")
    /**
     * Carrega todos os professores cadastrados e preenche a tabela da interface.
     * <p>
     * Caso ocorra algum erro inesperado durante a busca, uma mensagem de
     * erro é exibida ao usuário.
     * </p>
     */
    public void carregaTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.jTableProfessores.getModel();
        modelo.setNumRows(0);

        try {
            List<Professor> professores = professorService.listarTodos();

            for (Professor a : professores) {
                modelo.addRow(new Object[]{
                    a.getId(),
                    a.getNome(),
                    a.getIdade(),
                    a.getCampus(),
                    a.getCpf(),
                    a.getContato(),
                    a.getTitulo(),
                    "R$" + a.getSalario() + ".00"
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao carregar lista de professores.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método principal que inicia a tela de gerenciamento de professores.
     *
     * @param args argumentos de linha de comando (não utilizados).
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciaProfessores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciaProfessores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciaProfessores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciaProfessores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciaProfessores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCadastro;
    private javax.swing.JButton bDeletar;
    private javax.swing.JButton bEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProfessores;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem menuGerenciaAluno;
    private javax.swing.JMenuItem menuLeave;
    private javax.swing.JMenuItem menuRefresh;
    private javax.swing.JButton refresh;
    // End of variables declaration//GEN-END:variables
}
