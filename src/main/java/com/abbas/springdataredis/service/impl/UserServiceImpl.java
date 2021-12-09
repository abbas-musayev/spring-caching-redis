package com.abbas.springdataredis.service.impl;

import com.abbas.springdataredis.entity.User;
import com.abbas.springdataredis.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    private static final String HASH_KEY ="User";

    private final RedisTemplate redisTemplate;

    public UserServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String save(User user){
        redisTemplate.opsForHash().put(HASH_KEY,user.getId(),user);
        return "saved";
    }

    @Override
    public List<User> getAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    @Override
    public User findById(int id) {
        return (User) redisTemplate.opsForHash().get(HASH_KEY,id);
    }

    @Override
    public String delete(int id){
        redisTemplate.opsForHash().delete(HASH_KEY,id);
        return "deleted";
    }

    @Override
    public String update(User user) {
        redisTemplate.opsForHash().put(HASH_KEY,user.getId(),user);
        return "Updated";
    }


}
