package controller;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import model.Noticia;

public interface NoticiasRepository extends JpaRepository<Noticia, Long> {
	
	//Page<Noticia> findAllOrderByFechaDesc(Pageable page);
	//List <Noticia> findFirst4All();
//	@Cacheable
//	Page<Noticia> findAll(Pageable page);
}
