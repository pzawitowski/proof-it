package com.proofit.insurance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proofit.insurance.calculator.RiskType;
import com.proofit.insurance.view.Bicycle;
import com.proofit.insurance.view.CalculationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BicyclesInsuranceControllerTest {

    private static final String CALCULATE_ENDPOINT = "/api/v1/calculate";

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void givenOneObject_whenCalculate_thenStatus200WithThisObjectAndCalculatedPremiumAndSumInsured() throws Exception {
        CalculationRequest request = new CalculationRequest();
        Bicycle bicycle = new Bicycle();
        bicycle.setMake("Pearl");
        bicycle.setModel("Gravel SL EVO");
        bicycle.setCoverage("EXTRA");
        bicycle.setManufactureYear(2015);
        bicycle.setSumInsured(new BigDecimal("1000.00"));
        bicycle.setRisks(List.of(RiskType.DAMAGE));

        request.setBicycles(List.of(bicycle));

        mvc.perform(post(CALCULATE_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("objects", hasSize(1)))
                .andExpect(jsonPath("objects[0].coverageType", equalTo("EXTRA")))
                .andExpect(jsonPath("objects[0].attributes.MANUFACTURE_YEAR", equalTo(2015)))
                .andExpect(jsonPath("objects[0].attributes.MODEL", equalTo("Gravel SL EVO")))
                .andExpect(jsonPath("objects[0].attributes.MAKE", equalTo("Pearl")))
                .andExpect(jsonPath("objects[0].risks", hasSize(1)))
                .andExpect(jsonPath("objects[0].risks[0].riskType", equalTo("DAMAGE")))
                .andExpect(jsonPath("objects[0].risks[0].premium", notNullValue()))
                .andExpect(jsonPath("objects[0].risks[0].sumInsured", notNullValue()));
    }
}
