package br.com.apisys.service;


import br.com.apisys.exception.ResourceNotFoundException;
import br.com.apisys.model.Curso;
import br.com.apisys.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }


    /**
     * Monta uma lista de todos os cursos salvos
     *
     * @return Lista de todos os cursos
     * @see Curso
     */
    @GetMapping("/cursos")
    public Collection<Curso> cursos() {
        return new ArrayList<>(this.cursoRepository.findAll());
    }

    /**
     * Devolve os dados de um curso cujo ID foi enviado na URL
     *
     * @param id do curso pesquisado
     * @return dados do curso
     */
    @GetMapping("/curso/{id}")
    public Curso retrieveCurso(@PathVariable long id) {
        Optional<Curso> curso = this.cursoRepository.findById(id);

        if (!curso.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return curso.get();
    }

    /**
     * Exclui um curso da base de dados via id passado
     *
     * @param id ID do curso a ser apagado
     */
    @DeleteMapping("/curso/{id}")
    public void deleteCurso(@PathVariable long id) {
        this.cursoRepository.deleteById(id);
    }


    /**
     * Cria Um novo curso
     *
     * @param curso Dados para criacao do Curso
     * @return status da criacao
     */
    @PostMapping("/curso")
    public ResponseEntity<Object> createCurso(@RequestBody Curso curso) {
        Curso auxCurso = this.cursoRepository.save(curso);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(auxCurso.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    /**
     * Atualiza um Curso
     *
     * @param curso Novos dados do Curso
     * @param id    ID do Curso a ser atualizado
     * @return status 20x (200 ou 204) no sucesso
     */
    @PutMapping("/curso/{id}")
    public ResponseEntity<Object> updateCurso(@RequestBody Curso curso, @PathVariable long id) {

        Optional<Curso> cursoOptional = this.cursoRepository.findById(id);

        if (!cursoOptional.isPresent())
            return ResponseEntity.notFound().build();

        curso.setId(id);
        this.cursoRepository.save(curso);
        return ResponseEntity.noContent().build();
    }

}
