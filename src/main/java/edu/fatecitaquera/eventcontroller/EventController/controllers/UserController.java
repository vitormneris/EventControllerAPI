package edu.fatecitaquera.eventcontroller.EventController.controllers;

import edu.fatecitaquera.eventcontroller.EventController.model.entities.UserEntity;
import edu.fatecitaquera.eventcontroller.EventController.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}/encontrarporid")
    public ResponseEntity<UserEntity> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @GetMapping("/{email}/encontrarporemail")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(userService.findByEmail(email));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserEntity user) {
        return ResponseEntity.ok().body(userService.login(user));
    }

    @PostMapping("/inserir")
    public ResponseEntity<UserEntity> insert(@RequestBody UserEntity user) {
        UserEntity userEntity = userService.save(user);
        if (userEntity == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
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
