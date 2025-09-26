package br.com.pipocaIA.controller;

import br.com.pipocaIA.service.GptRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ia")
public class GptResponseController {

    private GptRequestService gptRequestService;

    public GptResponseController(GptRequestService gptRequestService) {
        this.gptRequestService = gptRequestService;
    }

    @GetMapping("/recomendados")
    public Mono<ResponseEntity<String>> filmesSugeridos(){

        return gptRequestService.filmesSugeridos()
                .map(filme -> ResponseEntity.ok(filme))
                .defaultIfEmpty(ResponseEntity.noContent().build());

    }
}
