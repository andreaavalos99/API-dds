package ReconocimientosExtra.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    @Autowired
    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<Colaborador> obtenerColaboradoresReconocidos(int puntajeMinimo, int donacionMinima, int maxColaboradores) {
        List<Colaborador> colaboradores = colaboradorRepository.findByPuntajeGreaterThanEqualAndDonacionesUltimoMesGreaterThanEqual(puntajeMinimo, donacionMinima);
        return colaboradores.stream().limit(maxColaboradores).collect(Collectors.toList());
    }

}
