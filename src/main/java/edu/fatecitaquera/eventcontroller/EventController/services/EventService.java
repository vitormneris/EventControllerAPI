package edu.fatecitaquera.eventcontroller.EventController.services;

import edu.fatecitaquera.eventcontroller.EventController.model.entities.EventEntity;
import edu.fatecitaquera.eventcontroller.EventController.model.entities.UserEntity;
import edu.fatecitaquera.eventcontroller.EventController.model.repositories.EventRepository;
import edu.fatecitaquera.eventcontroller.EventController.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

    public EventEntity findById(String id) {
            return eventRepository.findById(id).orElseThrow();
    }

    public void userAddEvent( String userId, String eventId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Instant now = Instant.now();

        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        EventEntity eventEntity = eventRepository.findById(eventId).orElseThrow();

        if (eventEntity.getUsers().stream().anyMatch((user) -> user.getId().equals(userId))) {
            UserEntity foundUser = eventEntity.getUsers().stream().filter((user) -> user.getId().equals(userId)).findFirst().orElseThrow();
            if (foundUser.getUserOut() == null) {
                foundUser.setName(userEntity.getName());

                foundUser.setUserOut(formatter.format(now));
            } else foundUser.setName(userEntity.getName());

        } else {
            UserEntity user = new UserEntity();
            user.setId(userEntity.getId());
            user.setName(userEntity.getName());
            user.setUserIn(formatter.format(now));
            eventEntity.getUsers().add(user);
        }
        eventRepository.save(eventEntity);
    }


    public EventEntity save(EventEntity eventEntity) {
        eventEntity.setId(null);
        return eventRepository.save(eventEntity);
    }

    public EventEntity update(EventEntity eventNew, String id) {
        EventEntity eventOld = findById(id);
        eventOld.setName(eventNew.getName() == null ? eventOld.getName() : eventNew.getName());
        eventOld.setStartEvent(eventNew.getStartEvent() == null ? eventOld.getStartEvent() : eventNew.getStartEvent());
        eventOld.setFinishEvent(eventNew.getFinishEvent() == null ? eventOld.getFinishEvent() : eventNew.getFinishEvent());
        return eventRepository.save(eventOld);
    }

    public void delete(String id) {
        eventRepository.delete(findById(id));
    }
}
