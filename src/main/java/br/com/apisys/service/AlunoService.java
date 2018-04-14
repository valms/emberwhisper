package br.com.apisys.service;

import br.com.apisys.exception.ResourceNotFoundException;
import br.com.apisys.model.Aluno;
import br.com.apisys.model.Curso;
import br.com.apisys.repository.AlunoRepository;
import br.com.apisys.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }


    /**
     * Monta uma lista de todos os alunos salvos
     *
     * @return Lista de todos os alunos
     * @see Aluno
     */
    @GetMapping("/alunos")
    public Collection<Aluno> alunos() {
        return new ArrayList<>(this.alunoRepository.findAll());
    }

    /**
     * Devolve os dados de um aluno cujo ID foi enviado na URL
     *
     * @param id do Aluno pesquisado
     * @return dados do Aluno
     */
    @GetMapping("/aluno/{id}")
    public Aluno retrieveAluno(@PathVariable long id) {
        Optional<Aluno> aluno = this.alunoRepository.findById(id);

        if (!aluno.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return aluno.get();
    }

    /**
     * Exclui um aluno da base de dados via id passado
     *
     * @param id ID do aluno a ser apagado
     */
    @DeleteMapping("/aluno/{id}")
    public void deleteAluno(@PathVariable long id) {
        this.alunoRepository.deleteById(id);
    }


    /**
     * Cria Um novo aluno
     *
     * @param aluno Dados para criacao do Aluno
     * @return status da criacao
     */
    @PostMapping("/aluno")
    public ResponseEntity<Object> createAluno(@RequestBody Aluno aluno) {

        Aluno auxAluno = this.alunoRepository.save(aluno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(auxAluno.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    /**
     * Atualiza um Aluno
     *
     * @param aluno Novos dados do aluno
     * @param id    ID do Aluno a ser atualizado
     * @return status 20x (200 ou 204) no sucesso
     */
    @PutMapping("/aluno/{id}")
    public ResponseEntity<Object> updateAluno(@RequestBody Aluno aluno, @PathVariable long id) {

        Optional<Aluno> alunoOptional = this.alunoRepository.findById(id);

        if (!alunoOptional.isPresent())
            return ResponseEntity.notFound().build();

        aluno.setId(id);
        this.alunoRepository.save(aluno);
        return ResponseEntity.noContent().build();
    }


}
