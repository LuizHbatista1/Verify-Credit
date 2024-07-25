package com.api.verify_credit.service.proposal.simulatorScore;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimulatorScoreService implements GenerateScore {


    @Override
    public int Score() {
        Random score = new Random();
       return score.nextInt(0,1000);

    }

    @Override
    public boolean negativeName() {
        Random negativeName =  new Random();
        return negativeName.nextBoolean();
    }
}
