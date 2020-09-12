package com.example.demo.controller;

import com.example.demo.entitiy.Board;
import com.example.demo.entitiy.Comment;
import com.example.demo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/comment")
@RestController
@AllArgsConstructor
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/write/{boardNo}")
    public Map<String, Object> write(@RequestBody Comment comment, @PathVariable long boardNo) {
        return commentService.insertComment(comment, boardNo);
    }

    @PostMapping("/modify/{boardNo}")
    public Map<String, Object> modify(@RequestBody Comment comment, @PathVariable long boardNo) {
        return commentService.updateComment(comment, boardNo);
    }

    @GetMapping("/delete/{boardNo}/{commentNo}")
    public Map<String, Object> delete(@PathVariable long boardNo, @PathVariable long commentNo) {
        return commentService.deleteComment(boardNo, commentNo);
    }

    @GetMapping("/list/{boardNo}")
    public Map<String, Object> list(@PathVariable long boardNo) {
        return commentService.readCommentList(boardNo);
    }
}
