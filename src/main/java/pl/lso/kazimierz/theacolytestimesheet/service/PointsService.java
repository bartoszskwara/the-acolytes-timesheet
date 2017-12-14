package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.ForbiddenException;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.UserDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.NewPoints;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.NewUser;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.repository.PointsRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

import java.util.Date;

@Service
public class PointsService {

    private UserRepository userRepository;
    private PointsRepository pointsRepository;

    @Autowired
    public PointsService(UserRepository userRepository, PointsRepository pointsRepository) {
        this.userRepository = userRepository;
        this.pointsRepository = pointsRepository;
    }

    public Points addNewPoints(NewPoints newPoints) {
        if(newPoints == null) {
            throw new NotFoundException("Points data not found");
        }

        Points points = new Points();
        points.setUserId(newPoints.getUserId());
        points.setActivityId(newPoints.getActivityId());
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
