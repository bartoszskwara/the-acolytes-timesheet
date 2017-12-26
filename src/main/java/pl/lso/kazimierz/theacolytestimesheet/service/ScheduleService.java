package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.*;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.event.EventDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.place.PlaceDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.NewSchedule;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.ScheduleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.UserSchedule;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Schedule;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.repository.EventRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.ScheduleRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;
    private EventRepository eventRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository,
                           UserRepository userRepository,
                           EventRepository eventRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public UserSchedule getScheduleByUserId(Long userId) {
        if(userId == null) {
            throw new NotFoundException("User not found");
        }

        User user = userRepository.findOne(userId);
        if(user == null) {
            throw new NotFoundException("User not found");
        }

        Set<Schedule> schedules = scheduleRepository.findByUser_Id(userId);

        UserScheduleBuilder userScheduleBuilder = UserScheduleBuilder.getInstance();
        for(Schedule s : schedules) {
            ActivityDto activity = ActivityDtoBuilder.getInstance()
                    .withId(s.getEvent().getActivity().getId())
                    .withName(s.getEvent().getActivity().getName())
                    .withValue(s.getEvent().getActivity().getValue())
                    .build();
            PlaceDto place = PlaceDtoBuilder.getInstance()
                    .withId(s.getEvent().getPlace().getId())
                    .withName(s.getEvent().getPlace().getName())
                    .withCoordinates(s.getEvent().getPlace().getLongitude(), s.getEvent().getPlace().getLatitude(), s.getEvent().getPlace().getAltitude())
                    .build();
            EventDto event = EventDtoBuilder.getInstance()
                    .withId(s.getEvent().getId())
                    .withActivity(activity)
                    .withPlace(place)
                    .withStartDate(s.getEvent().getStartDate())
                    .withEndDate(s.getEvent().getEndDate())
                    .build();
            userScheduleBuilder.withEvent(event);
        }
        UserDto userDto = UserDtoBuilder.getInstance()
                .withId(user.getId())
                .withName(user.getName())
                .build();
        userScheduleBuilder.withUser(userDto);

        return userScheduleBuilder.build();
    }

    @Transactional
    public List<Schedule> addNewSchedule(NewSchedule newSchedule) {
        if(newSchedule == null) {
            throw new NotFoundException("Schedule data not found");
        }
        User user = userRepository.findOne(newSchedule.getUserId());
        if(user == null) {
            throw new NotFoundException("User not found");
        }

        List<Schedule> result = new ArrayList<>();
        for(Long eventId : newSchedule.getEvents()) {
            Event event = eventRepository.findOne(eventId);
            if(event == null) {
                throw new NotFoundException("Event not found");
            }
            Schedule scheduleItem = new Schedule();
            scheduleItem.setUser(user);
            scheduleItem.setEvent(event);

            Schedule saved = scheduleRepository.save(scheduleItem);

            if(saved != null) {
                result.add(saved);
            }
            else {
                throw new ServerException("Schedule has not been saved due to server problems");
            }
        }

        return result;
    }

    public ScheduleDto getUpcomingSchedule(Long userId) {
        if(userId == null) {
            throw new NotFoundException("User ID not found");
        }
        User user = userRepository.findOne(userId);
        if(user == null) {
            throw new NotFoundException("User not found");
        }

        Schedule schedule = scheduleRepository.findTopByEvent_StartDateGreaterThanEqualOrderByEvent_StartDateAsc(new Date());
        if(schedule != null) {
            ActivityDto activity = ActivityDtoBuilder.getInstance()
                    .withId(schedule.getEvent().getActivity().getId())
                    .withName(schedule.getEvent().getActivity().getName())
                    .withValue(schedule.getEvent().getActivity().getValue())
                    .withEvents(null)
                    .build();
            PlaceDto place = PlaceDtoBuilder.getInstance()
                    .withId(schedule.getEvent().getPlace().getId())
                    .withName(schedule.getEvent().getPlace().getName())
                    .withCoordinates(schedule.getEvent().getPlace().getLongitude(), schedule.getEvent().getPlace().getLatitude(), schedule.getEvent().getPlace().getAltitude())
                    .withEvents(null)
                    .build();
            EventDto event = EventDtoBuilder.getInstance()
                    .withId(schedule.getEvent().getId())
                    .withStartDate(schedule.getEvent().getStartDate())
                    .withEndDate(schedule.getEvent().getEndDate())
                    .withActivity(activity)
                    .withPlace(place)
                    .build();
            UserDto userDto = UserDtoBuilder.getInstance()
                    .withId(schedule.getUser().getId())
                    .withName(schedule.getUser().getName())
                    .withPoints((Set<Points>) null)
                    .withRoles(null)
                    .withSchedules(null)
                    .build();

            return ScheduleDtoBuilder.getInstance()
                    .withId(schedule.getId())
                    .withUser(userDto)
                    .withEvent(event)
                    .build();
        }
        else {
            return null;
        }
    }
}
