package br.com.apisys.model;

public enum AlunoStatus {
    MATRICULADO("MATRICULADO"),
    TRANCADO("TRANCADO"),
    JUBILADO("JUBILADO");

    private String status;

    AlunoStatus(String status) {
        this.status = status;
    }
}
