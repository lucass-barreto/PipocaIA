package br.com.pipocaIA.service;

import br.com.pipocaIA.dto.FilmeDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class GptRequestService {

    private WebClient webClient;
    private FilmeService filmeService;
    @Value("${openIA.api.key}")
    private String apiGptKey;

    public GptRequestService(FilmeService filmeService, @Qualifier("gptWebClient") WebClient webClient) {
        this.filmeService = filmeService;
        this.webClient = webClient;
    }

    public Mono<String> filmesSugeridos(){
        List<FilmeDTO> filmesRegistrados = filmeService.filmesRegistrados();

        String filmes = filmesRegistrados.stream()
                .map(filme -> String.format("%s (nota: %.1f)", filme.getNome(), filme.getNota()))
                .collect(Collectors.joining(", "));

        String prompt =  "Sua tarefa é retornar 8 títulos, de filmes em sua linguagem nativa (inglês). " +
                "Sua resposta DEVE CONTER APENAS os 8 títulos separados por vírgula. " +
                "NÃO inclua saudações, explicações, números ou subtítulos.\n\n" +
                "REGRAS IMPORTANTES:\n" +
                "1. SEJA CRIATIVO: Informe 4 filmes polulares e 4 filmes menos óbvios ou populares. Procure por joias escondidas ou clássicos menos conhecidos que se encaixem no gosto do usuário em alguns desses filmes para os desconhecidos.\n" +
                "2. GERE NOVIDADE: A cada nova requisição, você DEVE gerar uma lista de filmes completamente diferente da anterior. Não repita sugestões." +
                "3. DIVERSIFIQUE: traga filmes de pelo menos 2 categorias diferentes" +
                "4. ATENÇÃO AS NOTAS: As notas enviadas são de zero a dez." +
                "5. SEM REPETIÇÃO: Não repita filmes que já estão na lista fornecida a você" +
                "6. QUANTIDADE: retorne SÓ 8 títulos. Nem mais, nem menos.\n\n" +
                "Baseado na seguinte lista de filmes e notas, gere as recomendações: " + filmes;

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um sistema de recomendação de filmes."),
                        Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiGptKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var choicesList = (List<Map<String, Object>>) response.get("choices");

                    if (choicesList != null && !choicesList.isEmpty()) {
                        Map<String, Object> firstChoice = choicesList.get(0);
                        Map message = (Map) firstChoice.get("message");

                        return message.get("content").toString();
                    } return "Nenhuma filme foi sugerido";
                });

    }
}
