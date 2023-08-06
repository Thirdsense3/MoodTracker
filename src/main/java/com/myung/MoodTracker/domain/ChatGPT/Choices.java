package com.myung.MoodTracker.domain.ChatGPT;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;

@Embeddable
@Data
public class Choices {
    private MessageGPT text;
    private Integer index;
    private String finish_reason;

    protected Choices() {

    }
}
