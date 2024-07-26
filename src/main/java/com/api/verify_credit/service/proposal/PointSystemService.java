package com.api.verify_credit.service.proposal;


import com.api.verify_credit.service.proposal.simulatorScore.SimulatorScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointSystemService {

    private SimulatorScoreService simulatorScoreService;

    @Autowired
    public PointSystemService(SimulatorScoreService simulatorScoreService) {
        this.simulatorScoreService = simulatorScoreService;
    }

    public int systemPoints() {

        int score = simulatorScoreService.Score();
        if (score < 100) {

            return 0;

        } else if (score <= 200) {

            return 30;

        } else if (score <= 450) {

            return 45;

        } else if (score <= 650) {

            return 60;

        }

        return 80;

    }

}
