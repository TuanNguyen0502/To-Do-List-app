package com.tuan.todolistapp.repositories.specifications;

import com.tuan.todolistapp.models.entites.Task;
import com.tuan.todolistapp.models.enums.ETaskStatus;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    public static Specification<Task> titleContains(String title) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Task> statusEquals(ETaskStatus status) {
        return (root, _, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }
}