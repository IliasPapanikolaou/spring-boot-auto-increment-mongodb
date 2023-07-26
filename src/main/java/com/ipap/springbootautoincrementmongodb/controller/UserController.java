package com.ipap.springbootautoincrementmongodb.controller;

import com.ipap.springbootautoincrementmongodb.dto.NewUserRequest;
import com.ipap.springbootautoincrementmongodb.entity.User;
import com.ipap.springbootautoincrementmongodb.repository.UserRepository;
import com.ipap.springbootautoincrementmongodb.service.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final SequenceGeneratorService generatorService;

    public UserController(UserRepository userRepository, SequenceGeneratorService generatorService) {
        this.userRepository = userRepository;
        this.generatorService = generatorService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody NewUserRequest newUserRequest) {
        User user = new User();
        user.setId(generatorService.generateSequence(User.SEQUENCE_NAME));
        user.setEmail(newUserRequest.email());
        User usr = userRepository.save(user);
        log.info("{} saved successfully", usr);
        return ResponseEntity.ok(usr);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }
}
