package pl.lso.kazimierz.theacolytestimesheet.model.builder.dto;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.role.RoleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.UserDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Role;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;

import java.util.HashSet;
import java.util.Set;

public class RoleDtoBuilder {

    private Long id;
    private String name;
    private Set<UserDto> users;

    public RoleDtoBuilder() {
        this.users = new HashSet<>();
    }

    public static RoleDtoBuilder getInstance() {
        return new RoleDtoBuilder();
    }

    public RoleDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    public RoleDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }
    public RoleDtoBuilder withUser(User user) {
        this.users.add(UserDtoBuilder.getInstance().buildFromEntity(user));
        return this;
    }
    public RoleDtoBuilder withUsers(Set<User> users) {
        HashSet<UserDto> userSet = new HashSet<>();
        users.forEach(u -> userSet.add(UserDtoBuilder.getInstance().buildFromEntity(u)));
        this.users = userSet;
        return this;
    }

    public static RoleDto buildFromEntity(Role role) {
        return RoleDtoBuilder.getInstance()
                .withId(role.getId())
                .withName(role.getName())
                .withUsers(role.getUsers())
                .build();
    }

    public RoleDto build() {
        RoleDto roleDto = new RoleDto(
                this.id,
                this.name,
                this.users);
        return roleDto;
    }
}
