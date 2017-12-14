package pl.lso.kazimierz.theacolytestimesheet.model.dto.points;

import pl.lso.kazimierz.theacolytestimesheet.validator.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class NewPoints {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Activity ID cannot be null")
    private Long activityId;

    public NewPoints() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
