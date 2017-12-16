package pl.lso.kazimierz.theacolytestimesheet.model.dto.points;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;

import java.util.Date;

public class PointsDto {

    private Long id;
    private UserDto user;
    private ActivityDto activity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss Z")
    private Date receivedDate;

    public PointsDto(Long id, UserDto user, ActivityDto activity, Date receivedDate) {
        this.id = id;
        this.user = user;
        this.activity = activity;
        this.receivedDate = receivedDate;
    }

    public Long getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }
}
