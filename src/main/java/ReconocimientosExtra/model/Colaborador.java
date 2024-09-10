package ReconocimientosExtra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "colaboradores")
public class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private Integer puntaje;
	private Integer donacionesUltimoMes;

	// Constructor sin argumentos (requerido por JPA)
	public Colaborador() {
	}

	// Constructor con parámetros
	public Colaborador(Long id, String nombre, Integer puntaje, Integer donacionesUltimoMes) {
		this.id = id;
		this.nombre = nombre;
		this.puntaje = puntaje;
		this.donacionesUltimoMes = donacionesUltimoMes;
	}
}