package ru.javalab.queue.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(schema = "public", name = "task")
public class Task {
    @Id
    private String generatedId;
    private String queueName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Type  type;
    private Boolean isDone;


}
