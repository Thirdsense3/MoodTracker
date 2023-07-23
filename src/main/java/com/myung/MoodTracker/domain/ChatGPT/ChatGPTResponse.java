package com.myung.MoodTracker.domain.ChatGPT;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatGPTResponse {
    private String id;
    private String object;
    private String crated;
    private String model;
    private Choices choices;
    private Usage usage;

}
