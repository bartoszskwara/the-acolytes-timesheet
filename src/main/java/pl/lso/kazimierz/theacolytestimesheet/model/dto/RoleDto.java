package pl.lso.kazimierz.theacolytestimesheet.model.dto;

import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Role;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Schedule;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;

import java.util.Set;

public class RoleDto {

    private Long id;
    private String name;
    private Set<UserDto> users;

    public RoleDto(Long id, String name, Set<UserDto> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<UserDto> getUsers() {
        return users;
    }
}
