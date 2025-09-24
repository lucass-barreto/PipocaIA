package br.com.pipocaIA.dto.mapper;

import br.com.pipocaIA.dto.FilmeDTO;
import br.com.pipocaIA.model.FilmeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilmeMapper {

    FilmeDTO map(FilmeModel filmeModel);
    FilmeModel map(FilmeDTO filmeDTO);
}
