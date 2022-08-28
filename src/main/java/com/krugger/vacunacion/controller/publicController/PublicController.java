package com.krugger.vacunacion.controller.publicController;

import com.krugger.vacunacion.exceptions.ErrorRequest;
import com.krugger.vacunacion.service.PublicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.krugger.vacunacion.Utils.Constants.CODE_ERROR_INTERNAL;

@RestController
@RequestMapping("public/")
@CrossOrigin(origins = "*")
public class PublicController {

    @Autowired
    private PublicService publicService;

    /**
     * Obtain public information
     *
     * @return
     */
    @Operation(summary = "Obtain public information ",
            responses = {
                    @ApiResponse(responseCode = "600", description = "Exepcion Internal")})
    @GetMapping(value = "/informationTypeVaccine")
    public ResponseEntity<Object> informationTypeVaccine() {
        try {
            return ResponseEntity.badRequest().body(publicService.findAll());
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }
}
