package pl.lso.kazimierz.theacolytestimesheet.model.builder;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.ScheduleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.PointsDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Role;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Schedule;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDtoBuilder {

    private Long id;
    private String name;
    private String username;
    private String email;
    private boolean active;
    private String avatar;
    private Set<String> roles;
    private Set<PointsDto> points;
    private Set<ScheduleDto> schedules;

    public UserDtoBuilder() {
        this.roles = new HashSet<>();
        this.points = new HashSet<>();
        this.schedules = new HashSet<>();
    }

    public static UserDtoBuilder getInstance() {
        return new UserDtoBuilder();
    }

    public UserDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    public UserDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserDtoBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDtoBuilder setActive(boolean active) {
        this.active = active;
        return this;
    }

    public UserDtoBuilder withAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public UserDtoBuilder withRole(String role) {
        this.roles.add(role);
        return this;
    }

    public UserDtoBuilder withRoles(Set<Role> roles) {
        this.roles = roles.stream().map(role -> role.getName()).collect(Collectors.toSet());
        return this;
    }

    public UserDtoBuilder withPoints(Points points) {
        this.points.add(PointsDtoBuilder.getInstance().buildFromEntity(points));
        return this;
    }

    public UserDtoBuilder withPoints(Set<Points> points) {
        HashSet<PointsDto> pointsSet = new HashSet<>();
        points.forEach(p -> {pointsSet.add(PointsDtoBuilder.getInstance().buildFromEntity(p));});
        this.points = pointsSet;
        return this;
    }

    public UserDtoBuilder withSchedule(Schedule schedule) {
        this.schedules.add(ScheduleDtoBuilder.getInstance().buildFromEntity(schedule));
        return this;
    }

    public UserDtoBuilder withSchedules(Set<Schedule> schedules) {
        HashSet<ScheduleDto> scheduleSet = new HashSet<>();
        schedules.forEach(s -> {scheduleSet.add(ScheduleDtoBuilder.getInstance().buildFromEntity(s));});
        this.schedules = scheduleSet;
        return this;
    }

    public static UserDto buildFromEntity(User user) {
        return UserDtoBuilder.getInstance()
                .withId(user.getId())
                .withName(user.getName())
                .withUsername(user.getUsername())
                .withEmail(user.getEmail())
                .withAvatar(user.getAvatar())
                .withPoints(user.getPoints())
                .withRoles(user.getRoles())
                .withSchedules(user.getSchedules())
                .setActive(user.isActive())
                .build();
    }

    public UserDto build() {
        UserDto userDto = new UserDto(
                this.id,
                this.name,
                this.username,
                this.email,
                this.active,
                this.avatar,
                this.roles,
                this.points,
                this.schedules);
        return userDto;
    }

}
