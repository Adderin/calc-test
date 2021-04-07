package com.arctest.calc.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(name = "CalculateFormulaRequest", description = "Contains submitted formula")
@Data
public class CalculateFormulaRequest {

    @Schema(description = "formula")
    @NotNull
    private String formula;
}