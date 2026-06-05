/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Leonardo
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
    
    public void excluirPergunta(int id) throws Exception {
    String sql = "DELETE FROM tb_pergunta WHERE id = ?";

    try (java.sql.Connection conn = ConexaoBD.obterConexao();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.execute();
    }
}
    
    public java.util.ArrayList<Object[]> listarRanking() throws Exception {
    java.util.ArrayList<Object[]> ranking = new java.util.ArrayList<>();

    String sql = "SELECT u.nome, r.acertos, r.percentual, r.pontuacao "
            + "FROM tb_resultado r "
            + "INNER JOIN tb_usuario u ON r.id_usuario = u.id "
            + "ORDER BY r.pontuacao DESC, r.acertos DESC";

    try (java.sql.Connection conn = ConexaoBD.obterConexao();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Object[] linha = new Object[]{
                rs.getString("nome"),
                rs.getInt("acertos"),
                rs.getInt("percentual") + "%",
                rs.getInt("pontuacao")
            };

            ranking.add(linha);
        }
    }

    return ranking;
}
    
    
   public java.util.ArrayList<Pergunta> listarPerguntas() throws Exception {
    java.util.ArrayList<Pergunta> perguntas = new java.util.ArrayList<>();

    String sql = "SELECT * FROM tb_pergunta ORDER BY id ASC";

    try (java.sql.Connection conn = ConexaoBD.obterConexao();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Pergunta pergunta = new Pergunta();

            pergunta.setId(rs.getInt("id"));
            pergunta.setEnunciado(rs.getString("enunciado"));
            pergunta.setImagem(rs.getString("imagem"));
            pergunta.setAlternativaA(rs.getString("alternativa_a"));
            pergunta.setAlternativaB(rs.getString("alternativa_b"));
            pergunta.setAlternativaC(rs.getString("alternativa_c"));
            pergunta.setAlternativaD(rs.getString("alternativa_d"));
            pergunta.setRespostaCorreta(rs.getString("resposta_correta"));
            pergunta.setDica(rs.getString("dica"));
            pergunta.setNivel(rs.getString("nivel"));

            perguntas.add(pergunta);
        }
    }

    return perguntas;
}
    
    public void salvarResultado(int idUsuario, int pontuacao, int acertos, int totalQuestoes, int percentual) throws Exception {
    String sql = "INSERT INTO tb_resultado (id_usuario, pontuacao, acertos, total_questoes, percentual) "
            + "VALUES (?, ?, ?, ?, ?)";

    try (java.sql.Connection conn = ConexaoBD.obterConexao();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idUsuario);
        ps.setInt(2, pontuacao);
        ps.setInt(3, acertos);
        ps.setInt(4, totalQuestoes);
        ps.setInt(5, percentual);

        ps.execute();
    }
}

    public Usuario buscarUsuario(String usuarioDigitado, String senhaDigitada, String tipoSelecionado) throws Exception {
        String sql = "SELECT * FROM tb_usuario WHERE usuario = ? AND senha = ? AND tipo = ?";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuarioDigitado);
            ps.setString(2, senhaDigitada);
            ps.setString(3, tipoSelecionado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();

                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipo(rs.getString("tipo"));

                    return usuario;
                }
            }
        }

        return null;
    }

    public void cadastrarUsuario(Usuario usuario) throws Exception {
        String sql = "INSERT INTO tb_usuario (nome, usuario, senha, tipo) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTipo());

            ps.execute();
        }
    }

    public boolean usuarioExiste(String usuarioDigitado) throws Exception {
        String sql = "SELECT id FROM tb_usuario WHERE usuario = ?";

        try (Connection conn = ConexaoBD.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuarioDigitado);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    public void cadastrarPergunta(Pergunta pergunta) throws Exception {
    String sql = "INSERT INTO tb_pergunta "
            + "(enunciado, imagem, alternativa_a, alternativa_b, alternativa_c, alternativa_d, resposta_correta, dica, nivel) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (java.sql.Connection conn = ConexaoBD.obterConexao();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, pergunta.getEnunciado());
        ps.setString(2, pergunta.getImagem());
        ps.setString(3, pergunta.getAlternativaA());
        ps.setString(4, pergunta.getAlternativaB());
        ps.setString(5, pergunta.getAlternativaC());
        ps.setString(6, pergunta.getAlternativaD());
        ps.setString(7, pergunta.getRespostaCorreta());
        ps.setString(8, pergunta.getDica());
        ps.setString(9, pergunta.getNivel());

        ps.execute();
    }
    
}
}