package ru.javalab.pstgrsinheritance.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.javalab.pstgrsinheritance.model.Question;
import ru.javalab.pstgrsinheritance.repository.QuestionRepository;

import javax.swing.text.Document;
import java.util.List;

public interface QuestionRepositoryJpaImpl extends JpaRepository<Question, Long> {


}
