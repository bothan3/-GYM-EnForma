package controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Clase;
import model.Socio;

public interface ClaseRepository extends JpaRepository<Clase, Long>{

	List<Clase> findByTipoIgnoreCase(String nombre);
	List<Clase> findByOrderByFechaDesc();

}
