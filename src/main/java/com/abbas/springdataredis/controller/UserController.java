package com.abbas.springdataredis.controller;

import com.abbas.springdataredis.entity.User;
import com.abbas.springdataredis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public String save(@RequestBody User user) {
        return service.save(user);
    }

    @PutMapping
    public String edit(@RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return service.delete(id);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }


    @GetMapping("/all")
    public List<String> getAlls() {
        return Arrays.asList("abbas","resul");
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return service.findById(id);
    }
}