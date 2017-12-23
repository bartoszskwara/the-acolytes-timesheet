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
    private Date startDate;
    private Date endDate;

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
    public EventDtoBuilder withPlace(PlaceDto place) {
        this.place = place;
        return this;
    }
    public EventDtoBuilder withActivity(Activity activity) {
        this.activity = ActivityDtoBuilder.buildFromEntity(activity);
        return this;
    }
    public EventDtoBuilder withActivity(ActivityDto activity) {
        this.activity = activity;
        return this;
    }
    public EventDtoBuilder withStartDate(Date date) {
        this.startDate = date;
        return this;
    }
    public EventDtoBuilder withEndDate(Date date) {
        this.endDate = date;
        return this;
    }
    public static EventDto buildFromEntity(Event event) {
        return EventDtoBuilder.getInstance()
                .withId(event.getId())
                .withActivity(event.getActivity())
                .withPlace(event.getPlace())
                .withStartDate(event.getStartDate())
                .withEndDate(event.getEndDate())
                .build();
    }

    public EventDto build() {
        EventDto eventDto = new EventDto(
                this.id,
                this.place,
                this.activity,
                this.startDate,
                this.endDate);
        return eventDto;
    }
}
