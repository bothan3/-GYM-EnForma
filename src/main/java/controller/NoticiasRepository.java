package controller;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Noticia;

public interface NoticiasRepository extends JpaRepository<Noticia, Long> {

}
