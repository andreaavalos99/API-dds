package ReconocimientosExtra.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    /*
    * Spring Data JPA interpreta el nombre del método y
    genera automáticamente la consulta SQL necesaria para realizar la búsqueda en la base de datos.
    No necesitas escribir la implementación de la consulta manualmente.
     */

    List<Colaborador> findByPuntajeGreaterThanEqualAndDonacionesUltimoMesGreaterThanEqual(Integer puntaje, Integer donacionesUltimoMes);
}
