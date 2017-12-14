package pl.lso.kazimierz.theacolytestimesheet.model.dto.points;

import java.util.Date;

public class PointsDto {

    private Long id;
    private Long userId;
    private Long activityId;
    private Date receivedDate;

    public PointsDto(Long id, Long userId, Long activityId, Date receivedDate) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.receivedDate = receivedDate;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }
}
