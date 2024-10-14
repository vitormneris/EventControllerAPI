package edu.fatecitaquera.eventcontroller.EventController.controllers;

import edu.fatecitaquera.eventcontroller.EventController.model.entities.UserEntity;
import edu.fatecitaquera.eventcontroller.EventController.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/todos")
    public ResponseEntity<List<UserEntity>> finAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}/encontrar")
    public ResponseEntity<UserEntity> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping("/inserir")
    public ResponseEntity<UserEntity> insert(@RequestBody UserEntity user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}/atualizar")
    public ResponseEntity<UserEntity> update(@PathVariable("id") String id, @RequestBody UserEntity user) {
        return ResponseEntity.ok().body(userService.update(user, id));
    }

    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
