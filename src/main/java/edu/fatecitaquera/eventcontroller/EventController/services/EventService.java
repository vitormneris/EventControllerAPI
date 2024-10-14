package edu.fatecitaquera.eventcontroller.EventController.services;

import edu.fatecitaquera.eventcontroller.EventController.model.entities.EventEntity;
import edu.fatecitaquera.eventcontroller.EventController.model.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

    public EventEntity findById(String id) {
            return eventRepository.findById(id).orElseThrow();
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
