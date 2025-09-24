package br.com.pipocaIA.repository;

import br.com.pipocaIA.enums.Categoria;
import br.com.pipocaIA.model.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeModel, Long> {

    List<FilmeModel> findAllByCategoria(Categoria categoria);
}
