package br.com.apisys.repository;

import br.com.apisys.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    public List<Curso> findByCodigo(String codigo);


}
