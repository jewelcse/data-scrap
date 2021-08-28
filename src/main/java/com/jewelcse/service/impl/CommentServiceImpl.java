package com.jewelcse.service.impl;


import com.jewelcse.entity.Comment;
import com.jewelcse.repository.CommentRepository;
import com.jewelcse.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CommentServiceImpl implements CommentService {

    Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    private CommentRepository commentRepository;

    CommentServiceImpl(CommentRepository repository){
        this.commentRepository = repository;
    }


    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Async
    @Override
    public CompletableFuture<List<Comment>> saveAllComment(List<Comment> comments) {
        logger.info("saving data.....");
        long start = System.currentTimeMillis();
        logger.info("Comments size {} ",comments.size());
        commentRepository.saveAll(comments);
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start));
        return CompletableFuture.completedFuture(comments);
    }

    @Override
    public List<Comment> getComments() {
        logger.info("fetching data from DB");
        return commentRepository.findAll();
    }
}
