package br.com.pipocaIA.dto.mapper;

import br.com.pipocaIA.dto.TmdbResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbSearchResponseDTO {

    private List<TmdbResponseDTO> results;
}
