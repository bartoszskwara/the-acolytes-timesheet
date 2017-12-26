package pl.lso.kazimierz.theacolytestimesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.ActivityDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.NewActivity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.service.ActivityService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping({"", "/"})
    public ResponseEntity addActivity(@RequestBody @Validated NewActivity newActivity) {
        Activity activity = activityService.addNewActivity(newActivity);
        ActivityDto activityDto = new ActivityDtoBuilder()
                .withId(activity.getId())
                .withName(activity.getName())
                .withValue(activity.getValue())
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("response", "Activity have been created");
        response.put("activity", activityDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
