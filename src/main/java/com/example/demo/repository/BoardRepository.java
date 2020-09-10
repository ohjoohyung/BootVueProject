package com.example.demo.repository;

import com.example.demo.entitiy.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface BoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {

}
