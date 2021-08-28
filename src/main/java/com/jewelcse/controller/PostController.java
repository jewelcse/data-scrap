package com.jewelcse.controller;


import com.jewelcse.entity.Post;
import com.jewelcse.service.PostService;
import com.jewelcse.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {


    RestTemplate restTemplate = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/posts";


    @Autowired
    private PostService postService;
    
    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post){
        return new ResponseEntity<>(postService.savePost(post), HttpStatus.OK);
    }


    @PostMapping("/saveAll")
    public ResponseEntity saveThePost(){

        ResponseEntity<Post[]> response = restTemplate.getForEntity(url, Post[].class);
        Post[] posts = response.getBody();

        postService.saveAllPost(Arrays.asList(posts));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@RequestParam(defaultValue = "1") Integer page){
        return new ResponseEntity<List<Post>>(postService.getAllPost(page),HttpStatus.OK);
    }


}
