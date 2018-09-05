package com.example.city.distance.validator;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.exception.CustomMethodArgumentNotValidException;
import org.junit.Test;

public class RoadDTOValidatorTest {

    private RoadDTOValidator validator = new RoadDTOValidator();

    @Test
    public void validate() {
        validator.validate(getValidDTO());
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validateEmptyTo() {
        RoadDTO dto = getValidDTO().setTo("");
        validator.validate(dto);
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validateEmptyFrom() {
        RoadDTO dto = getValidDTO().setFrom("");
        validator.validate(dto);
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validateNullDistance() {
        RoadDTO dto = getValidDTO().setDistance(null);
        validator.validate(dto);
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validateNegativeDistance() {
        RoadDTO dto = getValidDTO().setDistance(-42.);
        validator.validate(dto);
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validateFromEqualsTo() {
        RoadDTO dto = getValidDTO().setTo("from");
        validator.validate(dto);
    }


    private RoadDTO getValidDTO() {
        return new RoadDTO()
                .setFrom("from")
                .setTo("to")
                .setDistance(42.);
    }
}