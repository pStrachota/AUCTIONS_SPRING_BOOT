package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators;

import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.MAX_PRICE;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PriceConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceConstraint {
    String message() default "Price must be greater than 0 and less than " + MAX_PRICE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
