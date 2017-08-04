package hello.controllers;

import hello.domain.Challenge;
import hello.repositories.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Matthias on 4/07/2017.
 */
@RestController
@Transactional
public class ChallengeController {
    @Autowired
    private ChallengeRepository challengeRepository;

    @RequestMapping(path = "/getmychallenges", method = RequestMethod.GET)
    public Iterable<Challenge> getThreeChallenges(){
        List<Challenge> challengeList = new ArrayList<>();
        List<Challenge> resultList = new ArrayList<>();
        for(Challenge challenge : challengeRepository.findAll()){
            challengeList.add(challenge);
        }
        int random = 0;
        for(int i = 0; i<=2; i++){
            random = ThreadLocalRandom.current().nextInt(0, challengeList.size()-1);
            resultList.add(challengeList.get(random));
            challengeList.remove(random);
        }
        return resultList;
    }

}
