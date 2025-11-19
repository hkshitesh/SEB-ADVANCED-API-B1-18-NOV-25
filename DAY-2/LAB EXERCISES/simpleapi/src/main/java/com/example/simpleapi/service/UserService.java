package com.example.simpleapi.service;

import com.example.simpleapi.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> store = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    // seed a couple users
    public UserService() {
        save(new User(null, "Alice", "alice@example.com"));
        save(new User(null, "Bob", "bob@example.com"));
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public User findById(Long id) {
        return store.get(id);
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGen.getAndIncrement());
        }
        store.put(user.getId(), user);
        return user;
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }

    public boolean exists(Long id) {
        return store.containsKey(id);
    }
}

