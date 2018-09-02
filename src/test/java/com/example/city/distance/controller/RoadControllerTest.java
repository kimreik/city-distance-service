package com.example.city.distance.controller;

import com.example.city.distance.dto.RoadDTO;
import com.example.city.distance.service.RoadService;
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

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void add_valid() throws Exception {
        performPost(getValidDTO())
                .andExpect(status().isOk());
    }

    @Test
    public void add_empty_to() throws Exception {
        RoadDTO dto = getValidDTO().setTo("");
        performPost(dto)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_empty_from() throws Exception {
        RoadDTO dto = getValidDTO().setFrom("");
        performPost(dto)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_null_distance() throws Exception {
        RoadDTO dto = getValidDTO().setDistance(null);
        performPost(dto)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_negative_distance() throws Exception {
        RoadDTO dto = getValidDTO().setDistance(-42.);
        performPost(dto)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void get_roads_valid() throws Exception {
        mockMvc.perform(get("/roads")
                .param("from", "from")
                .param("to", "to")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void get_roads_empty_from() throws Exception {
        mockMvc.perform(get("/roads")
                .param("to", "to")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void get_roads_empty_to() throws Exception {
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