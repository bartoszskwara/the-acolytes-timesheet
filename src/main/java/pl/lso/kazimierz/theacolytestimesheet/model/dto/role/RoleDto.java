package pl.lso.kazimierz.theacolytestimesheet.model.dto.role;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;

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
