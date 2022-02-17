package com.example.demo.control;

import com.example.demo.classes.UserDTO;
import com.example.demo.converters.UserConverter;
import com.example.demo.entity.User;
import com.example.demo.jwt.JwtProvider;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/log")
public class RegController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserConverter userConverter;

    @Autowired
    UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<?> signIn(@RequestBody UserDTO userDTO){
        User user = userService.findUserByLoginAndPassword(
                userDTO.getLogin(), userDTO.getPassword());
        if (user != null){
            String token = jwtProvider.generateToken(user.getLogin());

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token", token);
            map.put("user", userDTO);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unfounded user", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/register")
    public  ResponseEntity<?> signUp(@RequestBody UserDTO userDTO){
        if (!userService.isUserExist(userDTO.getLogin())){
            User newUser = userConverter.convertToUser(userDTO);
            userService.saveUser(newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("User " + userDTO.getLogin() + " already exist",HttpStatus.BAD_REQUEST);
    }
}
