package com.myung.MoodTracker.service;

import org.springframework.stereotype.Service;

@Service
public interface FeelingService {
    public void recommendSongByFeeling(String feeling);
}
