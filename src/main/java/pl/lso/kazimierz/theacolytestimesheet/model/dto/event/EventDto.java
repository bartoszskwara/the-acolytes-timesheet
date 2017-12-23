package pl.lso.kazimierz.theacolytestimesheet.model.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.place.PlaceDto;

import java.util.Date;

public class EventDto {
    private Long id;
    private PlaceDto place;
    private ActivityDto activity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss Z")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss Z")
    private Date endDate;

    public EventDto(Long id, PlaceDto place, ActivityDto activity, Date startDate, Date endDate) {
        this.id = id;
        this.place = place;
        this.activity = activity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
