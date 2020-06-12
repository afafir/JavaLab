package ru.javalab.pstgrsinheritance.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.javalab.pstgrsinheritance.model.Question;
import ru.javalab.pstgrsinheritance.repository.QuestionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class QuestionRepositoryJdbcImpl implements QuestionRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;


    private static final String FIND_ALL_QUERY = "SELECT * FROM question";
    private static  final String FIND_QUERY = "SELECT * FROM question WHERE id=?";
    private static final String INSERT = "INSERT INTO question(content) VALUES  (?)";







    @Override
    public Optional<Question> find(Long aLong) {
        try {
            Question question = jdbcTemplate.queryForObject(FIND_QUERY, new Object[]{aLong},  BeanPropertyRowMapper.newInstance(Question.class));
            return Optional.ofNullable(question);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Question> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, BeanPropertyRowMapper.newInstance(Question.class));
    }

    @Override
    public Question save(Question entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT, new String[]{"id"});
            statement.setString(1, entity.getContent());
            return statement;
        }, keyHolder);
        entity.setId(keyHolder.getKey().longValue());
        return entity;
    }

    @Override
    public Question update(Question entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
