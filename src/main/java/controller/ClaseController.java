package controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Clase;
import model.Profesor;
import model.Socio;

@Controller
@EntityScan(basePackages = "model")
@RequestMapping("/clases")
public class ClaseController {

	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private SocioRepository socioRepository;
	
	@Autowired
	private ClaseRepository claseRepository;
	

	
	@PostMapping("/crearClase")
	public String asignarUsuariosProfesor(Model model,  Clase clase) {
		
		claseRepository.save(clase);
		model.addAttribute("clase", clase);
		model.addAttribute("Profesores", profesorRepository.findAll());

		return "clases/asignarProfesorClase";
		
	}
	
	@PostMapping("/asignarProfesorClase")
	public String asignarUsuariosProfesor(Model model,  long idProfesor,  long idClase) {
		Optional<Profesor> profesor = profesorRepository.findById(idProfesor);
		Optional<Clase> clase = claseRepository.findById(idClase);
		Profesor profe = profesor.get();
		
		if((clase.isPresent())&(profesor.isPresent())){
			List<Clase> clasesProfesor = profe.getClases();
			clasesProfesor.add(clase.get());

			clase.get().setProfesor(profesor.get());
			profe.setClases(clasesProfesor);
			
			claseRepository.save(clase.get());
			profesorRepository.save(profe);
		}
		model.addAttribute("ruta", "clases");		
		return "validacion";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarClase(Model model, @PathVariable long id) {
	Optional<Clase> clase = claseRepository.findById(id);
		
		if(clase.isPresent()) {
			Profesor profesor = clase.get().getProfesor();
			profesor.deleteClase(clase.get());
			claseRepository.delete(clase.get());
			model.addAttribute("ruta", "clases");
		}
		return "validacion";
		
	}
	
	@GetMapping("/listadoClases")
	public String listado(Model model) {
		
		model.addAttribute("clases", claseRepository.findAll());
		
		return "clases/listadoClases";	
	}
	
}
