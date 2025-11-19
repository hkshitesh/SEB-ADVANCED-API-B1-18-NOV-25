package com.example.simpleapi.controller;

import com.example.simpleapi.model.User;
import com.example.simpleapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService svc;
    public UserController(UserService svc) { this.svc = svc; }

    @GetMapping
    public List<User> list() {
        return svc.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        User u = svc.findById(id);
        return (u == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = svc.save(user);
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        if (!svc.exists(id)) return ResponseEntity.notFound().build();
        user.setId(id);
        return ResponseEntity.ok(svc.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!svc.exists(id)) return ResponseEntity.notFound().build();
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
