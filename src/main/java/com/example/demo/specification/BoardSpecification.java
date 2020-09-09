package com.example.demo.specification;

import com.example.demo.entitiy.Board;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardSpecification {
    public static Specification<Board> searchBoard(Map<String, Object> filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filter.forEach((key, value) -> {
                String likeValue = "%" + value + "%";
                switch (key) {
                    case "title":
                    case "content":
                        predicates.add(criteriaBuilder.like(root.get(key).as(String.class), likeValue));
                        break;
                    case "titleContent":
                        predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("title").as(String.class), likeValue),
                                                          criteriaBuilder.like(root.get("content").as(String.class), likeValue)));
                        break;
                    case "userName":
                        predicates.add(criteriaBuilder.equal(root.get(key), value));
                        break;
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
