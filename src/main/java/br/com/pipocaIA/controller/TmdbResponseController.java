package br.com.pipocaIA.controller;

import br.com.pipocaIA.dto.TmdbResponseDTO;
import br.com.pipocaIA.service.GptRequestService;
import br.com.pipocaIA.service.TmdbFilmeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/ia/recomendados")
public class TmdbResponseController {

    private TmdbFilmeService tmdbFilmeService;
    private GptRequestService gptRequestService;

    public TmdbResponseController(TmdbFilmeService tmdbFilmeService, GptRequestService gptRequestService) {
        this.tmdbFilmeService = tmdbFilmeService;
        this.gptRequestService = gptRequestService;
    }

    @GetMapping("/informacoes")
    public Mono<ResponseEntity<List<TmdbResponseDTO>>> filmesDetalhados(){

        return gptRequestService.filmesSugeridos()
                .flatMap(tmdbFilmeService :: filmesDetalhados)
                .map(ResponseEntity :: ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
