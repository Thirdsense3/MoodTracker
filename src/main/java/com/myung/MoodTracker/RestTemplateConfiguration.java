package com.myung.MoodTracker;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;

@Configuration
@EnableRetry
public class RestTemplateConfiguration {

    private static final int TIMEOUT_SECOND = 5;
    private static final int MAX_RETRY = 3;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(TIMEOUT_SECOND))
                .setReadTimeout(Duration.ofSeconds(TIMEOUT_SECOND))
                .additionalInterceptors(clientHttpRequestInterceptor())
                .build();

    }

    public ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
        return (request, body, execution) -> {
            RetryTemplate retryTemplate = new RetryTemplate();
            retryTemplate.setRetryPolicy(new SimpleRetryPolicy(MAX_RETRY));

            try {
                return retryTemplate.execute(context -> execution.execute(request, body));
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        };
    }

    @Slf4j
    static class LoggingInterceptor implements ClientHttpRequestInterceptor {
        @NotNull
        @Override
        public ClientHttpResponse intercept(@NotNull HttpRequest request, @NotNull byte[] body, ClientHttpRequestExecution execution) throws IOException {
            final String sessionNumber = makeSessionNumber();
            printRequest(sessionNumber, request, body);

            ClientHttpResponse execute = execution.execute(request, body);

            printResponse(sessionNumber, execute);

            return execute;
        }

        private String makeSessionNumber() {
            return Integer.toString((int) (Math.random() * 100000));
        }

        private void printRequest(final String sessionNumber, final HttpRequest request, final byte[] body) {
            log.info("[{}] URI : {}, Method : {}, Headers : {}, Body : {}",
                    sessionNumber, request.getURI(), request.getMethod(), request.getHeaders(), new String((body), StandardCharsets.UTF_8));
        }

        private void printResponse(final String sessionNumber, final ClientHttpResponse clientHttpResponse) throws IOException {
            String body = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody(), StandardCharsets.UTF_8)).lines()
                    .collect(Collectors.joining("\n"));

            log.info("[{}] Status : {}, Headers : {}, Body : {}",
                    sessionNumber, clientHttpResponse.getStatusCode(), clientHttpResponse.getHeaders(), body);
        }
    }

}
