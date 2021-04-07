package com.arctest.calc.spring;

import com.arctest.calc.api.request.CalculateFormulaRequest;
import com.arctest.calc.api.response.CalculateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalcApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void calculateShouldReturnSuccess() {
        //Arrange
        final String formula = "2 * 2";
        CalculateFormulaRequest calculateFormulaRequest = new CalculateFormulaRequest();
        calculateFormulaRequest.setFormula(formula);

        //Act
        String url = "http://localhost:" + port;
        URI uri = UriComponentsBuilder.fromHttpUrl(url).path("/calculate").build().toUri();

        final ResponseEntity<CalculateResponse> response = restTemplate.postForEntity(uri, calculateFormulaRequest, CalculateResponse.class);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
