package pl.lso.kazimierz.theacolytestimesheet.validator;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.user.NewUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<SamePasswords, NewUser> {

    @Override
    public void initialize(SamePasswords contactNumber) {
    }

    @Override
    public boolean isValid(NewUser newUser, ConstraintValidatorContext ctx) {

        if(newUser == null) {
            return false;
        }
        if(newUser.getPassword() != null && newUser.getConfirmPassword() != null && newUser.getPassword().equals(newUser.getConfirmPassword())) {
            return true;
        }
        else {
            return false;
        }
    }

}