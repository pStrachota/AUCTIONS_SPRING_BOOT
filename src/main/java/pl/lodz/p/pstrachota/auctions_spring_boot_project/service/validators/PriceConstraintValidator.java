package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators;

import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.MAX_PRICE;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PriceConstraintValidator implements ConstraintValidator<PriceConstraint, BigDecimal> {

    @Override
    public void initialize(PriceConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(BigDecimal bigDecimal,
                           ConstraintValidatorContext constraintValidatorContext) {
        return bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) > 0 && bigDecimal.compareTo(BigDecimal.valueOf(
                MAX_PRICE)) < 0
                && bigDecimal.scale() <= 2;
    }

}
