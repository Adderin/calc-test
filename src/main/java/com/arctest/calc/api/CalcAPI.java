package com.arctest.calc.api;

import com.arctest.calc.api.request.CalculateFormulaRequest;
import com.arctest.calc.api.response.CalculateResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@OpenAPIDefinition(
        info = @Info(
                title = "Calculation-Service",
                version = "0.0.1-SNAPSHOT",
                contact = @Contact(url = "http://localhost:8088/", name = "Adel")
        )
)
@RequestMapping("/")
public interface CalcAPI {

    /**
     * calculate operation
     *
     * @return - response with calculation result
     */
    @PostMapping("calculate")
    @Operation(summary = "Calculate result",
            responses = {@ApiResponse(description = "CalculateResponse",
                    headers = {@Header(name = "duration", description = "Time in milliseconds the processing took from request to response",
                            schema = @Schema(allOf = Long.class))
                    })
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculation successful"),
            @ApiResponse(responseCode = "405", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NotFound")
    })
    CalculateResponse calculate(@RequestBody @Parameter(description = "calculateCalcRequest", required = true) CalculateFormulaRequest calculateFormulaRequest);
}