package pl.lso.kazimierz.theacolytestimesheet.model.builder.dto;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.place.PlaceDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Place;

import java.util.Date;

public class EventDtoBuilder {

    private Long id;
    private PlaceDto place;
    private ActivityDto activity;
    private Date date;

    public EventDtoBuilder() {
    }

    public static EventDtoBuilder getInstance() {
        return new EventDtoBuilder();
    }

    public EventDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    public EventDtoBuilder withPlace(Place place) {
        this.place = PlaceDtoBuilder.buildFromEntity(place);
        return this;
    }
    public EventDtoBuilder withActivity(Activity activity) {
        this.activity = ActivityDtoBuilder.buildFromEntity(activity);
        return this;
    }
    public EventDtoBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public static EventDto buildFromEntity(Event event) {
        return EventDtoBuilder.getInstance()
                .withId(event.getId())
                .withActivity(event.getActivity())
                .withPlace(event.getPlace())
                .withDate(event.getDate())
                .build();
    }

    public EventDto build() {
        EventDto eventDto = new EventDto(
                this.id,
                this.place,
                this.activity,
                this.date);
        return eventDto;
    }
}
