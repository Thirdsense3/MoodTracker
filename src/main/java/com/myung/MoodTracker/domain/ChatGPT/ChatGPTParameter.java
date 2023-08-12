package com.myung.MoodTracker.domain.ChatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatGPTParameter {
    private String model;
    private String messages;
    private Double temperature;
}
