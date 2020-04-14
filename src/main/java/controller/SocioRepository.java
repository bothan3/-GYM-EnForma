package controller;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


import model.Socio;

@CacheConfig(cacheNames="socio")
public interface SocioRepository extends JpaRepository<Socio, Long>{
	
	@CacheEvict(allEntries=true)
	Socio save (Socio socio);
	
	@Cacheable
	List<Socio> findByNombreIgnoreCase(String nombre);
	@Cacheable
	List<Socio> findByApellido1IgnoreCase(String nombre);
	@Cacheable
	List<Socio> findByDniIgnoreCase(String nombre);
	@Cacheable
	List<Socio> findByCorreoIgnoreCase(String correo);
}
