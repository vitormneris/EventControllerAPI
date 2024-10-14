package edu.fatecitaquera.eventcontroller.EventController.services;

import edu.fatecitaquera.eventcontroller.EventController.model.entities.EventEntity;
import edu.fatecitaquera.eventcontroller.EventController.model.entities.UserEntity;
import edu.fatecitaquera.eventcontroller.EventController.model.repositories.EventRepository;
import edu.fatecitaquera.eventcontroller.EventController.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity findById(String id) {
            return userRepository.findById(id).orElseThrow();
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public Boolean login(UserEntity userEntity) {
        UserEntity entity = userRepository.findByEmail(userEntity.getEmail()).orElse(null);
        if (entity != null) {
            return entity.getPassword().equals(userEntity.getPassword());
        } else return false;
    }

    public UserEntity save(UserEntity userEntity) {
        userEntity.setId(null);
        return userRepository.save(userEntity);
    }

    public UserEntity update(UserEntity userNew, String id) {
        UserEntity userOld = findById(id);
        userOld.setName(userNew.getName() == null ? userOld.getName() : userNew.getName());
        userOld.setEmail(userNew.getEmail() == null ? userOld.getEmail() : userNew.getEmail());
        userOld.setPassword(userNew.getPassword() == null ? userOld.getPassword() : userNew.getPassword());
        return userRepository.save(userOld);
    }

    public void delete(String id) {
        userRepository.delete(findById(id));
    }
}
