package configurationmodel;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;

public class IntegerMagnitudeValidator implements Validator<Integer> {
    private int min;
    private int max;

    public IntegerMagnitudeValidator( int min, int max ) {
        this.min = min;
        this.max = max;
    }

    @Override
    public ValidationResult apply(Integer value, ValueContext context) {
        if( value > max || value < min ) {
            return ValidationResult.error( String.format("Value outside of acceptable range %s-%s", min, max) );
        }
        return ValidationResult.ok();
    }

}
