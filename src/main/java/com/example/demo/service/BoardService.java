package com.example.demo.service;

import com.example.demo.entitiy.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.specification.BoardSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {

    @Value("${board.pagesize}")
    private int pageSize;

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
            resultMap.put("msg", "게시글 등록 중 문제가 발생했습니다.");
        }
        return  resultMap;
    }

    //게시글 상세보기
    public Map<String, Object> readBoard(long id) {
        Map<String, Object> resultMap = new HashMap<>();

        Optional<Board> getBoard = boardRepository.findById(id);

        if(getBoard.isPresent()) {
            resultMap.put("result", 1);
            resultMap.put("board", getBoard);
        } else {
            resultMap.put("result", -1);
            resultMap.put("msg", "존재하지 않는 게시글 입니다.");
        }
        return resultMap;
    }

    //게시글 수정하기
    public Map<String, Object> updateBoard(Board board) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if(boardRepository.existsById(board.getId())) {
                Optional<Board> getBoard = boardRepository.findById(board.getId());
                if(getBoard.isPresent()) {
                    getBoard.get().setUserName(board.getUserName());
                    getBoard.get().setTitle(board.getTitle());
                    getBoard.get().setContent(board.getContent());

                    Long result = boardRepository.save(getBoard.get()).getId();
                    if(result > 0) {
                        resultMap.put("msg", "게시글이 수정되었습니다.");
                        resultMap.put("result", 1);
                    }else {
                        resultMap.put("msg", "게시글 수정 중 문제가 발생하였습니다.");
                        resultMap.put("result", -1);
                    }
                }else {
                    resultMap.put("msg", "존재하지 않는 게시글 입니다.");
                    resultMap.put("result", -2);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg", "처리 중 문제가 발생하였습니다.");
            resultMap.put("result", -3);

        }
        return resultMap;
    }

    //게시글 삭제하기
    public Map<String, Object> deleteBoard(long id) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            if(boardRepository.existsById(id)) {
                boardRepository.deleteById(id);

                resultMap.put("msg", "게시글이 삭제되었습니다.");
                resultMap.put("result", 1);
            }else {
                resultMap.put("msg", "존재하지 않는 게시글입니다.");
                resultMap.put("result", -1);
            }
        }catch (Exception e) {
            resultMap.put("msg", "게시글 삭제 중 문제가 발생했습니다.");
            resultMap.put("result", -2);
        }
        return resultMap;
    }

    //게시글 리스트 조회
    public Map<String, Object> readBoardList(int pageNo, Map<String, Object> listParam) {
        Map<String, Object> resultMap = new HashMap<>();
        System.out.println("파라미터 : " + listParam);
        if(!listParam.isEmpty()) {
            resultMap.put("list", boardRepository.findAll(BoardSpecification.searchBoard(listParam), PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"))));
        }else {
            resultMap.put("list", boardRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"))));
        }
        return resultMap;
    }

}
