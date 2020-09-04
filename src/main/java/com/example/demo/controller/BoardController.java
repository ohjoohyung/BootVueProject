package com.example.demo.controller;

import com.example.demo.entitiy.Board;
import com.example.demo.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    @PostMapping("/write")
    public Map<String, Object> write(@RequestBody Board board) {
        return boardService.insertBoard(board);
    }
}
