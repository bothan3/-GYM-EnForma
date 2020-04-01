package controller;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Comentario;

public interface ComentarioRepository  extends JpaRepository<Comentario, Long> {

}
