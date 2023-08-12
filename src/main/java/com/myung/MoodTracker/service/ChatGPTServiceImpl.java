package com.myung.MoodTracker.service;

import com.myung.MoodTracker.domain.ChatGPT.ChatGPTParameter;
import com.myung.MoodTracker.domain.ChatGPT.ChatGPTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService {

    private final RestTemplate restTemplate;

    @Value("${chatGPT.OPEN_AI.KEY}")
    private final String key;

    @Override
    public ChatGPTResponse getChatGPTResponse(ChatGPTParameter chatGPTParameter) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://api.openai.com")
                .path("v1/chat/completions")
                .encode()
                .build()
                .toUri();

        RequestEntity<ChatGPTParameter> requestEntity = RequestEntity
                .post(uri)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + key)
                .body(chatGPTParameter);

        ResponseEntity<ChatGPTResponse> responseEntity = restTemplate.exchange(requestEntity, ChatGPTResponse.class);


        return null;
    }
}
