package ReconocimientosExtra;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import ReconocimientosExtra.model.Colaborador;
import ReconocimientosExtra.model.ColaboradorService;
import ReconocimientosExtra.model.ColaboradorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ColaboradorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ColaboradorService colaboradorService;

    @InjectMocks
    private ColaboradorController colaboradorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(colaboradorController).build();
    }

    @Test
    void testGetColaboradoresRecomendados() throws Exception {
        // Arrange
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setId(1L);
        colaborador1.setNombre("Juan Perez");
        colaborador1.setPuntaje(85);
        colaborador1.setDonacionViandas(10);

        Colaborador colaborador2 = new Colaborador();
        colaborador2.setId(2L);
        colaborador2.setNombre("Ana Gomez");
        colaborador2.setPuntaje(90);
        colaborador2.setDonacionViandas(12);

        when(colaboradorService.obtenerColaboradoresReconocidos(80, 5, 10))
                .thenReturn(Arrays.asList(colaborador1, colaborador2));

        // Act & Assert
        mockMvc.perform(get("/api/v1/colaboradores/recomendados")
                        .param("minPuntos", "80")
                        .param("minDonacionViandas", "5")
                        .param("maxColaboradores", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Perez"))
                .andExpect(jsonPath("$[1].nombre").value("Ana Gomez"));
    }

    @Test
    void testGetColaboradoresRecomendados_Limit() throws Exception {
        // Arrange
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setId(1L);
        colaborador1.setNombre("Juan Perez");
        colaborador1.setPuntaje(85);
        colaborador1.setDonacionViandas(10);

        when(colaboradorService.obtenerColaboradoresReconocidos(80, 5, 1))
                .thenReturn(Arrays.asList(colaborador1));

        // Act & Assert
        mockMvc.perform(get("/api/v1/colaboradores/recomendados")
                        .param("minPuntos", "80")
                        .param("minDonacionViandas", "5")
                        .param("maxColaboradores", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Perez"));
    }
}
