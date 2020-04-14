package controller;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import model.Profesor;

@CacheConfig(cacheNames="profesor")
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

	@CacheEvict(allEntries=true)
	Profesor save (Profesor profesor);
	@Cacheable
	List<Profesor> findByNombreIgnoreCase(String nombre);
	@Cacheable
	List<Profesor> findByApellido1IgnoreCase(String nombre);
	@Cacheable
	List<Profesor> findByDniIgnoreCase(String nombre);
}
