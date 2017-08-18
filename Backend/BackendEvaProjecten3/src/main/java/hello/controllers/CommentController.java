package hello.controllers;

import hello.domain.Comment;
import hello.domain.Recipe;
import hello.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Matthias on 4/07/2017.
 */
@RestController
@Transactional
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(path = "/upvotecomment", method = RequestMethod.PUT)
    public void upvoteComment(@RequestParam long id){
        Comment comment = commentRepository.findOne(id);
        comment.setUpvotes(comment.getUpvotes()+1);
        commentRepository.save(comment);
    }

    @RequestMapping(path = "/downvotecomment", method = RequestMethod.PUT)
    public void downvoteComment(@RequestParam long id){
        Comment comment = commentRepository.findOne(id);
        comment.setDownvotes(comment.getDownvotes()+1);
        commentRepository.save(comment);
    }
}
