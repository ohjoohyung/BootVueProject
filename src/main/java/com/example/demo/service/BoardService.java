package com.example.demo.service;

import com.example.demo.entitiy.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //게시글 작성
    public Map<String, Object> insertBoard(Board board) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            Long boardId = boardRepository.save(board).getId();

            if(boardId > 0) {
                resultMap.put("result", 1);
                resultMap.put("msg", "게시글이 등록되었습니다.");
                resultMap.put("boardId", boardId);
            }
        } catch (Exception e) {
            resultMap.put("result", -1);
            resultMap.put("msg", "게시글 등록 에러 발생");
        }
        return  resultMap;
    }
}
