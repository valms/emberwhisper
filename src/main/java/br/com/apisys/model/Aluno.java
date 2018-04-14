package br.com.apisys.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "curso_id", unique = true)
//    @MapsId
    private Curso curso;

    @NotNull
//    @Enumerated(EnumType.STRING)

    private AlunoStatus alunoStatus;


}
