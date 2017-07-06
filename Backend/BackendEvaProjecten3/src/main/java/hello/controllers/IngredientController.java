package hello.controllers;

import hello.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Matthias on 4/07/2017.
 */
@RestController
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;


}
