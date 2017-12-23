package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.exception.ForbiddenException;
import pl.lso.kazimierz.theacolytestimesheet.exception.NotFoundException;
import pl.lso.kazimierz.theacolytestimesheet.exception.ServerException;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.UserDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.NewUser;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;
import pl.lso.kazimierz.theacolytestimesheet.repository.EventRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.PointsRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.UserRepository;

@Service
public class NewsService {

    private PointsRepository pointsRepository;
    private EventRepository eventRepository;

    @Autowired
    public NewsService(PointsRepository pointsRepository, EventRepository eventRepository) {
        this.pointsRepository = pointsRepository;
        this.eventRepository = eventRepository;
    }


}
