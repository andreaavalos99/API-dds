package ReconocimientosExtra.model;

import ReconocimientosExtra.model.Colaborador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public List<Colaborador> obtenerColaboradoresReconocidos(int puntajeMinimo, int donacionMinima, int maxColaboradores) {
        List<Colaborador> colaboradores = colaboradorRepository.findByPuntajeGreaterThanEqualAndDonacionViandasGreaterThanEqual(puntajeMinimo, donacionMinima);
        return colaboradores.stream().limit(maxColaboradores).collect(Collectors.toList());
    }

    public void sincronizarColaboradores(List<Colaborador> colaboradoresExternos) {
        colaboradorRepository.saveAll(colaboradoresExternos);
    }
}
