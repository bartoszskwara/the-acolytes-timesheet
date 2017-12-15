package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.NewEvent;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Place;
import pl.lso.kazimierz.theacolytestimesheet.repository.ActivityRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.EventRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.PlaceRepository;

@Service
public class EventService {

    private EventRepository eventRepository;
    private ActivityRepository activityRepository;
    private PlaceRepository placeRepository;

    @Autowired
    public EventService(EventRepository eventRepository,
                        ActivityRepository activityRepository,
                        PlaceRepository placeRepository) {
        this.eventRepository = eventRepository;
        this.activityRepository = activityRepository;
        this.placeRepository = placeRepository;
    }

    public Event addNewEvent(NewEvent newEvent) {
        if(newEvent == null) {
            throw new NotFoundException("Event data not found");
        }

        Activity activity = activityRepository.findOne(newEvent.getActivityId());
        if(activity == null) {
            throw new NotFoundException("Activity not found");
        }
        Place place = placeRepository.findOne(newEvent.getPlaceId());
        if(activity == null) {
            throw new NotFoundException("Place not found");
        }

        Event event = new Event();
        event.setActivity(activity);
        event.setPlace(place);
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
