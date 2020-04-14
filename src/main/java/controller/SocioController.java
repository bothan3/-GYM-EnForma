package controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import model.Busqueda;
import model.Clase;
import model.Profesor;
import model.Socio;
import model.User;

@Controller
@EntityScan(basePackages = "model")
@RequestMapping("/socios")
public class SocioController {

	@Autowired
	private ProfesorRepository profesorRepository;

	@Autowired
	private SocioRepository socioRepository;

	@Autowired
	private ClaseRepository claseRepository;

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		socioRepository.save(new Socio("ALFONSO","JOSE","TOBARRA","7550239","JOSETOBARRA@gmail.com"));
		socioRepository.save(new Socio("MARIA","JOSE","IZQUIERDO","5684469","JOSEIZQUIERDO@gmail.com"));
		socioRepository.save(new Socio("DANIEL","MUÑOZ","PICON","77722638","MUÑOZPICON@gmail.com"));
		socioRepository.save(new Socio("LUIS","ANTONIO","DELICADO","21678229","ANTONIODELICADO@gmail.com"));
		socioRepository.save(new Socio("DIEGO","CRESPO","SALMERON","75246716","CRESPOSALMERON@gmail.com"));
	}

	//Nos devueleve el listaod completo de socios
	@GetMapping("/listadoSocios")
	public String listado(Model model) {
		model.addAttribute("socios", socioRepository.findAll());
		return "socios/listadoSocios";
	}

	//Modifica los datos personales de un socio
	@GetMapping("/modificar")
	public String modificarSocio(Model model, @RequestParam long id, @RequestParam String nombre,
			@RequestParam String apellido1, @RequestParam String apellido2, @RequestParam String dni,
			@RequestParam String correo) {

		Optional<Socio> socio = socioRepository.findById(id);
		Socio sociorModif = socio.get();

		if (socio.isPresent()) {
			sociorModif.setNombre(nombre);
			sociorModif.setApellido1(apellido1);
			sociorModif.setApellido2(apellido2);
			sociorModif.setDni(dni);
			sociorModif.setCorreo(correo);

			socioRepository.save(sociorModif);
		}
		model.addAttribute("ruta", "socios");
		return "validacion";
	}

	//Da de alta un usuario y su user
	@PostMapping("/alta")
	public String altaUsuario(Model model, Socio socio, @RequestParam String password) {

		socioRepository.save(socio);
		
		User usuario = new User(socio.getNombre(), password, "ROLE_USER");
		socio.setUsuario(usuario);
		userRepository.save(usuario);
		model.addAttribute("ruta", "socios");

		return "validacion";
	}
	
	//Borra un usuario de la base de datos y sus relaciones
	@GetMapping("/borrar/{id}")
	public String borrarUsuario(Model model, @PathVariable long id) {
		Optional<Socio> socio = socioRepository.findById(id);

		if (socio.isPresent()) {
			
			if (socio.get().getProfesor() != null) {
				Profesor profesor = socio.get().getProfesor();
				profesor.deleteSocio(socio.get());
				profesorRepository.save(profesor);
			}
			
			for (Clase clase : socio.get().getClases()) {
				clase.deleteSocio(socio.get());
				claseRepository.save(clase);
			}
			socioRepository.delete(socio.get());
			model.addAttribute("ruta", "socios");
		}
		return "validacion";

	}

//	@GetMapping("/asignarProfesor/{id}")
//	public String asignarUsuarios(Model model, @PathVariable long id) {
//
//		Optional<Socio> socio = socioRepository.findById(id);
//
//		if (socio.isPresent()) {
//			model.addAttribute("socio", socio.get());
//			model.addAttribute("Profesores", profesorRepository.findAll());
//		}
//
//		return "socios/asignarProfesor";
//	}
//
//	@PostMapping("/asignarProfesor")
//	public String asignarUsuariosProfesor(Model model, long idProfesor, long idSocio) {
//		Optional<Profesor> profesor = profesorRepository.findById(idProfesor);
//		Optional<Socio> socio = socioRepository.findById(idSocio);
//		Profesor profe = profesor.get();
//
//		if ((socio.isPresent()) & (profesor.isPresent())) {
//			List<Socio> sociosProfesor = profe.getSocios();
//			sociosProfesor.add(socio.get());
//
//			socio.get().setProfesor(profesor.get());
//			profe.setSocios(sociosProfesor);
//
//			socioRepository.save(socio.get());
//			profesorRepository.save(profe);
//
//		}
//		model.addAttribute("ruta", "socios");
//		return "validacion";
//	}
	//Busqueda según criterio
	@PostMapping("/busquedaPersonalizada")
	public String busquedaPersonanlizada(Model model, Busqueda busqueda) {

		switch (busqueda.getTipo()) {
		case 1:
			model.addAttribute("socios", socioRepository.findByNombreIgnoreCase(busqueda.getPalabra()));
			break;
		case 2:
			model.addAttribute("socios", socioRepository.findByApellido1IgnoreCase(busqueda.getPalabra()));
			break;
		case 3:
			model.addAttribute("socios", socioRepository.findByDniIgnoreCase(busqueda.getPalabra()));
			break;
		}

		return "socios/listadoSocios";
	}

	@GetMapping("/ModificarDatos/{nombre}")
	public String busquedaPersonanlizada(Model model, @PathVariable String nombre) {

		model.addAttribute("socios", socioRepository.findByNombreIgnoreCase(nombre));

		return "socios/listadoSocios";
	}

	//Cargamos la lista de clases para apuntarse
	@GetMapping("/apuntarClase/{idSocio}")
	public  String apuntarClase (Model model, @PathVariable long idSocio ) {
		
		model.addAttribute("socio", socioRepository.findById(idSocio).get());
		model.addAttribute("clases", claseRepository.findAll());
		
		
		return "socios/apuntarClase";
	}

	//Añadimos una clase y socio en ambas bases de datos.
	@PostMapping("/apuntarClase")
	public  String apuntarClase (Model model, long idClase, long idSocio) {
		
		Optional<Clase> clase = claseRepository.findById(idClase);
		Optional<Socio> socio = socioRepository.findById(idSocio);

			
		if (!(socio.get().getClases().contains(clase.get()))) {
			
			socio.get().addClase(clase.get());
			clase.get().addSocio(socio.get());
			
			socioRepository.save(socio.get());
			claseRepository.save(clase.get());
		}else {
			model.addAttribute("mensaje", "Ya estas inscrito en esta clase.");
			return "error";
		}
		model.addAttribute("ruta", "socios");
		return "validacion";
	}
	
	//Cargamos la lista de clases para apuntarse
	@GetMapping("/desapuntarClase/{idSocio}")
	public  String desapuntarClase (Model model, @PathVariable long idSocio ) {
		
		model.addAttribute("socio", socioRepository.findById(idSocio).get());
		model.addAttribute("clases", socioRepository.findById(idSocio).get().getClases());
		
		
		return "socios/desapuntarClase";
	}

	//Añadimos una clase y socio en ambas bases de datos.
	@PostMapping("/desapuntarClase")
	public  String desapuntarClase (Model model, long idClase, long idSocio) {
		
		Optional<Clase> clase = claseRepository.findById(idClase);
		Optional<Socio> socio = socioRepository.findById(idSocio);

		if ((socio.isPresent()) & (clase.isPresent())) {
			
			socio.get().deleteClase(clase.get());
			clase.get().deleteSocio(socio.get());
			
			socioRepository.save(socio.get());
			claseRepository.save(clase.get());
		}
		model.addAttribute("ruta", "socios");
		return "validacion";
	}
	
	
}
