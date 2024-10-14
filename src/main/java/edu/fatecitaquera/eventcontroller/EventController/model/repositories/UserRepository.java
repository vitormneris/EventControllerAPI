package edu.fatecitaquera.eventcontroller.EventController.model.repositories;

import edu.fatecitaquera.eventcontroller.EventController.model.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
