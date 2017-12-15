package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.UserScheduleBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.NewSchedule;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.UserSchedule;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Schedule;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.repository.EventRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.ScheduleRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            userScheduleBuilder.withEvent(s.getEvent());
        }
        userScheduleBuilder.withUser(user);

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
}
