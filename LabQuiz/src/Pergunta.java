/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Leonardo
 */
public class Pergunta {

    private int id;
    private String enunciado;
    private String imagem;
    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;
    private String respostaCorreta;
    private String dica;
    private String nivel;

    public Pergunta() {
    }

    public int getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getImagem() {
        return imagem;
    }

    public String getAlternativaA() {
        return alternativaA;
    }

    public String getAlternativaB() {
        return alternativaB;
    }

    public String getAlternativaC() {
        return alternativaC;
    }

    public String getAlternativaD() {
        return alternativaD;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public String getDica() {
        return dica;
    }

    public String getNivel() {
        return nivel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void setAlternativaA(String alternativaA) {
        this.alternativaA = alternativaA;
    }

    public void setAlternativaB(String alternativaB) {
        this.alternativaB = alternativaB;
    }

    public void setAlternativaC(String alternativaC) {
        this.alternativaC = alternativaC;
    }

    public void setAlternativaD(String alternativaD) {
        this.alternativaD = alternativaD;
    }

    public void setRespostaCorreta(String respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
