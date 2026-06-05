/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Leonardo
 */
public class TelaAlunoQuestao extends javax.swing.JFrame {
    
    
    private java.util.ArrayList<Pergunta> perguntas = new java.util.ArrayList<>();
    private int indicePerguntaAtual = 0;
    private int acertos = 0;
    private int pontuacao = 0;
    private String respostaSelecionada = "";
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAlunoQuestao.class.getName());
    
    private void salvarResultadoFinal() {
    try {
        int totalQuestoes = perguntas.size();
        int percentual = (acertos * 100) / totalQuestoes;

        DAO dao = new DAO();
        dao.salvarResultado(Sessao.usuarioLogado.getId(), pontuacao, acertos, totalQuestoes, percentual);

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao salvar resultado.");
        e.printStackTrace();
    }
}
    
    private void carregarImagem(String caminhoImagem) {
    try {
        if (caminhoImagem == null || caminhoImagem.trim().isEmpty()) {
            lblImagemQuestao.setIcon(null);
            lblImagemQuestao.setText("Sem imagem");
            return;
        }

        caminhoImagem = caminhoImagem.trim();

        java.awt.image.BufferedImage imagemOriginal;

        if (caminhoImagem.startsWith("http://") || caminhoImagem.startsWith("https://")) {
            java.net.URL url = new java.net.URL(caminhoImagem);
            imagemOriginal = javax.imageio.ImageIO.read(url);
        } else {
            java.io.File arquivo = new java.io.File(caminhoImagem);

            if (!arquivo.exists()) {
                lblImagemQuestao.setIcon(null);
                lblImagemQuestao.setText("Imagem não encontrada");
                return;
            }

            imagemOriginal = javax.imageio.ImageIO.read(arquivo);
        }

        if (imagemOriginal == null) {
            lblImagemQuestao.setIcon(null);
            lblImagemQuestao.setText("Imagem inválida");
            return;
        }

        int largura = lblImagemQuestao.getWidth();
        int altura = lblImagemQuestao.getHeight();

        if (largura <= 0) {
            largura = 250;
        }

        if (altura <= 0) {
            altura = 160;
        }

        java.awt.Image imagemRedimensionada = imagemOriginal.getScaledInstance(
                largura,
                altura,
                java.awt.Image.SCALE_SMOOTH
        );

        lblImagemQuestao.setIcon(new javax.swing.ImageIcon(imagemRedimensionada));
        lblImagemQuestao.setText("");

    } catch (Exception e) {
        lblImagemQuestao.setIcon(null);
        lblImagemQuestao.setText("Erro ao carregar imagem");
        e.printStackTrace();
    }
}
    
    private void mostrarPergunta() {
    Pergunta perguntaAtual = perguntas.get(indicePerguntaAtual);
    
    if (perguntaAtual.getDica() != null && !perguntaAtual.getDica().isEmpty()) {
    lblDica.setText("<html>" + perguntaAtual.getDica() + "</html>");
} else {
    lblDica.setText("Sem dica");
}

    lblQuestaoAtual.setText("Questão " + (indicePerguntaAtual + 1) + " de " + perguntas.size());
    lblNivel.setText("Nível: " + perguntaAtual.getNivel());
    lblPontos.setText("Pontos: " + pontuacao);

    lblEnunciado.setText("<html>" + perguntaAtual.getEnunciado() + "</html>");

    btnAlternativaA.setText(perguntaAtual.getAlternativaA());
    btnAlternativaB.setText(perguntaAtual.getAlternativaB());
    btnAlternativaC.setText(perguntaAtual.getAlternativaC());
    btnAlternativaD.setText(perguntaAtual.getAlternativaD());

    

    respostaSelecionada = "";
    btnAlternativaA.setBackground(new java.awt.Color(39, 51, 54));
    btnAlternativaB.setBackground(new java.awt.Color(39, 51, 54));
    btnAlternativaC.setBackground(new java.awt.Color(39, 51, 54));
    btnAlternativaD.setBackground(new java.awt.Color(39, 51, 54));
    
    System.out.println("Imagem da pergunta: " + perguntaAtual.getImagem());
    
    carregarImagem(perguntaAtual.getImagem());
    
        
}
    
    

    /**
     * Creates new form TelaAlunoQuestao
     */
    public TelaAlunoQuestao() {
    initComponents();

    setContentPane(jpnPrincipal);
    pack();
    setLocationRelativeTo(null);
    setResizable(false);

    try {
        DAO dao = new DAO();
        perguntas = dao.listarPerguntas();
        System.out.println("Total de perguntas carregadas: " + perguntas.size());

        if (perguntas.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Nenhuma pergunta cadastrada.");
            TelaAluno tela = new TelaAluno();
            tela.setVisible(true);
            this.dispose();
            return;
        }

        mostrarPergunta();

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar perguntas.");
        e.printStackTrace();
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnProximaQuestao = new javax.swing.JButton();
        jpnPrincipal = new javax.swing.JPanel();
        jpnlTopo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblQuestaoAtual = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        lblPontos = new javax.swing.JLabel();
        btnAlternativaA = new javax.swing.JButton();
        btnAlternativaB = new javax.swing.JButton();
        btnAlternativaC = new javax.swing.JButton();
        btnAlternativaD = new javax.swing.JButton();
        jpnlEnunciado = new javax.swing.JPanel();
        lblEnunciado = new javax.swing.JLabel();
        lblImagemQuestao = new javax.swing.JLabel();
        btnProximaQuestao1 = new javax.swing.JButton();
        jDicaTela = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblDica = new javax.swing.JLabel();

        btnProximaQuestao.setBackground(new java.awt.Color(39, 51, 54));
        btnProximaQuestao.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        btnProximaQuestao.setForeground(new java.awt.Color(255, 255, 255));
        btnProximaQuestao.setText("Próxima questão");
        btnProximaQuestao.addActionListener(this::btnProximaQuestaoActionPerformed);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jpnlTopo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGO CPS.png"))); // NOI18N
        jLabel6.setText("jLabel6");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo etec.png"))); // NOI18N
        jLabel4.setText("jLabel4");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LOGO LAB QUIZ.png"))); // NOI18N
        jLabel7.setText("jLabel7");

        lblQuestaoAtual.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblQuestaoAtual.setText("Questão X de Y");

        lblNivel.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblNivel.setText("Nível: Fácil");

        lblPontos.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblPontos.setText("Pontos: 0");

        javax.swing.GroupLayout jpnlTopoLayout = new javax.swing.GroupLayout(jpnlTopo);
        jpnlTopo.setLayout(jpnlTopoLayout);
        jpnlTopoLayout.setHorizontalGroup(
            jpnlTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlTopoLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(lblQuestaoAtual)
                .addGap(18, 18, 18)
                .addComponent(lblNivel)
                .addGap(18, 18, 18)
                .addComponent(lblPontos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnlTopoLayout.setVerticalGroup(
            jpnlTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlTopoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(lblQuestaoAtual)
                    .addComponent(lblNivel)
                    .addComponent(lblPontos))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnAlternativaA.setBackground(new java.awt.Color(39, 51, 54));
        btnAlternativaA.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnAlternativaA.setForeground(new java.awt.Color(255, 255, 255));
        btnAlternativaA.setText("Alternativa A");
        btnAlternativaA.setAutoscrolls(true);
        btnAlternativaA.addActionListener(this::btnAlternativaAActionPerformed);

        btnAlternativaB.setBackground(new java.awt.Color(39, 51, 54));
        btnAlternativaB.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnAlternativaB.setForeground(new java.awt.Color(255, 255, 255));
        btnAlternativaB.setText("Alternativa B");
        btnAlternativaB.addActionListener(this::btnAlternativaBActionPerformed);

        btnAlternativaC.setBackground(new java.awt.Color(39, 51, 54));
        btnAlternativaC.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnAlternativaC.setForeground(new java.awt.Color(255, 255, 255));
        btnAlternativaC.setText("Alternativa C");
        btnAlternativaC.addActionListener(this::btnAlternativaCActionPerformed);

        btnAlternativaD.setBackground(new java.awt.Color(39, 51, 54));
        btnAlternativaD.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnAlternativaD.setForeground(new java.awt.Color(255, 255, 255));
        btnAlternativaD.setText("Alternativa D");
        btnAlternativaD.addActionListener(this::btnAlternativaDActionPerformed);

        lblEnunciado.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblEnunciado.setText("ENUNCIADO:");

        lblImagemQuestao.setText("Imagem da questão");

        javax.swing.GroupLayout jpnlEnunciadoLayout = new javax.swing.GroupLayout(jpnlEnunciado);
        jpnlEnunciado.setLayout(jpnlEnunciadoLayout);
        jpnlEnunciadoLayout.setHorizontalGroup(
            jpnlEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlEnunciadoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblEnunciado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagemQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jpnlEnunciadoLayout.setVerticalGroup(
            jpnlEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlEnunciadoLayout.createSequentialGroup()
                .addGroup(jpnlEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnlEnunciadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnlEnunciadoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblImagemQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnProximaQuestao1.setBackground(new java.awt.Color(153, 0, 0));
        btnProximaQuestao1.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        btnProximaQuestao1.setForeground(new java.awt.Color(255, 255, 255));
        btnProximaQuestao1.setText("Próxima questão");
        btnProximaQuestao1.setToolTipText("");
        btnProximaQuestao1.addActionListener(this::btnProximaQuestao1ActionPerformed);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Ajuda disponível:");

        lblDica.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblDica.setForeground(new java.awt.Color(153, 0, 0));
        lblDica.setText("DICAS");

        javax.swing.GroupLayout jDicaTelaLayout = new javax.swing.GroupLayout(jDicaTela);
        jDicaTela.setLayout(jDicaTelaLayout);
        jDicaTelaLayout.setHorizontalGroup(
            jDicaTelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDicaTelaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDicaTelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lblDica))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        jDicaTelaLayout.setVerticalGroup(
            jDicaTelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDicaTelaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(lblDica)
                .addContainerGap(253, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnPrincipalLayout = new javax.swing.GroupLayout(jpnPrincipal);
        jpnPrincipal.setLayout(jpnPrincipalLayout);
        jpnPrincipalLayout.setHorizontalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlTopo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(btnProximaQuestao1))
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpnlEnunciado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnAlternativaB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAlternativaA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)
                                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAlternativaC, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAlternativaD, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jDicaTela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jpnPrincipalLayout.setVerticalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addComponent(jpnlTopo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addComponent(jpnlEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAlternativaC, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlternativaA, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAlternativaB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlternativaD, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnProximaQuestao1))
                    .addComponent(jDicaTela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jpnPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jpnPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlternativaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlternativaAActionPerformed
respostaSelecionada = "A";

    // TODO add your handling code here:
    }//GEN-LAST:event_btnAlternativaAActionPerformed

    private void btnProximaQuestaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximaQuestaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProximaQuestaoActionPerformed

    private void btnProximaQuestao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximaQuestao1ActionPerformed

 if (respostaSelecionada.isEmpty()) {
    javax.swing.JOptionPane.showMessageDialog(this, "Selecione uma alternativa antes de continuar.");
    return;
}

Pergunta perguntaAtual = perguntas.get(indicePerguntaAtual);

if (respostaSelecionada.equals(perguntaAtual.getRespostaCorreta())) {
    acertos++;
    pontuacao += 20;

    TelaAcerto tela = new TelaAcerto(this);
    tela.setVisible(true);
    this.setVisible(false);

} else {
    TelaErro tela = new TelaErro(this);
    tela.setVisible(true);
    this.setVisible(false);
}

      // TODO add your handling code here:
    }//GEN-LAST:event_btnProximaQuestao1ActionPerformed
public void continuarDepoisDoFeedback() {
    indicePerguntaAtual++;

    if (indicePerguntaAtual < perguntas.size()) {
        mostrarPergunta();
        this.setVisible(true);
    } else {
        salvarResultadoFinal();

        RankingDosAlunos tela = new RankingDosAlunos("aluno");
        tela.setVisible(true);
        this.dispose();
    }
}
    
    
    private void btnAlternativaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlternativaCActionPerformed
respostaSelecionada = "C";

     // TODO add your handling code here:
    }//GEN-LAST:event_btnAlternativaCActionPerformed

    private void btnAlternativaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlternativaBActionPerformed
respostaSelecionada = "B";

       // TODO add your handling code here:
    }//GEN-LAST:event_btnAlternativaBActionPerformed

    private void btnAlternativaDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlternativaDActionPerformed
respostaSelecionada = "D";

       // TODO add your handling code here:
    }//GEN-LAST:event_btnAlternativaDActionPerformed

    /**
     * @param args the command line arguments
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaAlunoQuestao().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlternativaA;
    private javax.swing.JButton btnAlternativaB;
    private javax.swing.JButton btnAlternativaC;
    private javax.swing.JButton btnAlternativaD;
    private javax.swing.JButton btnProximaQuestao;
    private javax.swing.JButton btnProximaQuestao1;
    private javax.swing.JPanel jDicaTela;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jpnPrincipal;
    private javax.swing.JPanel jpnlEnunciado;
    private javax.swing.JPanel jpnlTopo;
    private javax.swing.JLabel lblDica;
    private javax.swing.JLabel lblEnunciado;
    private javax.swing.JLabel lblImagemQuestao;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblPontos;
    private javax.swing.JLabel lblQuestaoAtual;
    // End of variables declaration//GEN-END:variables
}
