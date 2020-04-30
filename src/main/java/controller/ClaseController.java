package controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	

	
//	@PostConstruct
//	public void init() {
//		claseRepository.save(new Clase("Cardio",30,"Baja",3));
//		claseRepository.save(new Clase("Tonificacion",45,"Baja",4));
//		claseRepository.save(new Clase("Baile",45,"Media",6));
//		claseRepository.save(new Clase("Cardio",60,"Media",9));
//		claseRepository.save(new Clase("Tonificacion",60,"Alta",12));
//		claseRepository.save(new Clase("Baile",90,"Alta",13));
//	}
	
	//Crea una clase
	@PostMapping("/crearClase")
	public String asignarUsuariosProfesor(Model model,  Clase clase) {
		
		claseRepository.save(clase);
		model.addAttribute("clase", clase);
		model.addAttribute("Profesores", profesorRepository.findAll());

		return "clases/asignarProfesorClase";
	}
	
	//Modifica la info de las clases
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
		
		if(clase.isPresent() & (!(clase.get().isEmptyProfesor()))) {
			model.addAttribute("clase", clase.get());
			model.addAttribute("Profesores", profesorRepository.findAll());
			return "clases/asignarProfesorClase";
		}
		else {
			model.addAttribute("mensaje", "Esta clase ya tiene profesor..");
			return "error";
		}
		
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarClase(Model model, @PathVariable long id) {
	Optional<Clase> clase = claseRepository.findById(id);
		
		if(clase.isPresent()) {
			if (!(clase.get().isEmptyProfesor())){
				Profesor profesor = clase.get().getProfesor();
				profesor.deleteClase(clase.get());
			}
			claseRepository.delete(clase.get());
			model.addAttribute("ruta", "clases");
		}
		return "validacion";
		
	}
	
	@GetMapping("/listadoClases")
	public String listado(Model model, Pageable page) {
		page = PageRequest.of(0, 5);
		model.addAttribute("clases", claseRepository.findAll(page));
		model.addAttribute("paginacion", true);
		model.addAttribute("antNum", 0);
		model.addAttribute("sigNum", 1);
		return "clases/listadoClases";	
	}
	@GetMapping("/listadoClases/{num}")
	public String listadoPag(Model model, Pageable page,  @PathVariable int num) {
		page = PageRequest.of(num, 5);
		model.addAttribute("paginacion", true);
		model.addAttribute("clases", claseRepository.findAll(page));
		if (num == 0) {
			model.addAttribute("antNum", 0);
		}else {
			model.addAttribute("antNum",num-1);
		}
		model.addAttribute("sigNum", num+1);
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
		model.addAttribute("paginacion", false);

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