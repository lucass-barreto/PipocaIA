package br.com.pipocaIA.controller;

import br.com.pipocaIA.dto.FilmeDTO;
import br.com.pipocaIA.enums.Categoria;
import br.com.pipocaIA.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }


    @GetMapping("/")
    public ResponseEntity<?> filmesRegistrados(){
        List<FilmeDTO> filmesRegistradosDTO = filmeService.filmesRegistrados();

        if(!filmesRegistradosDTO.isEmpty()){
            return ResponseEntity.ok(filmesRegistradosDTO);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> listarFilmesCategoria(@PathVariable Categoria categoria){
        List<FilmeDTO> filmesCategoriaDTO = filmeService.listarFilmesCategoria(categoria);

        if(!filmesCategoriaDTO.isEmpty()){
            return ResponseEntity.ok(filmesCategoriaDTO);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarFilmeId(@PathVariable Long id){
        FilmeDTO filmeEncontradoDTO = filmeService.listarFilmeId(id);

        if(filmeEncontradoDTO != null){
            return ResponseEntity.ok(filmeEncontradoDTO);
        } return ResponseEntity.notFound().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity<FilmeDTO> registrarFilme(@RequestBody FilmeDTO filmeDTO){
        FilmeDTO filmeRegistradoDTO = filmeService.registrarFilme(filmeDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmeRegistradoDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarFilmeId(@PathVariable Long id){
        boolean filmeDeletado = filmeService.deletarFilme(id);

        if(filmeDeletado){
            return ResponseEntity.noContent().build();
        } return ResponseEntity.notFound().build();
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarFilmeId(@PathVariable Long id, @RequestBody FilmeDTO filmeDTO){
        FilmeDTO filmeAlteradoDTO = filmeService.alterarFilme(id, filmeDTO);

        if(filmeAlteradoDTO != null){
            return ResponseEntity.ok(filmeAlteradoDTO);
        } return ResponseEntity.notFound().build();
    }

}
