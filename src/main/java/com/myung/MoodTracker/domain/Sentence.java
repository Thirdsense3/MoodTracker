package com.myung.MoodTracker.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
@Entity
public class Sentence {
    private String sentence;
}
