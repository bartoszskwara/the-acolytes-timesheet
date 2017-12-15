package pl.lso.kazimierz.theacolytestimesheet.model.builder.dto;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.PointsDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;

import java.util.Date;

public class PointsDtoBuilder {

    private Long id;
    private UserDto user;
    private ActivityDto activity;
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
    public PointsDtoBuilder withUser(User user) {
        this.user = UserDtoBuilder.buildFromEntity(user);
        return this;
    }
    public PointsDtoBuilder withActivity(Activity activity) {
        this.activity = ActivityDtoBuilder.buildFromEntity(activity);
        return this;
    }
    public PointsDtoBuilder withReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public static PointsDto buildFromEntity(Points points) {
        return PointsDtoBuilder.getInstance()
                .withId(points.getId())
                .withUser(points.getUser())
                .withActivity(points.getActivity())
                .withReceivedDate(points.getReceivedDate())
                .build();
    }

    public PointsDto build() {
        PointsDto pointsDto = new PointsDto(
                this.id,
                this.user,
                this.activity,
                this.receivedDate);
        return pointsDto;
    }
}
