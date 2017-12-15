package pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;

public class ScheduleDto {

    private Long id;
    private UserDto user;
    private EventDto event;

    public ScheduleDto(Long id, UserDto user, EventDto event) {
        this.id = id;
        this.user = user;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public EventDto getEvent() {
        return event;
    }
}
