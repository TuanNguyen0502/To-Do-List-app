package com.tuan.todolistapp.models.entites;

import com.tuan.todolistapp.models.enums.ETaskStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntity{
    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ETaskStatus status;
}
