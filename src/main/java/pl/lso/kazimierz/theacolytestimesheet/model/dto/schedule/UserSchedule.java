package pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;

import java.util.Set;

public class UserSchedule {
    private UserDto user;
    private Set<EventDto> events;

    public UserSchedule(UserDto user, Set<EventDto> events) {
        this.user = user;
        this.events = events;
    }
}
