package pl.lso.kazimierz.theacolytestimesheet.model.dto.event;

import java.util.Date;

public class EventDto {
    private Long id;
    private Long placeId;
    private Long activityId;
    private Date date;

    public EventDto(Long id, Long placeId, Long activityId, Date date) {
        this.id = id;
        this.placeId = placeId;
        this.activityId = activityId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public Date getDate() {
        return date;
    }
}
