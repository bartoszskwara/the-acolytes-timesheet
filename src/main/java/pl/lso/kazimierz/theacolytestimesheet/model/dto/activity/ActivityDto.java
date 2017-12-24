package pl.lso.kazimierz.theacolytestimesheet.model.dto.activity;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public Integer getValue() {
        return value;
    }

    public Set<EventDto> getEvents() {
        return events;
    }

}
