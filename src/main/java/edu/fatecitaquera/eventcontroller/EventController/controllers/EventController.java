package edu.fatecitaquera.eventcontroller.EventController.controllers;

import edu.fatecitaquera.eventcontroller.EventController.model.entities.EventEntity;
import edu.fatecitaquera.eventcontroller.EventController.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/todos")
    public ResponseEntity<List<EventEntity>> finAll() {
        return ResponseEntity.ok().body(eventService.findAll());
    }

    @GetMapping("/{id}/encontrar")
    public ResponseEntity<EventEntity> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(eventService.findById(id));
    }

    @GetMapping("/usuarioid/{userId}/eventoid/{eventId}")
    public ResponseEntity<Void> userAddEvent(@PathVariable("userId") String userId, @PathVariable("eventId") String eventId) {
        eventService.userAddEvent(userId, eventId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/inserir")
    public ResponseEntity<EventEntity> insert(@RequestBody EventEntity event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(event));
    }

    @PutMapping("/{id}/atualizar")
    public ResponseEntity<EventEntity> update(@PathVariable("id") String id, @RequestBody EventEntity event) {
        return ResponseEntity.ok().body(eventService.update(event, id));
    }

    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
