package rs.ac.uns.ftn.nistagram.constraints;

import rs.ac.uns.ftn.nistagram.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Provided password is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
