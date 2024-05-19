package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class UserService {
    private UserRepository repository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository repository, JwtUtil jwtUtil){
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public void addUser(String username, String email, String password) throws Exception {

        List<User> users = this.getAllUsers();
        for(User user: users){
            if(user.getEmail() == email){
                throw new Exception("User with this email already exists!.");
            }
        }
        User user = new User(username, email, password);
        this.repository.save(user);
    }

    public String checkLogin(String email, String password) {
        List<User> users = this.getAllUsers();
        for (User user : users) {
            if (Objects.equals(user.getEmail(), email) && Objects.equals(user.getPassword(), password)) {
                return jwtUtil.generateToken(email);
            }
        }
        return null;
    }

    public List<User> getAllUsers(){
        return (List<User>) this.repository.findAll();
    }

}
