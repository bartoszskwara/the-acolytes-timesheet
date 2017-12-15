package pl.lso.kazimierz.theacolytestimesheet.model.dto.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private Set<String> roles;

    public AuthUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.roles = user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
