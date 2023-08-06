package com.myung.MoodTracker.service;

import com.myung.MoodTracker.domain.ChatGPT.ChatGPTParameter;
import com.myung.MoodTracker.domain.ChatGPT.ChatGPTResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class ChatGPTServiceImpl implements ChatGPTService{
    @Override
    public ChatGPTResponse getChatGPTResponse(ChatGPTParameter chatGPTParameter) {

        try {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000);




        }

        return null;
    }
}
