package controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Busqueda;
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
	

	
	@PostConstruct
	public void init() {
		for (int i=0; i<5; i++) {
			claseRepository.save(new Clase("tipo"+i,60,"duracion"+i));
		} 
	}
	
	@PostMapping("/crearClase")
	public String asignarUsuariosProfesor(Model model,  Clase clase) {
		
		claseRepository.save(clase);
		model.addAttribute("clase", clase);
		model.addAttribute("Profesores", profesorRepository.findAll());

		return "clases/asignarProfesorClase";
	}
	
	@GetMapping("/modificar")
	public String modificarClase(Model model, @RequestParam long id, @RequestParam String tipo, @RequestParam Date fecha,
			@RequestParam int duracion, @RequestParam String intensidad) {
		
		Optional<Clase> clase = claseRepository.findById(id);
		Clase claseModif = clase.get();
		
		if(clase.isPresent()) {
			claseModif.setTipo(tipo);
			claseModif.setFecha(fecha);
			claseModif.setDuracion(duracion);
			claseModif.setIntensidad(intensidad);
			
			claseRepository.save(claseModif);
		}
		model.addAttribute("ruta", "clases");
		return "validacion";
	}
	
	//Asigna el profesor a una clase en ambas entidades
	@PostMapping("/asignarProfesorClase")
	public String asignarProfesorClase(Model model,  long idProfesor,  long idClase) {
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
	
	//Carga el menu de profesores para asignar a una clase
	@GetMapping("/asignarProfesor/{id}")
	public String asignarProfesor(Model model, @PathVariable long id) {
		
		Optional<Clase> clase = claseRepository.findById(id);
		
		if(clase.isPresent()) {
			model.addAttribute("clase", clase.get());
			model.addAttribute("Profesores", profesorRepository.findAll());
		}
		
		return "clases/asignarProfesorClase";
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
	
	@GetMapping("/eliminarProfesor/{id}")
	public String eliminarSocio(Model model, @PathVariable long id) {
		
		Optional<Clase> clase = claseRepository.findById(id);
		
		if(clase.isPresent()) {
			Profesor profesor = clase.get().getProfesor();
			profesor.deleteClase(clase.get());
			clase.get().deleteProfesor();
			
			profesorRepository.save(profesor);
			claseRepository.save(clase.get());
			model.addAttribute("ruta", "clases");
		}
		
		return "validacion";
	}
	
	//Busqueda personalizada
	@PostMapping("/busquedaPersonalizada")
	public String busquedaPersonanlizada (Model model, Busqueda busqueda) {
		
		switch(busqueda.getTipo()) {
		case 4:
			model.addAttribute("clases", claseRepository.findByTipoIgnoreCase(busqueda.getPalabra()));
			break;
		case 5:
			model.addAttribute("clases", claseRepository.findByOrderByFechaDesc());
			break;
		}
		return "clases/listadoClases";
	}
	
}