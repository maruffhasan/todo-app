package com.example.mytodo.repository;

import com.example.mytodo.DTO.UserDTO;
import com.example.mytodo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "insert into users (username, password, name, role) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getRole());
    }

    public String getName(String username) {
        String sql = "select name from users where username = ?";
        return  jdbcTemplate.queryForObject(sql, String.class, username);
    }

    public User getUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("role")
        ), username);
    }

    public boolean userNameExists(String username) {
        String sql = "select count(*) from users where username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    public List<UserDTO> getUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new UserDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("username")
        ));
    }

    public void delete(int id) {
        String sql = "delete from users where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
