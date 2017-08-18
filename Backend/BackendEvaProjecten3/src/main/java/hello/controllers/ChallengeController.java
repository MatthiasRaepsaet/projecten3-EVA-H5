package hello.controllers;

import hello.domain.Challenge;
import hello.domain.EvaUser;
import hello.dtos.ChallengeUserDto;
import hello.repositories.ChallengeRepository;
import hello.repositories.EvaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private EvaUserRepository evaUserRepository;

    @RequestMapping(path = "/getmychallenge", method = RequestMethod.GET)
    public Challenge getChallenges(){
        /*List<Challenge> challengeList = new ArrayList<>();
        List<Challenge> resultList = new ArrayList<>();
        for(Challenge challenge : challengeRepository.findAll()){
            challengeList.add(challenge);
        }
        int random = 0;
        for(int i = 0; i<=2; i++){
            random = ThreadLocalRandom.current().nextInt(1, challengeList.size()-1);
            resultList.add(challengeList.get(random));
            challengeList.remove(random);
        }
        return resultList;*/
        List<Challenge> challengeList = (List<Challenge>) challengeRepository.findAll();
        int random = ThreadLocalRandom.current().nextInt(0, challengeList.size()-1);
        return challengeList.get(random);
    }

    @RequestMapping(path = "/addchallenge", method = RequestMethod.POST)
    public void addChallenge(@RequestBody Challenge challenge){
        challengeRepository.save(challenge);
    }

    @RequestMapping(path = "/getallchallenges", method = RequestMethod.GET)
    public Iterable<Challenge> getAllChallenges(){
        return challengeRepository.findAll();
    }

    @RequestMapping(path = "/selectchallenge", method = RequestMethod.POST)
    public void selectChallenge(@RequestBody ChallengeUserDto challengeUserDto){
        EvaUser user = evaUserRepository.findOne(challengeUserDto.getUserId());
        Challenge challenge = challengeRepository.findOne(challengeUserDto.getChallengeId());
        user.getTodaysChallenges().add(challenge);
        evaUserRepository.save(user);
    }

    @RequestMapping(path = "/completechallenge", method = RequestMethod.POST)
    public void completeChallenge(@RequestBody ChallengeUserDto challengeUserDto){
        EvaUser user = evaUserRepository.findOne(challengeUserDto.getUserId());
        Challenge challenge = challengeRepository.findOne(challengeUserDto.getChallengeId());
        user.getCompletedChallenges().add(challenge);
        evaUserRepository.save(user);
    }
}
