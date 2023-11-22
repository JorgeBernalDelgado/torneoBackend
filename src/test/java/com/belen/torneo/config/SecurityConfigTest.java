package com.belen.torneo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPublicAccessToApiTestEndpoints() throws Exception {
        // Verifica que la solicitud se complete con éxito (código de estado 200)
        mockMvc.perform(MockMvcRequestBuilders.get("/api/equiposDeportistas/listarDeportistaAnotaciones")
                        .param("torneo", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/equiposDeportistas/listarVallaMenosVencida")
                        .param("torneo", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}