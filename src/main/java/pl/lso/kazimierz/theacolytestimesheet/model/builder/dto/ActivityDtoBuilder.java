package pl.lso.kazimierz.theacolytestimesheet.model.builder.dto;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;

import java.util.HashSet;
import java.util.Set;

public class ActivityDtoBuilder {

    private Long id;
    private String name;
    private Integer value;
    private Set<EventDto> events;

    public ActivityDtoBuilder() {
        this.events = new HashSet<>();
    }

    public static ActivityDtoBuilder getInstance() {
        return new ActivityDtoBuilder();
    }

    public ActivityDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    public ActivityDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }
    public ActivityDtoBuilder withValue(Integer value) {
        this.value = value;
        return this;
    }
    public ActivityDtoBuilder withEvent(Event event) {
        this.events.add(EventDtoBuilder.getInstance().buildFromEntity(event));
        return this;
    }
    public ActivityDtoBuilder withEvents(Set<Event> events) {
        if(events == null) {
            this.events = null;
            return this;
        }
        HashSet<EventDto> eventSet = new HashSet<>();
        events.forEach(e -> eventSet.add(EventDtoBuilder.getInstance().buildFromEntity(e)));
        this.events = eventSet;
        return this;
    }

    public static ActivityDto buildFromEntity(Activity activity) {
        return ActivityDtoBuilder.getInstance()
                .withId(activity.getId())
                .withName(activity.getName())
                .withValue(activity.getValue())
                .withEvents(activity.getEvents())
                .build();
    }

    public ActivityDto build() {
        ActivityDto activityDto = new ActivityDto(
                this.id,
                this.name,
                this.value,
                this.events);
        return activityDto;
    }
}
