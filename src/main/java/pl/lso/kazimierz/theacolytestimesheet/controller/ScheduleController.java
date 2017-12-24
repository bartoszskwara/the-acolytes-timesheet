package pl.lso.kazimierz.theacolytestimesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.ScheduleDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.NewSchedule;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.schedule.ScheduleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Schedule;
import pl.lso.kazimierz.theacolytestimesheet.service.ScheduleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity getScheduleByUserId(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(scheduleService.getScheduleByUserId(userId), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity addSchedule(@RequestBody @Validated NewSchedule newSchedule) {

        List<Schedule> schedule = scheduleService.addNewSchedule(newSchedule);
        List<ScheduleDto> scheduleDto = new ArrayList<>();
        for(Schedule s : schedule) {
            scheduleDto.add(ScheduleDtoBuilder.getInstance().withEvent(s.getEvent()).build());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("response", "Schedule have been saved");
        response.put("schedule", scheduleDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/upcoming/{userId}")
    public ResponseEntity getUpcomingEvent(@PathVariable("userId") Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("upcomingSchedule", this.scheduleService.getUpcomingSchedule(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
