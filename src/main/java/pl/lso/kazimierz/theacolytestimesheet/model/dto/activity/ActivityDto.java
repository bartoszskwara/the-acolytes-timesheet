package pl.lso.kazimierz.theacolytestimesheet.model.dto.activity;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;

import java.util.Set;

public class ActivityDto {

    private Long id;
    private String name;
    private Integer value;
    private Set<EventDto> events;

    public ActivityDto(Long id, String name, Integer value, Set<EventDto> events) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<EventDto> getEvents() {
        return events;
    }
}
