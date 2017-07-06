package hello.repositories;

import hello.domain.EvaUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Matthias on 3/07/2017.
 */
public interface EvaUserRepository extends CrudRepository<EvaUser, Long> {

}
