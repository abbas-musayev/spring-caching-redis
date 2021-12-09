package com.abbas.springdataredis.controller;

import com.abbas.springdataredis.entity.User;
import com.abbas.springdataredis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<String> save(User user){
        return ResponseEntity.ok(service.save(user));
    }

    @PutMapping
    public ResponseEntity<String> edit(User user){
        return ResponseEntity.ok(service.update(user));
    }

    @DeleteMapping
    public ResponseEntity<String> delete(int id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping
    public ResponseEntity<User> getById(@RequestParam int id){
        return ResponseEntity.ok(service.findById(id));
    }


}
