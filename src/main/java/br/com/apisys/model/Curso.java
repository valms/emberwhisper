package br.com.apisys.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String nome;

    private String instituicaoEnsino;

//    @JsonManagedReference
    @JsonBackReference
//    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "curso", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Aluno alunos;

}
