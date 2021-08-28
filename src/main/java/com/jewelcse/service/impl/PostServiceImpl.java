package com.jewelcse.service.impl;


import com.jewelcse.dao.PostDao;
import com.jewelcse.entity.Post;
import com.jewelcse.repository.PostRepository;
import com.jewelcse.service.PostService;
import com.jewelcse.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private Mapper mapper;

    Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);


    @Override
    public Post savePost(Post post) {
        logger.info("saving data.....{}",post);
        return postDao.savePost(post);
    }


    @Async
    @Override
    public CompletableFuture<List<Post>> saveAllPost(List<Post> posts) {

        logger.info("saving data.....");
        long start = System.currentTimeMillis();
        logger.info("posts size {} ",posts.size());
        postDao.saveAllPost(posts);
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start));
        return CompletableFuture.completedFuture(posts);
    }

    @Override
    public List<Post> getAllPost(Integer page) {
        logger.info("fetching data from DB");
        return mapper.convertToList(postDao.getAllPost(page),Post.class);
    }
}
