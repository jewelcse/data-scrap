package com.jewelcse.controller;


import com.jewelcse.entity.Comment;
import com.jewelcse.entity.Post;
import com.jewelcse.service.CommentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    RestTemplate restTemplate = new RestTemplate();
    String url = "https://jsonplaceholder.typicode.com/comments";

    private CommentService commentService;

    CommentController(CommentService service){
        this.commentService = service;
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.saveComment(comment), HttpStatus.OK);
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveAllComment(){

        ResponseEntity<Comment[]> response = restTemplate.getForEntity(url, Comment[].class);
        Comment[] comments = response.getBody();

        commentService.saveAllComment(Arrays.asList(comments));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Cacheable
    public ResponseEntity<List<Comment>> getComments(){
        return new ResponseEntity<>(commentService.getComments(), HttpStatus.OK);
    }

}
