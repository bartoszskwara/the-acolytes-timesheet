package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.NewActivity;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.NewPoints;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.repository.ActivityRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.PointsRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

import java.util.Date;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity addNewActivity(NewActivity newActivity) {
        if(newActivity == null) {
            throw new NotFoundException("Activity data not found");
        }

        Activity activity = new Activity();
        activity.setName(newActivity.getName());
        activity.setValue(newActivity.getValue());

        Activity saved = activityRepository.save(activity);
        if(saved != null) {
            return saved;
        }
        else {
            throw new ServerException("Activity has not been saved due to server problems");
        }
    }
}
