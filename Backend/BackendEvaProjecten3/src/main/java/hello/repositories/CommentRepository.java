package hello.repositories;

import hello.domain.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Matthias on 4/07/2017.
 */
public interface CommentRepository extends CrudRepository<Comment, Long>{
}
