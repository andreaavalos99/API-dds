package ReconocimientosExtra.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping("/colaboradores/recomendados")
    public ResponseEntity<List<Colaborador>> getColaboradoresRecomendados(
            @RequestParam int minPuntos,
            @RequestParam int minDonacionViandas,
            @RequestParam int maxColaboradores) {

        try {
            List<Colaborador> colaboradores = colaboradorService.obtenerColaboradoresReconocidos(minPuntos, minDonacionViandas, maxColaboradores);
            if (colaboradores.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(colaboradores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/colaboradores/sincronizar")
    public ResponseEntity<String> sincronizarColaboradores(@RequestBody List<Colaborador> colaboradoresExternos) {
        try {
            colaboradorService.sincronizarColaboradores(colaboradoresExternos);
            return ResponseEntity.ok("Colaboradores sincronizados correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al sincronizar colaboradores");
        }
    }
}