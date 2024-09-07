package ReconocimientosExtra.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    List<Colaborador> findByPuntajeGreaterThanEqualAndDonacionViandasGreaterThanEqual(int puntajeMinimo, int donacionMinima);
}
