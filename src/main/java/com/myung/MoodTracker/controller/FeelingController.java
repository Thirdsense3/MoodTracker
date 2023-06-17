package com.myung.MoodTracker.controller;

import com.myung.MoodTracker.domain.Sentence;
import com.myung.MoodTracker.service.FeelingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FeelingController {
    private final FeelingService feelingService;

    @PostMapping("/feeling")
    public String feeling(SentenceForm sentenceForm) {
        System.out.println("sentenceForm = " + sentenceForm);

        Sentence newSentence = new Sentence();
        newSentence.setSentence(sentenceForm.getSentence());

        feelingService.recommendSongByFeeling(newSentence.getSentence());

        return "feeling";
    }
}
