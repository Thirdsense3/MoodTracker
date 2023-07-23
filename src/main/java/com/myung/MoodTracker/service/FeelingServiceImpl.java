package com.myung.MoodTracker.service;

import org.springframework.stereotype.Service;

@Service
public class FeelingServiceImpl implements FeelingService{
    @Override
    public void recommendSongByFeeling(String feeling) {
        System.out.println("feeling = " + feeling);



    }
}
