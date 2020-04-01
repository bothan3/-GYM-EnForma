package controller;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

}
