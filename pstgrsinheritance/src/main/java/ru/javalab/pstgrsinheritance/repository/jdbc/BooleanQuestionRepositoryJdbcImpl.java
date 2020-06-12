package ru.javalab.pstgrsinheritance.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.javalab.pstgrsinheritance.model.BooleanQuestion;
import ru.javalab.pstgrsinheritance.model.Question;
import ru.javalab.pstgrsinheritance.repository.BooleanQuestionRepository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository("jpaJdbc")
public class BooleanQuestionRepositoryJdbcImpl implements BooleanQuestionRepository {



    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_QUERY = "SELECT * FROM boolean_question";
    private static  final String FIND_QUERY = "SELECT * FROM boolean_question WHERE id=?";
    private static final String INSERT = "INSERT INTO boolean_question(content, answer) VALUES  (?, ?)";
    private static final String FIND_BY_ANSWER_QUERY = "SELECT * FROM boolean_question WHERE answer=?";
    @Override
    public List<BooleanQuestion> findByAnswer(Boolean answer) {
        return jdbcTemplate.query(FIND_BY_ANSWER_QUERY, new Object[]{answer}, BeanPropertyRowMapper.newInstance(BooleanQuestion.class));
    }

    @Override
    public Optional<BooleanQuestion> find(Long aLong) {
        try {
            BooleanQuestion question = jdbcTemplate.queryForObject(FIND_QUERY, new Object[]{aLong},  BeanPropertyRowMapper.newInstance(BooleanQuestion.class));
            return Optional.ofNullable(question);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<BooleanQuestion> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, BeanPropertyRowMapper.newInstance(BooleanQuestion.class));
    }

    @Override
    public BooleanQuestion save(BooleanQuestion booleanQuestion) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT, new String[]{"id"});
            statement.setString(1, booleanQuestion.getContent());
            statement.setBoolean(2, booleanQuestion.getAnswer());
            return statement;
        }, keyHolder);
        booleanQuestion.setId(keyHolder.getKey().longValue());
        return booleanQuestion;
    }

    @Override
    public BooleanQuestion update(BooleanQuestion entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
