package controller;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import model.Socio;

public interface SocioRepository extends JpaRepository<Socio, Long>{
	
	List<Socio> findByNombreIgnoreCase(String nombre);
	List<Socio> findByApellido1IgnoreCase(String nombre);
	List<Socio> findByDniIgnoreCase(String nombre);

}
