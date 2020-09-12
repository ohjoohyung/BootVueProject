package com.example.demo.service;

import com.example.demo.entitiy.Board;
import com.example.demo.entitiy.Comment;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.specification.BoardSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Lob;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    //댓글 작성
    public Map<String, Object> insertComment(Comment comment, long boardNo) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            Optional<Board> getBoard = boardRepository.findById(boardNo);
            if(getBoard.isPresent()) {
                comment.setBoard(getBoard.get());
                Long result = commentRepository.save(comment).getId();
                if(result > 0) {
                    resultMap.put("result", 1);
                }else {
                    resultMap.put("result", -1);
                    resultMap.put("msg", "댓글 등록 중 문제가 발생하였습니다.");
                }
            }else {
                resultMap.put("result", -2);
                resultMap.put("msg", "게시글이 존재하지 않습니다.");
            }
        } catch (Exception e) {
            resultMap.put("result", -3);
            resultMap.put("msg", "처리 중 문제가 발생했습니다.");
        }
        resultMap.put("list", commentRepository.getCommentsByBoardNo(boardNo));
        return  resultMap;
    }


    //댓글 수정하기
    public Map<String, Object> updateComment(Comment comment, long boardNo) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if(commentRepository.existsById(comment.getId())) {
                Optional<Comment> getComment = commentRepository.findById(comment.getId());
                if(getComment.isPresent()) {
                    getComment.get().setContent(comment.getContent());
                    getComment.get().setUserName(comment.getUserName());

                    Long result = commentRepository.save(getComment.get()).getId();
                    if(result > 0) {
                        resultMap.put("result", 1);


                    }else {
                        resultMap.put("result", -1);
                        resultMap.put("msg", "댓글 수정 중 문제가 발생하였습니다.");
                    }
                }else {
                    resultMap.put("result", -2);
                    resultMap.put("msg", "존재하지 않는 댓글입니다.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg", "처리 중 문제가 발생하였습니다.");
            resultMap.put("result", -3);

        }
        resultMap.put("list", commentRepository.getCommentsByBoardNo(boardNo));

        return resultMap;
    }

    //댓글 삭제하기
    public Map<String, Object> deleteComment(long boardNo, long commentNo) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            if(commentRepository.existsById(commentNo)) {
                commentRepository.deleteById(commentNo);

                resultMap.put("msg", "댓글이 삭제되었습니다.");
                resultMap.put("result", 1);

            }else {
                resultMap.put("msg", "존재하지 않는 댓글입니다.");
                resultMap.put("result", -1);
            }
        }catch (Exception e) {
            resultMap.put("msg", "댓글 삭제 중 문제가 발생했습니다.");
            resultMap.put("result", -2);
        }
        resultMap.put("list", commentRepository.getCommentsByBoardNo(boardNo));
        return resultMap;
    }

    //댓글 리스트 조회
    public Map<String, Object> readCommentList(long boardNo) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", commentRepository.getCommentsByBoardNo(boardNo));
        return resultMap;
    }
}
