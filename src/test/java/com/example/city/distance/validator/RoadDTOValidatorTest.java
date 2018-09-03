package com.example.city.distance.validator;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.exception.CustomMethodArgumentNotValidException;
import org.junit.Test;

public class RoadDTOValidatorTest {

    private RoadDTOValidator validator = new RoadDTOValidator();

    @Test
    public void validate_valid() throws Exception {
        validator.validate(getValidDTO());
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validate_empty_to() throws Exception {
        RoadDTO dto = getValidDTO().setTo("");
        validator.validate(dto);
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validate_empty_from() throws Exception {
        RoadDTO dto = getValidDTO().setFrom("");
        validator.validate(dto);
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validate_null_distance() throws Exception {
        RoadDTO dto = getValidDTO().setDistance(null);
        validator.validate(dto);
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validate_negative_distance() throws Exception {
        RoadDTO dto = getValidDTO().setDistance(-42.);
        validator.validate(dto);
    }

    @Test(expected = CustomMethodArgumentNotValidException.class)
    public void validate_from_equals_to() throws Exception {
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