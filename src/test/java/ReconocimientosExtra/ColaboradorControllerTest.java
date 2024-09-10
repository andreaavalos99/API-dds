package ReconocimientosExtra;

import ReconocimientosExtra.model.Colaborador;
import ReconocimientosExtra.model.ColaboradorController;
import ReconocimientosExtra.model.ColaboradorService;
import ReconocimientosExtra.model.ReconocimientosExtraApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = ReconocimientosExtraApplication.class)
@AutoConfigureMockMvc
public class ColaboradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ColaboradorService colaboradorService;  // Mock del servicio

    @BeforeEach
    void setUp() {
        // Configuramos las respuestas simuladas para el servicio mockeado
        when(colaboradorService.obtenerColaboradoresReconocidos(50, 15, 2)).thenReturn(
                Arrays.asList(
                        new Colaborador(null, "Juan", 50, 20),
                        new Colaborador(null, "Pedro", 70, 15)
                )
        );
    }

    @Test
    void testGetColaboradoresRecomendados() throws Exception {
        mockMvc.perform(get("/api/v1/colaboradores/recomendados")
                        .param("minPuntos", "50")
                        .param("minDonacionViandas", "15")
                        .param("maxColaboradores", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[1].nombre").value("Pedro"));
    }
}