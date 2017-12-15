package pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule;

import javax.validation.constraints.NotNull;
import java.util.List;


public class NewSchedule {
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Events list cannot be null")
    private List<Long> events;

    public NewSchedule() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getEvents() {
        return events;
    }

    public void setEvents(List<Long> events) {
        this.events = events;
    }
}
