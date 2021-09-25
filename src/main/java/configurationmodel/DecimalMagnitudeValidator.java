package configurationmodel;

import java.math.BigDecimal;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class DecimalMagnitudeValidator implements Validator<BigDecimal> {
    private BigDecimal min;
    private BigDecimal max;

    public DecimalMagnitudeValidator( String min, String max) {
        this.min = new BigDecimal(min);
        this.max = new BigDecimal(max);
    }

    @Override
    public ValidationResult apply(BigDecimal value, ValueContext context) {
        if( value.compareTo(min) < 0 || value.compareTo(max) > 0) {
            return ValidationResult.error(String.format("Value is outisde of acceptable range %s-%s", min.toString(), max.toString()));
        }
        return ValidationResult.ok();
    }
}
