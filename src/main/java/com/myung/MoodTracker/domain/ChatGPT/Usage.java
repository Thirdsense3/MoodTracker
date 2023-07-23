package com.myung.MoodTracker.domain.ChatGPT;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Usage {
    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;

    protected Usage() {

    }
}
