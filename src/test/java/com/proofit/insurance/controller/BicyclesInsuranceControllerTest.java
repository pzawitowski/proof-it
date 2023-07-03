package com.proofit.insurance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proofit.insurance.calculator.RiskType;
import com.proofit.insurance.validator.InsuredObjectValidator;
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

    @Test
    void givenTwoObjects_whenCalculate_thenStatus200WithTwoObjectsWithCalculatedPremiumAndSumInsured() throws Exception {
        CalculationRequest request = new CalculationRequest();
        Bicycle bicycleOne = new Bicycle();
        bicycleOne.setMake("Pearl");
        bicycleOne.setModel("Gravel SL EVO");
        bicycleOne.setCoverage("EXTRA");
        bicycleOne.setManufactureYear(2015);
        bicycleOne.setSumInsured(new BigDecimal("1000.00"));
        bicycleOne.setRisks(List.of(RiskType.DAMAGE));

        Bicycle bicycleTwo = new Bicycle();
        bicycleTwo.setMake("Whyte");
        bicycleTwo.setModel("T-160 RS");
        bicycleTwo.setCoverage("STANDARD");
        bicycleTwo.setManufactureYear(2019);
        bicycleTwo.setSumInsured(new BigDecimal("500.00"));
        bicycleTwo.setRisks(List.of(RiskType.THIRD_PARTY_DAMAGE, RiskType.DAMAGE, RiskType.THEFT));

        request.setBicycles(List.of(bicycleOne, bicycleTwo));

        mvc.perform(post(CALCULATE_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("objects", hasSize(2)))
                .andExpect(jsonPath("objects[0].coverageType", equalTo("EXTRA")))
                .andExpect(jsonPath("objects[0].attributes.MANUFACTURE_YEAR", equalTo(2015)))
                .andExpect(jsonPath("objects[0].attributes.MODEL", equalTo("Gravel SL EVO")))
                .andExpect(jsonPath("objects[0].attributes.MAKE", equalTo("Pearl")))
                .andExpect(jsonPath("objects[0].risks", hasSize(1)))
                .andExpect(jsonPath("objects[0].risks[0].riskType", equalTo("DAMAGE")))
                .andExpect(jsonPath("objects[0].risks[0].premium", notNullValue()))
                .andExpect(jsonPath("objects[0].risks[0].sumInsured", notNullValue()))
                .andExpect(jsonPath("objects[1].coverageType", equalTo("STANDARD")))
                .andExpect(jsonPath("objects[1].attributes.MANUFACTURE_YEAR", equalTo(2019)))
                .andExpect(jsonPath("objects[1].attributes.MODEL", equalTo("T-160 RS")))
                .andExpect(jsonPath("objects[1].attributes.MAKE", equalTo("Whyte")))
                .andExpect(jsonPath("objects[1].risks", hasSize(3)))
                .andExpect(jsonPath("objects[1].risks[0].riskType", equalTo("THIRD_PARTY_DAMAGE")))
                .andExpect(jsonPath("objects[1].risks[0].premium", notNullValue()))
                .andExpect(jsonPath("objects[1].risks[0].sumInsured", notNullValue()))
                .andExpect(jsonPath("objects[1].risks[1].riskType", equalTo("DAMAGE")))
                .andExpect(jsonPath("objects[1].risks[1].premium", notNullValue()))
                .andExpect(jsonPath("objects[1].risks[1].sumInsured", notNullValue()))
                .andExpect(jsonPath("objects[1].risks[2].riskType", equalTo("THEFT")))
                .andExpect(jsonPath("objects[1].risks[2].premium", notNullValue()))
                .andExpect(jsonPath("objects[1].risks[2].sumInsured", notNullValue()));
    }

    @Test
    void givenSumInsuredTooHigh_whenCalculate_thenStatus422WithErrorResponse() throws Exception {
        CalculationRequest request = new CalculationRequest();
        Bicycle bicycle = new Bicycle();
        bicycle.setMake("Pearl");
        bicycle.setModel("Gravel SL EVO");
        bicycle.setCoverage("EXTRA");
        bicycle.setManufactureYear(2015);
        bicycle.setSumInsured(new BigDecimal(InsuredObjectValidator.MAX_SUM_INSURED + 1000));
        bicycle.setRisks(List.of(RiskType.DAMAGE));


        request.setBicycles(List.of(bicycle));

        mvc.perform(post(CALCULATE_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", notNullValue()));
    }
}
