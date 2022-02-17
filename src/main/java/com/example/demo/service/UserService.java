package com.example.demo.service;

import com.example.demo.classes.ERole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){
        Role role = roleRepository.findByName(ERole.ROLE_USER);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }

    public User findUserByLoginAndPassword(String login, String password) {
        if (userRepository.existsByLogin(login)){
            User founded = findUserByLogin(login);
            if (passwordEncoder.matches(password, founded.getPassword()))
                return founded;
        }
        return null;
    }

    public boolean isUserExist(String login){
        return userRepository.existsByLogin(login);
    }


}
