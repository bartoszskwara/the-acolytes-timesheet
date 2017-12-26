package pl.lso.kazimierz.theacolytestimesheet.model.builder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.place.PlaceDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Place;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaceDtoBuilder {

    private Long id;
    private String name;
    private Double longitude;
    private Double latitude;
    private Double altitude;
    private Set<EventDto> events;

    public PlaceDtoBuilder() {
        this.events = new HashSet<>();
    }

    public static PlaceDtoBuilder getInstance() {
        return new PlaceDtoBuilder();
    }

    public PlaceDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    public PlaceDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }
    public PlaceDtoBuilder withCoordinates(Double longitude, Double latitude, Double altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        return this;
    }
    public PlaceDtoBuilder withEvent(Event event) {
        this.events.add(EventDtoBuilder.getInstance().buildFromEntity(event));
        return this;
    }
    public PlaceDtoBuilder withEvents(Set<Event> events) {
        if(events == null) {
            this.events = null;
            return this;
        }
        HashSet<EventDto> eventSet = new HashSet<>();
        events.forEach(e -> eventSet.add(EventDtoBuilder.getInstance().buildFromEntity(e)));
        this.events = eventSet;
        return this;
    }

    public static PlaceDto buildFromEntity(Place place) {
        return PlaceDtoBuilder.getInstance()
                .withId(place.getId())
                .withName(place.getName())
                .withCoordinates(place.getLongitude(), place.getLatitude(), place.getAltitude())
                .withEvents(place.getEvents())
                .build();
    }

    public PlaceDto build() {
        PlaceDto placeDto = new PlaceDto(
                this.id,
                this.name,
                this.longitude,
                this.latitude,
                this.altitude,
                this.events);
        return placeDto;
    }
}
