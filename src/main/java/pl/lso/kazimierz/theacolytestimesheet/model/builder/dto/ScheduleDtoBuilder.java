package pl.lso.kazimierz.theacolytestimesheet.model.builder.dto;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.ScheduleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Schedule;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;

public class ScheduleDtoBuilder {

    private Long id;
    private UserDto user;
    private EventDto event;

    public ScheduleDtoBuilder() {
    }

    public static ScheduleDtoBuilder getInstance() {
        return new ScheduleDtoBuilder();
    }

    public ScheduleDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    public ScheduleDtoBuilder withUser(User user) {
        this.user = UserDtoBuilder.buildFromEntity(user);
        return this;
    }
    public ScheduleDtoBuilder withEvent(Event event) {
        this.event = EventDtoBuilder.buildFromEntity(event);
        return this;
    }

    public static ScheduleDto buildFromEntity(Schedule schedule) {
        return ScheduleDtoBuilder.getInstance()
                .withId(schedule.getId())
                .withUser(schedule.getUser())
                .withEvent(schedule.getEvent())
                .build();
    }

    public ScheduleDto build() {
        ScheduleDto scheduleDto = new ScheduleDto(
                this.id,
                this.user,
                this.event);
        return scheduleDto;
    }
}
