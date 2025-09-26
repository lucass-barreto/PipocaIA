package br.com.pipocaIA.dto;

import br.com.pipocaIA.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDTO {

    private Long id;
    private String nome;
    private Categoria categoria;
    private double nota;
}
