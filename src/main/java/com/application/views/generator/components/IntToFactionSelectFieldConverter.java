package com.application.views.generator.components;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import org.javatuples.Triplet;

public class IntToFactionSelectFieldConverter implements Converter<Triplet<Boolean, String, Integer>, Integer> {

    Triplet<Boolean, String, Integer> fieldValue = new Triplet<Boolean, String, Integer>( false, "", 0 );

    public IntToFactionSelectFieldConverter( FactionSelectField field ) {
        this.fieldValue = field.getValue();
    }

    @Override
    public Result<Integer> convertToModel(Triplet<Boolean, String, Integer> value, ValueContext context) {
        return Result.ok( value.getValue2() );
    }

    @Override
    public Triplet<Boolean, String, Integer> convertToPresentation(Integer value, ValueContext context) {
        this.fieldValue.setAt2( value );
        return this.fieldValue;
    }
    
}
