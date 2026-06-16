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
    String dica = perguntaAtual.getDica().replace("\n", "<br>");

    lblDica.setText(
        "<html><body style='width: 250px;'>" + dica + "</body></html>"
    );
} else {
    lblDica.setText("Sem dica");
}

    lblQuestaoAtual.setText("Questão " + (indicePerguntaAtual + 1) + " de " + perguntas.size());
    lblNivel.setText("Nível: " + perguntaAtual.getNivel());
    lblPontos.setText("Pontos: " + pontuacao);

    

    btnAlternativaA.setText(perguntaAtual.getAlternativaA());
    btnAlternativaB.setText(perguntaAtual.getAlternativaB());
    btnAlternativaC.setText(perguntaAtual.getAlternativaC());
    btnAlternativaD.setText(perguntaAtual.getAlternativaD());

    txtEnunciadoQuestao.setText(perguntaAtual.getEnunciado());
    txtEnunciadoQuestao.setCaretPosition(0);

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
    
    txtEnunciadoQuestao.setEditable(false);
    txtEnunciadoQuestao.setLineWrap(true);
    txtEnunciadoQuestao.setWrapStyleWord(true);
    txtEnunciadoQuestao.setOpaque(false);
    txtEnunciadoQuestao.setBorder(null);
    
    aplicarQuebraNosBotoes();
    fixarTamanhoBotoesAlternativas();
    
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
    private void fixarTamanhoBotoesAlternativas() {
    java.awt.Dimension tamanho = new java.awt.Dimension(260, 55);

    btnAlternativaA.setPreferredSize(tamanho);
    btnAlternativaA.setMinimumSize(tamanho);
    btnAlternativaA.setMaximumSize(tamanho);

    btnAlternativaB.setPreferredSize(tamanho);
    btnAlternativaB.setMinimumSize(tamanho);
    btnAlternativaB.setMaximumSize(tamanho);

    btnAlternativaC.setPreferredSize(tamanho);
    btnAlternativaC.setMinimumSize(tamanho);
    btnAlternativaC.setMaximumSize(tamanho);

    btnAlternativaD.setPreferredSize(tamanho);
    btnAlternativaD.setMinimumSize(tamanho);
    btnAlternativaD.setMaximumSize(tamanho);
}
    
   
    
       private void aplicarQuebraNosBotoes() {
        btnAlternativaA.setUI(new QuebraLinhaButtonUI());
        btnAlternativaB.setUI(new QuebraLinhaButtonUI());
        btnAlternativaC.setUI(new QuebraLinhaButtonUI());
        btnAlternativaD.setUI(new QuebraLinhaButtonUI());
}
    private class QuebraLinhaButtonUI extends javax.swing.plaf.basic.BasicButtonUI {

    @Override
    public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
        javax.swing.AbstractButton botao = (javax.swing.AbstractButton) c;
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();

        java.awt.Color corFundo = botao.getBackground();

        if (botao.getModel().isPressed()) {
            corFundo = corFundo.darker();
        }

        g2.setColor(corFundo);
        g2.fillRect(0, 0, botao.getWidth(), botao.getHeight());

        g2.setColor(botao.getForeground());
        g2.setFont(botao.getFont());

        java.awt.FontMetrics fm = g2.getFontMetrics();
        int larguraMaxima = botao.getWidth() - 20;

        java.util.List<String> linhas = quebrarTexto(botao.getText(), fm, larguraMaxima);

        int alturaLinha = fm.getHeight();
        int alturaTotal = linhas.size() * alturaLinha;
        int y = (botao.getHeight() - alturaTotal) / 2 + fm.getAscent();

        for (String linha : linhas) {
            int x = (botao.getWidth() - fm.stringWidth(linha)) / 2;
            g2.drawString(linha, x, y);
            y += alturaLinha;
        }

        g2.dispose();
    }

    private java.util.List<String> quebrarTexto(String texto, java.awt.FontMetrics fm, int larguraMaxima) {
        java.util.List<String> linhas = new java.util.ArrayList<>();

        if (texto == null || texto.isEmpty()) {
            linhas.add("");
            return linhas;
        }

        String[] palavras = texto.split(" ");
        String linhaAtual = "";

        for (String palavra : palavras) {
            String teste = linhaAtual.isEmpty() ? palavra : linhaAtual + " " + palavra;

            if (fm.stringWidth(teste) <= larguraMaxima) {
                linhaAtual = teste;
            } else {
                if (!linhaAtual.isEmpty()) {
                    linhas.add(linhaAtual);
                }
                linhaAtual = palavra;
            }
        }

        if (!linhaAtual.isEmpty()) {
            linhas.add(linhaAtual);
        }

        return linhas;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEnunciadoQuestao = new javax.swing.JTextArea();
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
        btnAlternativaA.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnAlternativaA.setForeground(new java.awt.Color(255, 255, 255));
        btnAlternativaA.setText("Alternativa A");
        btnAlternativaA.setAutoscrolls(true);
        btnAlternativaA.addActionListener(this::btnAlternativaAActionPerformed);

        btnAlternativaB.setBackground(new java.awt.Color(39, 51, 54));
        btnAlternativaB.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnAlternativaB.setForeground(new java.awt.Color(255, 255, 255));
        btnAlternativaB.setText("Alternativa B");
        btnAlternativaB.addActionListener(this::btnAlternativaBActionPerformed);

        btnAlternativaC.setBackground(new java.awt.Color(39, 51, 54));
        btnAlternativaC.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnAlternativaC.setForeground(new java.awt.Color(255, 255, 255));
        btnAlternativaC.setText("Alternativa C");
        btnAlternativaC.addActionListener(this::btnAlternativaCActionPerformed);

        btnAlternativaD.setBackground(new java.awt.Color(39, 51, 54));
        btnAlternativaD.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnAlternativaD.setForeground(new java.awt.Color(255, 255, 255));
        btnAlternativaD.setText("Alternativa D");
        btnAlternativaD.addActionListener(this::btnAlternativaDActionPerformed);

        txtEnunciadoQuestao.setColumns(20);
        txtEnunciadoQuestao.setRows(5);
        jScrollPane1.setViewportView(txtEnunciadoQuestao);

        lblImagemQuestao.setText("Imagem da questão");

        javax.swing.GroupLayout jpnlEnunciadoLayout = new javax.swing.GroupLayout(jpnlEnunciado);
        jpnlEnunciado.setLayout(jpnlEnunciadoLayout);
        jpnlEnunciadoLayout.setHorizontalGroup(
            jpnlEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlEnunciadoLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImagemQuestao, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jpnlEnunciadoLayout.setVerticalGroup(
            jpnlEnunciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
            .addGroup(jpnlEnunciadoLayout.createSequentialGroup()
                .addComponent(lblImagemQuestao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAlternativaB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAlternativaA, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                                .addGap(55, 55, 55)
                                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAlternativaD, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAlternativaC, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAlternativaC, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                            .addComponent(btnAlternativaA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAlternativaD, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                            .addComponent(btnAlternativaB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(222, Short.MAX_VALUE))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnPrincipal;
    private javax.swing.JPanel jpnlEnunciado;
    private javax.swing.JPanel jpnlTopo;
    private javax.swing.JLabel lblDica;
    private javax.swing.JLabel lblImagemQuestao;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblPontos;
    private javax.swing.JLabel lblQuestaoAtual;
    private javax.swing.JTextArea txtEnunciadoQuestao;
    // End of variables declaration//GEN-END:variables
}
