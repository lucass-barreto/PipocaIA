package br.com.pipocaIA.service;

import br.com.pipocaIA.dto.TmdbResponseDTO;
import br.com.pipocaIA.dto.mapper.TmdbSearchResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class TmdbFilmeService {

    private WebClient webClient;
    @Value("${tmdb.api.key}")
    private String apiTmdbKey;

    public TmdbFilmeService(@Qualifier("tmdbWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<TmdbResponseDTO>> filmesDetalhados (String filmesSugeridos){
        String[] arrayFilmesSugeridos = filmesSugeridos.split(",");

        return Flux.fromArray(arrayFilmesSugeridos)
                .map(String::trim)
                .concatMap(this::buscarFilmePorNome)
                .collectList();
    }

    public Mono<TmdbResponseDTO> buscarFilmePorNome(String nomeFilme){
        return this.webClient.get()
                .uri(uriBuilder ->  uriBuilder
                        .queryParam("api_key", apiTmdbKey)
                        .queryParam("query", nomeFilme)
                        .queryParam("language", "pt-BR")
                        .build())
                .retrieve()
                .bodyToMono(TmdbSearchResponseDTO.class)
                .flatMap(response -> {
                    if (response.getResults() != null && !response.getResults().isEmpty()) {
                        return Mono.just(response.getResults().get(0));
                    } else {
                        return Mono.empty();
                    }
                });
    }

}

