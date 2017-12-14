package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.NewActivity;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.NewEvent;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.repository.ActivityRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.EventRepository;

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addNewEvent(NewEvent newEvent) {
        if(newEvent == null) {
            throw new NotFoundException("Event data not found");
        }

        Event event = new Event();
        event.setActivityId(newEvent.getActivityId());
        event.setPlaceId(newEvent.getPlaceId());
        event.setDate(newEvent.getDate());

        Event saved = eventRepository.save(event);
        if(saved != null) {
            return saved;
        }
        else {
            throw new ServerException("Event has not been saved due to server problems");
        }
    }
}
