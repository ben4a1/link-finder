package by.paramonov.linkcount.validation;

import org.primefaces.validate.bean.ClientConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UrlConstraintValidator.class)
@ClientConstraint(resolvedBy = UrlClientValidationConstraint.class)
@Documented
public @interface Url {
    String message() default "{by.paramonov.link.finder}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
