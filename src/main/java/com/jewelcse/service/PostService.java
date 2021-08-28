package com.jewelcse.service;

import com.jewelcse.entity.Post;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PostService {


    Post savePost(Post post);
    CompletableFuture<List<Post>> saveAllPost(List<Post> asList);
    List<Post> getAllPost();
}
