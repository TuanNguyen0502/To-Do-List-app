package com.tuan.todolistapp.repositories;

import com.tuan.todolistapp.models.entites.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
