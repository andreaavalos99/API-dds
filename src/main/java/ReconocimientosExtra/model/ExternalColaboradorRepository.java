package ReconocimientosExtra.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExternalColaboradorRepository extends JpaRepository<Colaborador, Long> {
    // Este método recupera todos los colaboradores sin ningún filtro
    List<Colaborador> findAll();
}
