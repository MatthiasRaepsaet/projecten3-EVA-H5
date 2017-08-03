package hello.controllers;

import hello.domain.EvaUser;
import hello.domain.Recipe;
import hello.dtos.LoginDto;
import hello.repositories.EvaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Matthias on 3/07/2017.
 */
@RestController
@javax.transaction.Transactional
public class UserController {

    @Autowired
    private EvaUserRepository evaUserRepository;

    @RequestMapping("/getusers")
    public Iterable<EvaUser> getAllUsers() {
        return evaUserRepository.findAll();
    }

    @RequestMapping("/adduser")
    public void addUser(@RequestBody EvaUser newUser){
        EvaUser evaUser = new EvaUser(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getUsername(), newUser.getPassword());
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

    public void recipe(Recipe recipe, long userId){
        EvaUser user = evaUserRepository.findOne(userId);
        user.getMyRecipes().add(recipe);
        evaUserRepository.save(user);
    }

    public EvaUser getUserById(long userId){
        return evaUserRepository.findOne(userId);
    }
}
