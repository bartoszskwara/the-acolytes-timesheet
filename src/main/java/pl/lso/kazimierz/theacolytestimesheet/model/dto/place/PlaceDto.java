package pl.lso.kazimierz.theacolytestimesheet.model.dto.place;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceDto {

    private Long id;
    private String name;
    private Double longitude;
    private Double latitude;
    private Double altitude;
    private Set<EventDto> events;

    public PlaceDto(Long id, String name, Double longitude, Double latitude, Double altitude, Set<EventDto> events) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public Set<EventDto> getEvents() {
        return events;
    }

}
