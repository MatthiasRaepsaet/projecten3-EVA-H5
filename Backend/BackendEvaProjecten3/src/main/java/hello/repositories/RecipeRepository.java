package hello.repositories;

import hello.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Matthias on 4/07/2017.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long>{
}
