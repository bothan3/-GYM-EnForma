package controller;

import java.awt.List;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;

import model.Busqueda;
import model.Profesor;
import model.Socio;

@Controller
@EntityScan(basePackages = "model")
@RequestMapping("/profesores")
public class ProfesorController {
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private SocioRepository socioRepository;
	
	@PostConstruct
	public void init() {
		for(int i = 0; i<5; i++){
			profesorRepository.save(new Profesor("Profesor"+i, "Apellido"+i));
		}
	}
		
	@GetMapping("/listadoProfesores")
	public String listado (Model model) {
		model.addAttribute("Profesores", profesorRepository.findAll());
		
		return "profesores/listadoProfesores";
	}
	
	@GetMapping("/asignarSocio/{id}")
	public String asignarUsuarios(Model model, @PathVariable long id) {
		
		Optional<Profesor> profesor = profesorRepository.findById(id);
		
		if(profesor.isPresent()) {
			for (Socio socio: socioRepository.findAll()) {
				socio.setProfesor(profesor.get());
			}
			profesorRepository.save(profesor.get());
		}
		
		return "ok";
	}
	
	@GetMapping("/modificar")
	public String modificarProfesor(Model model, @RequestParam long id, @RequestParam String nombre, @RequestParam String apellido1,
			@RequestParam String apellido2, @RequestParam String dni, @RequestParam String correo) {
		
		Optional<Profesor> profesor = profesorRepository.findById(id);
		Profesor profesorModif = profesor.get();
		
		if(profesor.isPresent()) {
			profesorModif.setNombre(nombre);
			profesorModif.setApellido1(apellido1);
			profesorModif.setApellido2(apellido2);
			profesorModif.setDni(dni);
			profesorModif.setCorreo(correo);
			
			profesorRepository.save(profesorModif);
		}
		model.addAttribute("ruta", "profesores");
		return "validacion";
	}
	
	@PostMapping("/alta")
	public String altaUsuario(Model model, Profesor profesor) {

		profesorRepository.save(profesor);
		model.addAttribute("ruta", "profesores");

		return "validacion";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarProfesor(Model model, @PathVariable long id) {
	Optional<Profesor> profesor = profesorRepository.findById(id);
	
		
		if(profesor.isPresent()) {
			for (Socio socio : profesor.get().getSocios()) {
				socio.deleteProfesor();
			}
			profesorRepository.delete(profesor.get());
			model.addAttribute("ruta", "profesores");
		}
		return "validacion";
		
	}
	
	@GetMapping("/eliminarSocio/{id}")
	public String eliminarSocio(Model model, @PathVariable long id) {
		
		Optional<Profesor> profesor = profesorRepository.findById(id);
		
		if(profesor.isPresent()) {
			model.addAttribute("profesor", profesor.get());
			model.addAttribute("socios", profesor.get().getSocios());
		}
		
		return "profesores/eliminarSocio";
	}
	
	@PostMapping("/eliminarSocio")
	public String asignarUsuariosProfesor(Model model,  long idProfesor,  long idSocio) {
		Optional<Profesor> profesor = profesorRepository.findById(idProfesor);
		Optional<Socio> socio = socioRepository.findById(idSocio);
		Profesor profe = profesor.get();
		
		if((socio.isPresent())&(profesor.isPresent())){
			profe.deleteSocio(socio.get());
			socio.get().deleteProfesor();
			
			socioRepository.save(socio.get());
			profesorRepository.save(profe);
			
		}
		model.addAttribute("ruta", "profesores");		
		return "validacion";
	}
	
	//Busqueda personalizada
	@PostMapping("/busquedaPersonalizada")
	public String busquedaPersonanlizada (Model model, Busqueda busqueda) {
		
		switch(busqueda.getTipo()) {
		case 1:
			model.addAttribute("Profesores", profesorRepository.findByNombreIgnoreCase(busqueda.getPalabra()));
			break;
		case 2:
			model.addAttribute("Profesores", profesorRepository.findByApellido1IgnoreCase(busqueda.getPalabra()));
			break;
		case 3:
			model.addAttribute("Profesores", profesorRepository.findByDniIgnoreCase(busqueda.getPalabra()));
			break;
		}
		
		return "profesores/listadoProfesores";
	}
	
	
}
