package com.example.city.distance.validator;

import com.example.city.distance.dto.RoadDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

@Component
public class RoadDTOValidator extends BaseValidator<RoadDTO> {

    protected void validate(RoadDTO target, Errors errors) {
        checkDistanceNull(target, errors);
        checkFromEmpty(target, errors);
        checkToEmpty(target, errors);
        checkNamesNotEquals(target, errors);
        checkDistancePositive(target, errors);
    }

    private void checkDistanceNull(RoadDTO target, Errors errors) {
        if(target.getDistance()==null){
            errors.rejectValue("distance", "distance.null", "'distance' can not be null");
        }
    }

    private void checkFromEmpty(RoadDTO target, Errors errors) {
        if(StringUtils.isEmpty(target.getFrom())){
            errors.rejectValue("from", "from.empty", "'from' can not be empty");
        }
    }


    private void checkToEmpty(RoadDTO target, Errors errors) {
        if(StringUtils.isEmpty(target.getTo())){
            errors.rejectValue("to", "to.empty", "'to' can not be empty");
        }
    }


    private void checkDistancePositive(RoadDTO target, Errors errors) {
        if(target.getDistance()==null){
            return;
        }
        if (target.getDistance() <= 0) {
            errors.rejectValue("distance", "distance.not.positive", "'distance' should be positive");
        }
    }

    private void checkNamesNotEquals(RoadDTO target, Errors errors) {
        if(StringUtils.isEmpty(target.getFrom()) || StringUtils.isEmpty(target.getTo())){
            return;
        }
        if (target.getFrom().equals(target.getTo())) {
            errors.reject("cities.with.the.same.names", "can not add distance between the same city");
        }
    }
}
