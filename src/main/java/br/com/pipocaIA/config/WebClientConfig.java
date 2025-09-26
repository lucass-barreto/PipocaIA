package br.com.pipocaIA.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${tmdb.api.url}")
    private String apiTmdbUrl;
    @Value("${tmdb.api.key}")
    private String apiTmdbKey;
    @Value("${openIA.api.url}")
    private String apiGptUrl;
    @Value("${openIA.api.key}")
    private String apiGptKey;

    @Bean("tmdbWebClient")
    public WebClient webClientTmdb(WebClient.Builder builder){
        return builder.baseUrl(apiTmdbUrl).build();
    }

    @Bean("gptWebClient")
    public WebClient webClientGpt (WebClient.Builder builder){
        return builder.baseUrl(apiGptUrl).build();
    }


}
