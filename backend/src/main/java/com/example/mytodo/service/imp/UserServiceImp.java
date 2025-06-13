    package com.example.mytodo.service.imp;

    import com.example.mytodo.DTO.UserDTO;
    import com.example.mytodo.model.User;
    import com.example.mytodo.repository.UserRepo;
    import com.example.mytodo.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Map;

    @Service
    public class UserServiceImp implements UserService {

        @Autowired
        private UserRepo userRepo;
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void signUp(User user) {
            if (userRepo.userNameExists(user.getUsername())) {
                throw new IllegalArgumentException("username already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }

        @Override
        public Map<String, String> getName(String username) {
            String fullName = userRepo.getName(username);
            return Map.of("name", fullName);
        }

        @Override
        public List<UserDTO> getUsers() {
            return userRepo.getUsers();
        }

        @Override
        public void deleteUser(int id) {
            userRepo.delete(id);
        }
    }