package br.com.pipocaIA.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbResponseDTO {

    @JsonProperty("title")
    private String titulo;

    @JsonProperty("overview")
    private String sinopse;

    @JsonProperty("poster_path")
    private String caminhoPoster;

    @JsonProperty("release_date")
    private String dataLancamento;

    @JsonProperty("vote_average")
    private double notaMedia;

    public String getUrlPoster() {

        if (this.caminhoPoster == null || this.caminhoPoster.isBlank()) {
            return null;
        }

        return "https://image.tmdb.org/t/p/w500" + this.caminhoPoster;
    }

}
