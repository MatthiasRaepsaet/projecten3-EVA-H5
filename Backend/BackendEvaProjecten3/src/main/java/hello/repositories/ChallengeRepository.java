package hello.repositories;

import hello.domain.Challenge;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Matthias on 4/07/2017.
 */
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {
}
