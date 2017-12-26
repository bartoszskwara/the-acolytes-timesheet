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
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.ScheduleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Place;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Schedule;
import pl.lso.kazimierz.theacolytestimesheet.repository.ActivityRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.EventRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.PlaceRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.ScheduleRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    private EventRepository eventRepository;
    private ActivityRepository activityRepository;
    private PlaceRepository placeRepository;
    private ScheduleRepository scheduleRepository;

    @Autowired
    public EventService(EventRepository eventRepository,
                        ActivityRepository activityRepository,
                        PlaceRepository placeRepository,
                        ScheduleRepository scheduleRepository) {
        this.eventRepository = eventRepository;
        this.activityRepository = activityRepository;
        this.placeRepository = placeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public List<Event> getEventsByPlaceId(Long placeId) {
        if(placeId == null) {
            throw new NotFoundException("Place not found");
        }
        List<Event> events = eventRepository.findByPlace_Id(placeId);
        events.sort(Comparator.comparing(Event::getStartDate).reversed());
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
                            .withCoordinates(e.getPlace().getLongitude(), e.getPlace().getLatitude(), e.getPlace().getAltitude())
                            .withEvents(null)
                            .build();

                    return EventDtoBuilder.getInstance()
                            .withId(e.getId())
                            .withPlace(place)
                            .withActivity(activity)
                            .withStartDate(e.getStartDate())
                            .withEndDate(e.getEndDate())
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
        event.setStartDate(newEvent.getStartDate());
        event.setEndDate(newEvent.getEndDate());

        Event saved = eventRepository.save(event);
        if(saved != null) {
            return saved;
        }
        else {
            throw new ServerException("Event has not been saved due to server problems");
        }
    }

    public EventDto getUpcomingEvent() {
        Date date = new Date();
        Schedule upcomingSchedule = scheduleRepository.findTopByEvent_StartDateGreaterThanEqualOrderByEvent_StartDateAsc(date);
        Event e = this.eventRepository.findTopByStartDateGreaterThanEqualAndIdNotOrderByStartDateAsc(date, upcomingSchedule.getEvent().getId());
        if(e != null) {
            ActivityDto activity = ActivityDtoBuilder.getInstance()
                    .withId(e.getActivity().getId())
                    .withName(e.getActivity().getName())
                    .withValue(e.getActivity().getValue())
                    .withEvents(null)
                    .build();
            PlaceDto place = PlaceDtoBuilder.getInstance()
                    .withId(e.getPlace().getId())
                    .withName(e.getPlace().getName())
                    .withCoordinates(e.getPlace().getLongitude(), e.getPlace().getLatitude(), e.getPlace().getAltitude())
                    .withEvents(null)
                    .build();

            return EventDtoBuilder.getInstance()
                    .withId(e.getId())
                    .withPlace(place)
                    .withActivity(activity)
                    .withStartDate(e.getStartDate())
                    .withEndDate(e.getEndDate())
                    .build();
        }
        else {
            return null;
        }

    }
}
