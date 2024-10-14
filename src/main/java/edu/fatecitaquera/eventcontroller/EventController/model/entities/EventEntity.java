package edu.fatecitaquera.eventcontroller.EventController.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id
    private String id;
    private String name;
    private String startEvent;
    private String finishEvent;
}
