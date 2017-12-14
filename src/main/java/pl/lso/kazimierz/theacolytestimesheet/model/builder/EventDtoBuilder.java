package pl.lso.kazimierz.theacolytestimesheet.model.builder;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.RoleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Role;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EventDtoBuilder {

    private Long id;
    private Long placeId;
    private Long activityId;
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
    public EventDtoBuilder withPlaceId(Long placeId) {
        this.placeId = placeId;
        return this;
    }
    public EventDtoBuilder withActivityId(Long activityId) {
        this.activityId = activityId;
        return this;
    }
    public EventDtoBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public static EventDto buildFromEntity(Event event) {
        return EventDtoBuilder.getInstance()
                .withId(event.getId())
                .withActivityId(event.getActivityId())
                .withPlaceId(event.getPlaceId())
                .withDate(event.getDate())
                .build();
    }

    public EventDto build() {
        EventDto eventDto = new EventDto(
                this.id,
                this.placeId,
                this.activityId,
                this.date);
        return eventDto;
    }
}
