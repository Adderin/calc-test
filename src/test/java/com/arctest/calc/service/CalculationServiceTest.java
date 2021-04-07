package com.arctest.calc.service;

import com.arctest.calc.exception.CalculationException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculationServiceTest {

    private final CalculationService calculationService = new CalculationService();

    @Test
    void shouldReturnCorrectResultsWhenCalculate() {
        //Arrange
        final String formula_1 = "10 + 2 * 6";
        final String formula_2 = "100 * 2 + 12";
        final String formula_3 = "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) -11) - 11";
        //Act
        //Assert
        assertEquals("22", calculationService.calculate(formula_1));
        assertEquals("212", calculationService.calculate(formula_2));
        assertEquals("190", calculationService.calculate(formula_3));
    }

    @Test
    public void shouldThrowExceptionWhenClosingBracketsNotFound() {
        //Arrange
        final String formula = "100 * ( 2 + 12 ";
        //Act
        //Assert
        assertThrows(CalculationException.class,
                () -> calculationService.calculate(formula));
    }

    @Test
    public void shouldThrowExceptionWhenDivisionByZero() {
        //Arrange
        final String formula = "100 / 0";
        //Act
        //Assert
        assertThrows(ArithmeticException.class,
                () -> calculationService.calculate(formula));
    }
}