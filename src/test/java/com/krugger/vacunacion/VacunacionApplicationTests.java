package com.krugger.vacunacion;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class VacunacionApplicationTests {

    @Autowired
    private MockMvc mock;

    /**
     * Presentamos un test ya que los demas manejan roles por jwt
     *
     * @throws Exception
     */
    @Test
    @Order(0)
    void testTypeVaccine() throws Exception {
        mock.perform(get("/public/informationTypeVaccine")).andDo(print());
    }

}
