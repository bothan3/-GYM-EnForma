package controller;

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
import model.Socio;
import model.User;

@Controller
@EntityScan(basePackages = "model")
@RequestMapping("/profesores")
public class ProfesorController {

	@Autowired
	private ProfesorRepository profesorRepository;

	@Autowired
	private SocioRepository socioRepository;

	@Autowired
	private ClaseRepository claseRepository;

	@Autowired
	private UserRepository userRepository;

//	@PostConstruct
//	public void init() {
//		profesorRepository.save(new Profesor("IVAN", "RUIZ", "SOLER", "74364294", "RUIZSOLER@gmail.com"));
//		profesorRepository.save(new Profesor("JOSE", "LUIS", "ROSIQUE", "23023982", "LUISROSIQUE@gmail.com"));
//		profesorRepository.save(new Profesor("MARCO", "ANTONIO", "JIMENEZ", "21492944", "ANTONIOJIMENEZ@gmail.com"));
//		profesorRepository.save(new Profesor("ELENA", "PATERNA", "MORAN", "14312215", "PATERNAMORAN@gmail.com"));
//		profesorRepository.save(new Profesor("ALBERT", "FERNANDEZ", "MONTALVO", "74366966", "FERNANDEZMONTALVO@gmail.com"));
//	}

	// Listar todo los profesores
	@GetMapping("/listadoProfesores")
	public String listado(Model model, Pageable page) {
		model.addAttribute("paginacion", true);
		page = PageRequest.of(0, 5);		
		model.addAttribute("profesores", profesorRepository.findAll(page));
		model.addAttribute("antNum", 0);
		model.addAttribute("sigNum", 1);
		return "profesores/listadoProfesores";
	}
	
	@GetMapping("/listadoProfesores/{num}")
	public String listadoPag(Model model, Pageable page, @PathVariable int num) {
		model.addAttribute("paginacion", true);
		page = PageRequest.of(num, 5);		
		model.addAttribute("profesores", profesorRepository.findAll(page));
		if (num == 0) {
			model.addAttribute("antNum", 0);
		}else {
			model.addAttribute("antNum",num-1);
		}
		model.addAttribute("sigNum", num+1);
		return "profesores/listadoProfesores";
	}

	// Modificar datos personales
	@GetMapping("/modificar")
	public String modificarProfesor(Model model, @RequestParam long id, @RequestParam String nombre,
			@RequestParam String apellido1, @RequestParam String apellido2, @RequestParam String dni,
			@RequestParam String correo) {

		Optional<Profesor> profesor = profesorRepository.findById(id);
		Profesor profesorModif = profesor.get();

		if (profesor.isPresent()) {
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

	// Alta de un nuevo profesor en la base de datos y su user
	@PostMapping("/alta")
	public String altaUsuario(Model model, Profesor profesor,@RequestParam String user, @RequestParam String password) {
		
		if (userRepository.findByNameIgnoreCase(user) == null) {
			User usuario = new User(user, password,"ROLE_ADMIN");
			profesor.setUsuario(usuario);
			userRepository.save(usuario);
			profesorRepository.save(profesor);
			model.addAttribute("ruta", "profesores");
	
			return "validacion";
		}else {
			model.addAttribute("mensaje", "Este usuario esta ya en uso, inserta otro por favor");
			return "error";
		}
	}

	// Borrar Profesor y sus relaciones en las otra entidades
	@GetMapping("/borrar/{id}")
	public String borrarProfesor(Model model, @PathVariable long id) {

		Optional<Profesor> profesor = profesorRepository.findById(id);

		if (profesor.isPresent()) {
			for (Socio socio : profesor.get().getSocios()) {
				socio.deleteProfesor();
				socioRepository.save(socio);
			}
			for (Clase clase : profesor.get().getClases()) {
				clase.deleteProfesor();
				claseRepository.save(clase);
			}

			profesorRepository.delete(profesor.get());
			model.addAttribute("ruta", "profesores");
		}
		return "validacion";

	}
	
	// Busqueda personalizada por criterio
	@PostMapping("/busquedaPersonalizada")
	public String busquedaPersonanlizada(Model model, Busqueda busqueda) {
		model.addAttribute("paginacion", false);
		switch (busqueda.getTipo()) {
		case 1:
			model.addAttribute("profesores", profesorRepository.findByNombreIgnoreCase(busqueda.getPalabra()));
			break;
		case 2:
			model.addAttribute("profesores", profesorRepository.findByApellido1IgnoreCase(busqueda.getPalabra()));
			break;
		case 3:
			model.addAttribute("profesores", profesorRepository.findByDniIgnoreCase(busqueda.getPalabra()));
			break;
		}

		return "profesores/listadoProfesores";
	}

	// Asignar Socio: muestra listado de socios
	@GetMapping("/asignarSocio/{id}")
	public String asignarSocioListado (Model model, @PathVariable long id) {
		Optional<Profesor> profesor = profesorRepository.findById(id);

		if (profesor.isPresent()) {
			model.addAttribute("profe", profesor.get());
			model.addAttribute("socios", socioRepository.findAll());
		}

		return "profesores/asignarSocio";
	}
	
	// Asignar socio: asigna un socio a un profesor 
	@PostMapping("/asignarSocio")
	public String asignarsSocio(Model model, long idProfesor, long idSocio) {
		
		
		Optional<Profesor> profesor = profesorRepository.findById(idProfesor);
		Optional<Socio> socio = socioRepository.findById(idSocio);
		if ((socio.get().isEmpyProfesor())){
			model.addAttribute("mensaje", "Este socio ya tiene un profesor asignado.");
			return "error";
		}else {		
			if ((socio.isPresent()) & (profesor.isPresent())) {
		
			socio.get().setProfesor(profesor.get());
			profesor.get().addSocio(socio.get());
			
			socioRepository.save(socio.get());
			profesorRepository.save(profesor.get());
			}
		model.addAttribute("ruta", "profesores");
		return "validacion";
		}
	}

	//Eliminar socio: muestra la lista de socios de ese profesor
	@GetMapping("/eliminarSocio/{id}")
	public String eliminarSocio(Model model, @PathVariable long id) {

		Optional<Profesor> profesor = profesorRepository.findById(id);

		if (profesor.isPresent()) {
			model.addAttribute("profesor", profesor.get());
			model.addAttribute("socios", profesor.get().getSocios());
		}

		return "profesores/eliminarSocio";
	}
	
	//Eliminar socio: elimina de la base de datos la relacion entre profesor y socio
	@PostMapping("/eliminarSocio")
	public String asignarUsuariosProfesor(Model model, long idProfesor, long idSocio) {
		Optional<Profesor> profesor = profesorRepository.findById(idProfesor);
		Optional<Socio> socio = socioRepository.findById(idSocio);
		Profesor profe = profesor.get();

		if ((socio.isPresent()) & (profesor.isPresent())) {
			profe.deleteSocio(socio.get());
			socio.get().deleteProfesor();

			socioRepository.save(socio.get());
			profesorRepository.save(profe);

		}
		model.addAttribute("ruta", "profesores");
		return "validacion";
	}

}
