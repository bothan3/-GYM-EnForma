package controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Profesor;
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

	List<Profesor> findByNombreIgnoreCase(String nombre);
	List<Profesor> findByApellido1IgnoreCase(String nombre);
	List<Profesor> findByDniIgnoreCase(String nombre);

}
