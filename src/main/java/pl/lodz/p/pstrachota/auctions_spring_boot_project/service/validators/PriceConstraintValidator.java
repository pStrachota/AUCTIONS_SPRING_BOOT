package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators;

import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.maxPrice;

import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceConstraintValidator implements ConstraintValidator<PriceConstraint, BigDecimal> {

    @Override
    public void initialize(PriceConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(BigDecimal bigDecimal,
                           ConstraintValidatorContext constraintValidatorContext) {
        return bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) > 0 && bigDecimal.compareTo(BigDecimal.valueOf(maxPrice)) < 0
                && bigDecimal.scale() <= 2;
    }

}
