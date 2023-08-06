package com.myung.MoodTracker.service;

import com.myung.MoodTracker.domain.ChatGPT.ChatGPTParameter;
import com.myung.MoodTracker.domain.ChatGPT.ChatGPTResponse;
import org.springframework.stereotype.Service;

@Service
public interface ChatGPTService {
    ChatGPTResponse getChatGPTResponse(ChatGPTParameter chatGPTParameter);
}
