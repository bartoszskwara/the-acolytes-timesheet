package pl.lso.kazimierz.theacolytestimesheet.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.ScheduleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.PointsDto;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String name;
    private String username;
    private String email;
    private boolean active;
    private String avatar;
    private Set<String> roles;
    private Set<PointsDto> points;
    private Set<ScheduleDto> schedules;

    public UserDto(Long id, String name, String username, String email, boolean active,
                   String avatar, Set<String> roles, Set<PointsDto> points, Set<ScheduleDto> schedules) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.active = active;
        this.avatar = avatar;
        this.roles = roles;
        this.points = points;
        this.schedules = schedules;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public String getAvatar() {
        return avatar;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public Set<PointsDto> getPoints() {
        return points;
    }

    public Set<ScheduleDto> getSchedules() {
        return schedules;
    }
}
