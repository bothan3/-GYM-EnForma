package controller;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Clase;


@CacheConfig(cacheNames="clase")
public interface ClaseRepository extends JpaRepository<Clase, Long>{

	@CacheEvict(allEntries=true)
	Clase save (Clase clase);
	@Cacheable
	List<Clase> findByTipoIgnoreCase(String nombre);
	@Cacheable
	List<Clase> findByOrderByFechaDesc();


}
