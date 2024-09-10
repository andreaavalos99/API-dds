package ReconocimientosExtra;

import ReconocimientosExtra.model.Colaborador;
import ReconocimientosExtra.model.ColaboradorRepository;
import ReconocimientosExtra.model.ColaboradorService;
import ReconocimientosExtra.model.ReconocimientosExtraApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ReconocimientosExtraApplication.class)  // Asegúrate de que esta clase esté en el paquete correcto
public class ColaboradorServiceTest {

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @BeforeEach
    void setUp() {
        colaboradorRepository.deleteAll();  // Limpiar la base de datos antes de cada prueba
        // Agregar colaboradores de prueba a la base de datos
        colaboradorRepository.save(new Colaborador(null, "Juan", 50, 20));
        colaboradorRepository.save(new Colaborador(null, "Ana", 60, 10));
        colaboradorRepository.save(new Colaborador(null, "Pedro", 70, 15));
        colaboradorRepository.save(new Colaborador(null, "Laura", 40, 25));
    }

    @Test
    void testObtenerColaboradoresReconocidos() {
        List<Colaborador> colaboradores = colaboradorService.obtenerColaboradoresReconocidos(50, 15, 2);
        assertEquals(2, colaboradores.size());
        assertTrue(colaboradores.stream().anyMatch(c -> c.getNombre().equals("Juan")));
        assertTrue(colaboradores.stream().anyMatch(c -> c.getNombre().equals("Pedro")));
    }

    @Test
    void testObtenerColaboradoresReconocidosNoResultados() {
        List<Colaborador> colaboradores = colaboradorService.obtenerColaboradoresReconocidos(100, 30, 2);
        assertTrue(colaboradores.isEmpty());
    }
}
