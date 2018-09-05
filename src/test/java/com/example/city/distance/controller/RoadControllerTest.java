package com.example.city.distance.controller;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.service.RoadService;
import com.example.city.distance.validator.RoadDTOValidator;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RoadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoadService roadService;

    @MockBean
    private RoadDTOValidator validator;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addValid() throws Exception {
        performPost(getValidDTO())
                .andExpect(status().isOk());
    }

    @Test
    public void getRoadsValid() throws Exception {
        mockMvc.perform(get("/roads")
                .param("from", "from")
                .param("to", "to")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getRoadsEmptyFrom() throws Exception {
        mockMvc.perform(get("/roads")
                .param("to", "to")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getRoadsEmptyTo() throws Exception {
        mockMvc.perform(get("/roads")
                .param("from", "from")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    private ResultActions performPost(RoadDTO dto) throws Exception {
        return mockMvc.perform(post("/roads")
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON));
    }

    private RoadDTO getValidDTO() {
        return new RoadDTO()
                .setTo("to")
                .setFrom("from")
                .setDistance(42.);
    }


}