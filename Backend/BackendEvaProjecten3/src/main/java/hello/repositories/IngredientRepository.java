package hello.repositories;

import hello.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Matthias on 4/07/2017.
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
