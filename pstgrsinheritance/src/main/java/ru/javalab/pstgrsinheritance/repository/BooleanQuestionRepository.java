package ru.javalab.pstgrsinheritance.repository;

import ru.javalab.pstgrsinheritance.model.BooleanQuestion;

import java.util.List;

public interface BooleanQuestionRepository extends CrudRepository<BooleanQuestion, Long> {
    List<BooleanQuestion> findByAnswer(Boolean answer);
}
