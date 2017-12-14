package pl.lso.kazimierz.theacolytestimesheet.model.dto.user;

import pl.lso.kazimierz.theacolytestimesheet.validator.SamePasswords;
import pl.lso.kazimierz.theacolytestimesheet.validator.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SamePasswords
public class NewUser {

    @NotNull(message = "Name cannot be null")
    @Size(min=1, message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Username cannot be null")
    @Size(min=1, message = "Username cannot be blank")
    private String username;

    @NotNull(message = "Email cannot be null")
    @Size(min=5, message = "Email too short")
    @ValidEmail
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min=3, message = "Password should contain at least 3 characters")
    private String password;

    @NotNull(message = "Password confirmation cannot be null")
    @Size(min=3, message = "Password should contain at least 3 characters")
    private String confirmPassword;

    public NewUser() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
