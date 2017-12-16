package pl.lso.kazimierz.theacolytestimesheet.model.builder.dto;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.UserSchedule;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;

import java.util.HashSet;
import java.util.Set;

public class UserScheduleBuilder {

    private UserDto user;
    private Set<EventDto> events;

    public UserScheduleBuilder() {
        this.events = new HashSet<>();
    }

    public static UserScheduleBuilder getInstance() {
        return new UserScheduleBuilder();
    }

    public UserScheduleBuilder withUser(User user) {
        this.user = UserDtoBuilder.buildFromEntity(user);
        return this;
    }
    public UserScheduleBuilder withUser(UserDto user) {
        this.user = user;
        return this;
    }
    public UserScheduleBuilder withEvent(Event event) {
        this.events.add(EventDtoBuilder.buildFromEntity(event));
        return this;
    }
    public UserScheduleBuilder withEvent(EventDto event) {
        this.events.add(event);
        return this;
    }
    public UserScheduleBuilder withEvents(Set<Event> events) {
        HashSet<EventDto> eventSet = new HashSet<>();
        events.forEach(e -> eventSet.add(EventDtoBuilder.getInstance().buildFromEntity(e)));
        this.events = eventSet;
        return this;
    }

    public UserSchedule build() {
        UserSchedule userSchedule = new UserSchedule(
                this.user,
                this.events);
        return userSchedule;
    }
}
