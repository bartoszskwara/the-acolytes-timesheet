package pl.lso.kazimierz.theacolytestimesheet.model.dto.place;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceDto {

    private Long id;
    private String name;
    private String coordinates;
    private Set<EventDto> events;

    public PlaceDto(Long id, String name, String coordinates, Set<EventDto> events) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public Set<EventDto> getEvents() {
        return events;
    }

}
