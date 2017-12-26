package pl.lso.kazimierz.theacolytestimesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.ActivityDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.EventDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.PlaceDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.NewActivity;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.location.Location;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.place.PlaceDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.service.ActivityService;
import pl.lso.kazimierz.theacolytestimesheet.service.LocationService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/location")
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping({"", "/"})
    public ResponseEntity addActivity(@RequestBody @Validated Location location) {

        System.out.println(location.getTimestamp());

        Set<Event> events = locationService.getAnEventNearbyIfExists(location);
        Set<EventDto> eventDtos = new HashSet<>();
        for(Event e : events) {
            PlaceDto placeDto = PlaceDtoBuilder.getInstance()
                    .withId(e.getPlace().getId())
                    .withName(e.getPlace().getName())
                    .withCoordinates(e.getPlace().getLongitude(), e.getPlace().getLatitude(), e.getPlace().getAltitude())
                    .build();
            ActivityDto activityDto = ActivityDtoBuilder.getInstance()
                    .withId(e.getActivity().getId())
                    .withName(e.getActivity().getName())
                    .withValue(e.getActivity().getValue())
                    .build();
            eventDtos.add(EventDtoBuilder.getInstance()
                    .withId(e.getId())
                    .withActivity(activityDto)
                    .withPlace(placeDto)
                    .withStartDate(e.getStartDate())
                    .withEndDate(e.getEndDate())
                    .build());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("events", eventDtos);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
