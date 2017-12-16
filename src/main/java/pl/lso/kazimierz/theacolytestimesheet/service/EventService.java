package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.ActivityDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.EventDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.PlaceDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.NewEvent;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.place.PlaceDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Place;
import pl.lso.kazimierz.theacolytestimesheet.repository.ActivityRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.EventRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.PlaceRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Event> getEventsByPlaceId(Long placeId) {
        if(placeId == null) {
            throw new NotFoundException("Place not found");
        }
        List<Event> events = eventRepository.findByPlace_Id(placeId);
        events.sort(Comparator.comparing(Event::getDate).reversed());
        return events;
    }

    public List<EventDto> mapEventListToEventDtoList(List<Event> events) {
        return events.stream()
                .map(e -> {
                    ActivityDto activity = ActivityDtoBuilder.getInstance()
                            .withId(e.getActivity().getId())
                            .withName(e.getActivity().getName())
                            .withValue(e.getActivity().getValue())
                            .withEvents(null)
                            .build();
                    PlaceDto place = PlaceDtoBuilder.getInstance()
                            .withId(e.getPlace().getId())
                            .withName(e.getPlace().getName())
                            .withCoordinates(e.getPlace().getCoordinates())
                            .withEvents(null)
                            .build();

                    return EventDtoBuilder.getInstance()
                            .withId(e.getId())
                            .withPlace(place)
                            .withActivity(activity)
                            .withDate(e.getDate())
                            .build();
                })
                .collect(Collectors.toList());
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
