package pl.lso.kazimierz.theacolytestimesheet.model.dto;

import java.util.Date;

public class ScheduleDto {

    private Long id;
    private Long userId;
    private Long eventId;

    public ScheduleDto(Long id, Long userId, Long eventId) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEventId() {
        return eventId;
    }
}
