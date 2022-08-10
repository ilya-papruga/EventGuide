package by.it_academy.jd2.user.validation;

import by.it_academy.jd2.user.validation.api.Password;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password password) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if(StringUtils.isEmpty(password)) {
            context.buildConstraintViolationWithTemplate( "не должно равняться null" )
                    .addConstraintViolation().disableDefaultConstraintViolation();;
            return false;
        }
        if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$")) {
            return true;
        }
        context.buildConstraintViolationWithTemplate( "пароль должен состоять из 8-20" +
                        " символов и содержать как минимум одну заглавную букву, " +
                        "одну строчную букву и одну цифру" )
                .addConstraintViolation().disableDefaultConstraintViolation();
        return false;
    }
}

