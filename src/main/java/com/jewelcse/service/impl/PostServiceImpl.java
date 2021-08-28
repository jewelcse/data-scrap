package com.jewelcse.service.impl;


import com.jewelcse.entity.Post;
import com.jewelcse.repository.PostRepository;
import com.jewelcse.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PostServiceImpl implements PostService {



    private PostRepository postRepository;

    Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    PostServiceImpl(PostRepository repository){
        this.postRepository = repository;
    }

    @Override
    public Post savePost(Post post) {
        logger.info("saving data.....{}",post);
        return postRepository.save(post);
    }


    @Async
    @Override
    public CompletableFuture<List<Post>> saveAllPost(List<Post> posts) {

        logger.info("saving data.....");
        long start = System.currentTimeMillis();
        logger.info("posts size {} ",posts.size());
        postRepository.saveAll(posts);
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start));
        return CompletableFuture.completedFuture(posts);
    }

    @Override
    public List<Post> getAllPost() {
        logger.info("fetching data from DB");
        return postRepository.findAll();
    }
}
