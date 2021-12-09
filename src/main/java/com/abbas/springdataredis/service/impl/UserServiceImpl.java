package com.abbas.springdataredis.service.impl;

import com.abbas.springdataredis.entity.User;
import com.abbas.springdataredis.repository.UserRepo;
import com.abbas.springdataredis.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String HASH_KEY = "User";

    private final RedisTemplate redisTemplate;
    private final UserRepo userRepo;


    @Override
    public String save(User user) {
        log.info("Save Method Started");
        redisTemplate.opsForHash().put(HASH_KEY, user.getId(), user);
        User save = userRepo.save(user);
        log.info("UserRepo: {}", save);
        return "saved" + user.getId();
    }

    @Override
    public List getAll() {
        log.info("GETALL Started");
        List values = redisTemplate.opsForHash().values(HASH_KEY);
        if (!values.isEmpty()){
            log.info("GetALl From Redis");
            return values;
        }
        return userRepo.findAll();
    }

    @Override
    public User findById(int id) {
        log.info("FindById Method Started");
        User user = (User) redisTemplate.opsForHash().get(HASH_KEY, id);
        if (user != null) {
            log.info("FindById From Redis");
            return user;
        }
        return userRepo.findById(id).
                orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public String delete(int id) {
        Long delete = redisTemplate.opsForHash().delete(HASH_KEY, id);
        userRepo.deleteById(id);
        return "deleted"+delete;
    }

    @Override
    public String update(User user) {
        redisTemplate.opsForHash().put(HASH_KEY, user.getId(), user);
        userRepo.save(user);
        return "Updated";
    }


}
