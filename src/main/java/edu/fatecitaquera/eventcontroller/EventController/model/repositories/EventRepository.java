package edu.fatecitaquera.eventcontroller.EventController.model.repositories;

import edu.fatecitaquera.eventcontroller.EventController.model.entities.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<EventEntity, String> {
}
