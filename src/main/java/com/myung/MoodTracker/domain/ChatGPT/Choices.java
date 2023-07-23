package com.myung.MoodTracker.domain.ChatGPT;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Choices {
    private String text;
    private Integer index;
    private String logProbs;
    private String finish_reason;

    protected Choices() {

    }
}
