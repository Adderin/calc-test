package com.arctest.calc.controller;

import com.arctest.calc.api.CalcAPI;
import com.arctest.calc.api.request.CalculateFormulaRequest;
import com.arctest.calc.api.response.CalculateResponse;
import com.arctest.calc.exception.CalculationException;
import com.arctest.calc.service.CalculationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Log4j2
@RestController
public class CalculateController implements CalcAPI {

    private final CalculationService calculationService;

    public CalculateController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @Override
    public CalculateResponse calculate(CalculateFormulaRequest calculateFormulaRequest) {
        log.trace("Received a request: {}", calculateFormulaRequest);
        final String formula = calculateFormulaRequest.getFormula();
        if (Objects.isNull(formula)){
            log.debug("Formula is null.");
            throw new CalculationException("Formula cannot be null.");
        }
        return CalculateResponse.builder()
                .result(calculationService.calculate(formula))
                .build();
    }
}
