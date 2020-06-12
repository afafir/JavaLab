package ru.javalab.pstgrsinheritance.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javalab.pstgrsinheritance.model.BooleanQuestion;

public interface BooleanQuestionRepositoryJpaImpl extends JpaRepository<BooleanQuestion, Long> {

}
