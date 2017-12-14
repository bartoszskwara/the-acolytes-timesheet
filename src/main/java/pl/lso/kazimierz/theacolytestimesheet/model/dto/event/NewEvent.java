package pl.lso.kazimierz.theacolytestimesheet.model.dto.event;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class NewEvent {

    @NotNull(message = "Place ID cannot be null")
    private Long placeId;

    @NotNull(message = "Activity ID cannot be null")
    private Long activityId;

    @NotNull(message = "Date cannot be null")
    private Date date;

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
