package com.example.city.distance.validator;

import com.example.city.distance.exception.CustomMethodArgumentNotValidException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public abstract class BaseValidator<T> {

    public void validate(T target) {
        Errors errors = new BeanPropertyBindingResult(target, target.getClass().getName());
        validate(target, errors);
        if(errors.hasErrors()){
            throw new CustomMethodArgumentNotValidException(errors);
        }
    }

    protected abstract void validate(T target, Errors errors);
}
