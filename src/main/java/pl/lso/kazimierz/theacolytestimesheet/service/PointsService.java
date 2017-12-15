package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.NewPoints;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.repository.ActivityRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.PointsRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

import java.util.Date;

@Service
public class PointsService {

    private UserRepository userRepository;
    private PointsRepository pointsRepository;
    private ActivityRepository activityRepository;

    @Autowired
    public PointsService(UserRepository userRepository,
                         PointsRepository pointsRepository,
                         ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.pointsRepository = pointsRepository;
        this.activityRepository = activityRepository;
    }

    public Points addNewPoints(NewPoints newPoints) {
        if(newPoints == null) {
            throw new NotFoundException("Points data not found");
        }

        User user = userRepository.findOne(newPoints.getUserId());
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        Activity activity = activityRepository.findOne(newPoints.getActivityId());
        if(activity == null) {
            throw new NotFoundException("Activity not found");
        }

        Points points = new Points();
        points.setUser(user);
        points.setActivity(activity);
        points.setReceivedDate(new Date());

        Points saved = pointsRepository.save(points);
        if(saved != null) {
            return saved;
        }
        else {
            throw new ServerException("Points has not been saved due to server problems");
        }
    }
}
