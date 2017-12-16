package pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;

import java.util.Set;

public class UserSchedule {
    private UserDto user;
    private Set<EventDto> schedule;

    public UserSchedule(UserDto user, Set<EventDto> schedule) {
        this.user = user;
        this.schedule = schedule;
    }

    public UserDto getUser() {
        return user;
    }

    public Set<EventDto> getSchedule() {
        return schedule;
    }
}
