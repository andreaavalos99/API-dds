package ReconocimientosExtra;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import ReconocimientosExtra.model.Colaborador;
import ReconocimientosExtra.model.ColaboradorRepository;
import ReconocimientosExtra.model.ColaboradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ColaboradorServiceTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @InjectMocks
    private ColaboradorService colaboradorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerColaboradoresReconocidos() {
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

        List<Colaborador> colaboradoresMock = Arrays.asList(colaborador1, colaborador2);

        when(colaboradorRepository.findByPuntajeGreaterThanEqualAndDonacionViandasGreaterThanEqual(80, 5))
                .thenReturn(colaboradoresMock);

        // Act
        List<Colaborador> result = colaboradorService.obtenerColaboradoresReconocidos(80, 5, 10);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Juan Perez", result.get(0).getNombre());
        assertEquals("Ana Gomez", result.get(1).getNombre());
    }

    @Test
    void testObtenerColaboradoresReconocidos_Limit() {
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

        List<Colaborador> colaboradoresMock = Arrays.asList(colaborador1, colaborador2);

        when(colaboradorRepository.findByPuntajeGreaterThanEqualAndDonacionViandasGreaterThanEqual(80, 5))
                .thenReturn(colaboradoresMock);

        // Act
        List<Colaborador> result = colaboradorService.obtenerColaboradoresReconocidos(80, 5, 1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan Perez", result.get(0).getNombre());
    }
}
