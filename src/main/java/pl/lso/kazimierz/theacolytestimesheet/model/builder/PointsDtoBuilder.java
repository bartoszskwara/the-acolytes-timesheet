package pl.lso.kazimierz.theacolytestimesheet.model.builder;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.PointsDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.RoleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Role;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PointsDtoBuilder {

    private Long id;
    private Long userId;
    private Long activityId;
    private Date receivedDate;

    public PointsDtoBuilder() {
    }

    public static PointsDtoBuilder getInstance() {
        return new PointsDtoBuilder();
    }

    public PointsDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    public PointsDtoBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public PointsDtoBuilder withActivityId(Long activityId) {
        this.activityId = activityId;
        return this;
    }
    public PointsDtoBuilder withReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public static PointsDto buildFromEntity(Points points) {
        return PointsDtoBuilder.getInstance()
                .withId(points.getId())
                .withUserId(points.getUserId())
                .withActivityId(points.getActivityId())
                .withReceivedDate(points.getReceivedDate())
                .build();
    }

    public PointsDto build() {
        PointsDto pointsDto = new PointsDto(
                this.id,
                this.userId,
                this.activityId,
                this.receivedDate);
        return pointsDto;
    }
}
