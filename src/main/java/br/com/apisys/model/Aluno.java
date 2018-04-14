package br.com.apisys.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private int matricula;

    private int semestre;

    //    @JsonBackReference
    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "curso_id", unique = true)
    private Curso curso;

    /*
     * Versão 1 para tratar ENUM a nível de Aplicação
     */
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private AlunoStatus alunoStatus;

    /*
     * Versão 2 para tratar ENUM a nível de Banco
     */
    @Column(columnDefinition = "enum('MATRICULADO','TRANCADO','JUBILADO')")
    private String alunoStatus;

}
