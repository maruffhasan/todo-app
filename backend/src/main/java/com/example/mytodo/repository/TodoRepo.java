package com.example.mytodo.repository;

import com.example.mytodo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void save(Todo todo, String username) {
        String sql = """
            INSERT INTO todo (user_id, description)
            SELECT u.id, ?
            FROM users u
            WHERE u.username = ?
        """;
        jdbcTemplate.update(sql, todo.getDescription(), username);
    }

    public List<Todo> findAll(String username) {
        String sql = """
            SELECT t.id, t.description
            FROM todo t
            JOIN users u ON u.id = t.user_id
            WHERE u.username = ?
            ORDER BY t.id ASC
            """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Todo(
                rs.getInt("id"),
                rs.getString("description")
        ), username);
    }

    public Optional<Todo> find(long id) {
        try {
            String sql = "SELECT * FROM todo WHERE id = ?";
//            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
//                Todo a = new Todo();
//                a.setId(rs.getInt("id"));
//                a.setDescription((rs.getString("description")));
//                return a;
//            }, id);
            Todo todo = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Todo.class), id);
            return Optional.ofNullable(todo);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void update(Todo todo) {
        String sql = "UPDATE todo SET description = ? WHERE id = ?";
        jdbcTemplate.update(sql, todo.getDescription(), todo.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
