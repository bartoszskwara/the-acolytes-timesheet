package pl.lso.kazimierz.theacolytestimesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.EventDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.NewEvent;
import pl.lso.kazimierz.theacolytestimesheet.service.EventService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping({"", "/"})
    public ResponseEntity addEvent(@RequestBody @Validated NewEvent newEvent) {
        EventDto eventDto = EventDtoBuilder.buildFromEntity(eventService.addNewEvent(newEvent));

        Map<String, Object> response = new HashMap<>();
        response.put("response", "Event have been created");
        response.put("event", eventDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

}
