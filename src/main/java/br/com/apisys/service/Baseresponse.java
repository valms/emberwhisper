package br.com.apisys.service;

public class Baseresponse {

    private int codigo;
    private String mensagem;

    public Baseresponse() {
    }

    public Baseresponse(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
