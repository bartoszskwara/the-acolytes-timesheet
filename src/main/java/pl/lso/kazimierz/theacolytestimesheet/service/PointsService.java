package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.ActivityDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.PointsDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.UserDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.activity.ActivityDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.NewPoints;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.PointsDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Activity;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.repository.ActivityRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.PointsRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Points> getPointsByUserId(Long userId) {
        if(userId == null) {
            throw new NotFoundException("User not found");
        }
        User user = userRepository.findOne(userId);
        if(user == null) {
            throw new NotFoundException("User not found");
        }

        List<Points> points = pointsRepository.findByUser_Id(userId);
        return points.stream().sorted(Comparator.comparing(Points::getReceivedDate)).collect(Collectors.toList());

    }

    public int getTotalPointsFromList(List<Points> points) {
        int total = 0;
        for(Points p : points) {
            total += p.getActivity().getValue();
        }
        return total;
    }

    public List<PointsDto> mapPointsListToPointsDtoList(List<Points> points) {
        return points.stream()
                .map(p -> {
                    ActivityDto activity = ActivityDtoBuilder.getInstance()
                            .withId(p.getActivity().getId())
                            .withName(p.getActivity().getName())
                            .withValue(p.getActivity().getValue())
                            .build();
                    UserDto user = UserDtoBuilder.getInstance()
                            .withId(p.getUser().getId())
                            .withName(p.getUser().getName())
                            .build();

                    return PointsDtoBuilder.getInstance()
                            .withId(p.getId())
                            .withActivity(activity)
                            .withUser(user)
                            .withReceivedDate(p.getReceivedDate())
                            .build();
                })
                .collect(Collectors.toList());
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
