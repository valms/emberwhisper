package br.com.apisys.model;

public enum AlunoStatus {
    MATRICULADO("Matriculado"),
    TRANCADO("TRANCADO"),
    JUBILADO("JUBILADO");

    private String status;

    AlunoStatus(String status) {
        this.status = status;
    }
}
