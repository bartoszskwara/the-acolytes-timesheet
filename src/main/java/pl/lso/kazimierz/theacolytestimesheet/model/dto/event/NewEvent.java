package pl.lso.kazimierz.theacolytestimesheet.model.dto.event;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class NewEvent {

    @NotNull(message = "Place ID cannot be null")
    private Long placeId;

    @NotNull(message = "Activity ID cannot be null")
    private Long activityId;

    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
