package com.jewelcse.service;

import com.jewelcse.entity.Comment;
import com.jewelcse.entity.Post;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CommentService {


    Comment saveComment(Comment comment);
    CompletableFuture<List<Comment>> saveAllComment(List<Comment> comments);
    List<Comment> getComments();
}
