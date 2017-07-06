package hello.controllers;

import hello.domain.EvaUser;
import hello.dtos.LoginDto;
import hello.repositories.EvaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Matthias on 3/07/2017.
 */
@RestController
public class UserController {

    @Autowired
    private EvaUserRepository evaUserRepository;

    @RequestMapping("/getusers")
    public Iterable<EvaUser> greeting() {
        return evaUserRepository.findAll();
    }

    @RequestMapping("/adduser")
    public void addUser(@RequestBody EvaUser newUser){
        EvaUser evaUser = newUser;
        evaUserRepository.save(evaUser);
    }

    @RequestMapping("/login")
    public EvaUser login(@RequestBody LoginDto loginDto){
        Iterable<EvaUser> userList = evaUserRepository.findAll();
        EvaUser result = null;
        for(EvaUser evaUser : userList){
            if(evaUser.getUsername().equals(loginDto.getUsername())&&evaUser.getPassword().equals(loginDto.getPassword())){
                result = evaUser;
            }
        }
        return result;
    }
}
