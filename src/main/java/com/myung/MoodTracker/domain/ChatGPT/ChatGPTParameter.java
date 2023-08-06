package com.myung.MoodTracker.domain.ChatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ChatGPTParameter {
    private String model;
    private String prompt;
    private Integer max_token;
    private Integer temperature;
    private Integer top_p;
    private Integer n;
    private Boolean stream;
    private String logProbs;
    private String stop;
}
