package com.example.demo.controller;

import com.example.demo.entitiy.Board;
import com.example.demo.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/board")
@RestController
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    @PostMapping("/write")
    public Map<String, Object> write(@RequestBody Board board) {
        return boardService.insertBoard(board);
    }

    @GetMapping("/content/{id}")
    public Map<String, Object> content(@PathVariable Long id) {
        return boardService.readBoard(id);
    }

   @PostMapping("/modify")
    public Map<String, Object> modify(@RequestBody Board board) {
        return boardService.updateBoard(board);
    }

    @GetMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }

    @GetMapping("/list/{pageNo}")
    public Map<String, Object> list(@PathVariable int pageNo,
                                    @RequestParam Map<String, Object> listParam) {
        return boardService.readBoardList(pageNo, listParam);
    }

}
