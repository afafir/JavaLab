package ru.javalab.pstgrsinheritance.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class StringQuestion extends AbstractQuestion{
    private String stringAnswer;
}
