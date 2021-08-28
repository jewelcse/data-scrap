package com.jewelcse.dao;


import com.jewelcse.entity.Post;
import com.jewelcse.repository.PostRepository;
import com.jewelcse.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class PostDao implements PostService {


    @Autowired
    private PostRepository postRepository;


    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public CompletableFuture<List<Post>> saveAllPost(List<Post> asList) {
        return null;
    }

    @Override
    @Cacheable(key = "#page", value = "posts")
    public List<Post> getAllPost(Integer page) {
        Page<Post> posts = postRepository.findAll(PageRequest.of(--page, 5));
        return posts.getContent();
    }
}
