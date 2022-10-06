package br.com.client.api.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class WebClientService {

    protected static final Log logger = LogFactory.getLog(WebClientService.class);
    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    @Value("${sicredi.api.base.url}")
    private String sicrediBaseUrl;

    public Boolean memberCanVote(String cpf) {
        try {
            Mono<String> response = WebClient
                    .builder()
                    .baseUrl(sicrediBaseUrl)
                    .build()
                    .method(HttpMethod.GET)
                    .uri(String.format("users/%s", cpf))
                    .accept(MediaType.APPLICATION_JSON)
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve()
                    .onStatus(HttpStatus.NOT_FOUND::equals,
                            clientResponse -> clientResponse.bodyToMono(String.class).map(RestClientException::new))
                    .bodyToMono(String.class);

            return Objects.requireNonNull(response.block()).contains(ABLE_TO_VOTE);
        } catch (RestClientException e) {
            logger.error("Not found - 404");
            return false;
        }
    }
}
