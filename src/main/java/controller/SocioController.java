package controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Profesor;
import model.Socio;

@Controller
@EntityScan(basePackages = "model")
@RequestMapping("/socios")
public class SocioController {

	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private SocioRepository socioRepository;
	
	
	
	@PostConstruct
	public void init () {
		for (int i=0; i<5; i++) {
			socioRepository.save(new Socio("socio"+i,"apellido1"+i,"apellido2"+i,"h"));
		} 
	}
	
	@GetMapping("/listadoSocios")
	public String listado(Model model) {
		
		model.addAttribute("socios", socioRepository.findAll());
		
		return "socios/listadoSocios";	
	}
	
	@GetMapping("/modificar")
	public String modificarSocio(Model model, @RequestParam long id, @RequestParam String nombre, @RequestParam String apellido1,
			@RequestParam String apellido2, @RequestParam String sexo) {
		
		Optional<Socio> socio = socioRepository.findById(id);
		Socio sociorModif = socio.get();
		
		if(socio.isPresent()) {
			sociorModif.setNombre(nombre);
			sociorModif.setApellido1(apellido1);
			sociorModif.setApellido2(apellido2);
			sociorModif.setSexo(sexo);
			
			socioRepository.save(sociorModif);
		}
		model.addAttribute("ruta", "socios");
		return "validacion";
	}
	
	
	@PostMapping("/alta")
	public String altaUsuario(Model model, Socio socio) {

		socioRepository.save(socio);
		model.addAttribute("ruta", "socios");

		return "validacion";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarUsuario(Model model, @PathVariable long id) {
	Optional<Socio> socio = socioRepository.findById(id);
		
		if(socio.isPresent()) {
			socioRepository.delete(socio.get());
			model.addAttribute("ruta", "socios");
		}
		return "validacion";
		
	}
	
	@GetMapping("/asignarProfesor/{id}")
	public String asignarUsuarios(Model model, @PathVariable long id) {
		
		Optional<Socio> socio = socioRepository.findById(id);
		
		if(socio.isPresent()) {
			model.addAttribute("socio", socio.get());
			model.addAttribute("Profesores", profesorRepository.findAll());
		}
		
		return "socios/asignarProfesor";
	}
	
	@PostMapping("/asignarProfesor")
	public String asignarUsuariosProfesor(Model model,  long idProfesor,  long idSocio) {
		Optional<Profesor> profesor = profesorRepository.findById(idProfesor);
		Optional<Socio> socio = socioRepository.findById(idSocio);
		Profesor profe = profesor.get();
		
		if((socio.isPresent())&(profesor.isPresent())){
			List<Socio> sociosProfesor = profe.getSocios();
			sociosProfesor.add(socio.get());

			socio.get().setProfesor(profesor.get());
			profe.setSocios(sociosProfesor);
			
			socioRepository.save(socio.get());
			profesorRepository.save(profe);
			
		}
		model.addAttribute("ruta", "socios");		
		return "validacion";
	}
	


	
	

	
}
