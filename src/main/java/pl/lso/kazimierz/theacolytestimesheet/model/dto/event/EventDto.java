package pl.lso.kazimierz.theacolytestimesheet.model.dto.event;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.place.PlaceDto;

import java.util.Date;

public class EventDto {
    private Long id;
    private PlaceDto place;
    private ActivityDto activity;
    private Date date;

    public EventDto(Long id, PlaceDto placeDto, ActivityDto activityDto, Date date) {
        this.id = id;
        this.place = place;
        this.activity = activity;
        this.date = date;
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

    public Date getDate() {
        return date;
    }
}
