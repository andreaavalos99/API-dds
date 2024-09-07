package ReconocimientosExtra.model;

import ReconocimientosExtra.model.Colaborador;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "colaboradores")
public class Colaborador {

    // Getters y setters
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private int puntaje;
	private int donacionViandas;

}
