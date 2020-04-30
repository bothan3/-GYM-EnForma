package controller;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Clase;
import model.Noticia;
import model.Profesor;
import model.Socio;
import model.User;

@Controller
@EntityScan(basePackages = "model")
@RequestMapping("/")
public class WebController {

	@Autowired
	private NoticiasRepository noticiasRepository;
	
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
//			noticiasRepository.save(new Noticia("Cierre por COVID-19", "Debido a la situación extraordinaria que estamos sufriendo, el gimnasio cerrará hasta junio."));
//			noticiasRepository.save(new Noticia("Nueva clase de cardio", "Este miercoles a las 18.00 tendremos una clase de cario indoor de spinning en nuestro canal youtube"));
//			noticiasRepository.save(new Noticia("Nueva clase de baile", "Este jueves tendremos una clase de baile en nuestro canal youtube"));
//			noticiasRepository.save(new Noticia("Nueva clase de crossfit", "Este viernes tendremos una crossfit de cario indoor de spinning en nuestro canal youtube"));
//			noticiasRepository.save(new Noticia("Proxima apertura", "Es posible que la apertura del gimnasio se retrase a 2021. Sentimos las molestias"));
//	}

	@GetMapping("/")
	public String mostrarPortada(Model model,  HttpServletRequest request, Pageable page) throws IOException, ServletException {

		 model.addAttribute("admin", request.isUserInRole("ADMIN"));
		 model.addAttribute("user", request.isUserInRole("USER"));
		 model.addAttribute("usuario", request.getRemoteUser());

		 //Creamos una pagina con las ultimas noticias
		 page = PageRequest.of(0, 4, Sort.by("id").descending());
		 
		 model.addAttribute("noticias", noticiasRepository.findAll(page));
		
		return "portada";
	}


	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		return "login";
	}

	@GetMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}
	
	@GetMapping("/tablon/noticia")
	public String listarNoticias(Model model, Pageable page, HttpServletRequest request) {
		page = PageRequest.of(0, 5);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("noticias", noticiasRepository.findAll(page));
		model.addAttribute("paginacion", true);
		model.addAttribute("antNum", 0);
		model.addAttribute("sigNum", 1);
		return "tablon/noticia";	
	}
	@GetMapping("/tablon/noticia/{num}")
	public String listarNoticiasPag(Model model, Pageable page, @PathVariable int num, HttpServletRequest request) {
		page = PageRequest.of(num, 5);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("noticias", noticiasRepository.findAll(page));
		model.addAttribute("paginacion", true);
		if (num == 0) {
			model.addAttribute("antNum", 0);
		}else {
			model.addAttribute("antNum",num-1);
		}
		model.addAttribute("sigNum", num+1);
		return "tablon/noticia";	
	}
	@PostMapping("/tablon/insertarNoticia")
	public String nuevaNotica (Model model, Noticia noticia) {
		noticiasRepository.save(noticia);	
		return "validacion";
	}
	
	@GetMapping("/cargar")
	public String cargarDatos (Model model) {
		//Borro la informacion de la base de datos
		noticiasRepository.deleteAll();
		claseRepository.deleteAll();
		socioRepository.deleteAll();
		profesorRepository.deleteAll();
		userRepository.deleteAll();
		
		//Inserto la información
		//Noticias
		noticiasRepository.save(new Noticia("Cierre por COVID-19", "Debido a la situación extraordinaria que estamos sufriendo, el gimnasio cerrará hasta junio."));
		noticiasRepository.save(new Noticia("Nueva clase de cardio", "Este miercoles a las 18.00 tendremos una clase de cario indoor de spinning en nuestro canal youtube"));
		noticiasRepository.save(new Noticia("Nueva clase de baile", "Este jueves tendremos una clase de baile en nuestro canal youtube"));
		noticiasRepository.save(new Noticia("Nueva clase de crossfit", "Este viernes tendremos una crossfit de cario indoor de spinning en nuestro canal youtube"));
		noticiasRepository.save(new Noticia("Proxima apertura", "Es posible que la apertura del gimnasio se retrase a 2021. Sentimos las molestias"));
		noticiasRepository.save(new Noticia("Proxima master class", "El día de la apertura tendremos una master class de spinning"));

		
		//Clases
		claseRepository.save(new Clase("Cardio",30,"Baja",3));
		claseRepository.save(new Clase("Tonificacion",45,"Baja",4));
		claseRepository.save(new Clase("Baile",45,"Media",6));
		claseRepository.save(new Clase("Cardio",60,"Media",9));
		claseRepository.save(new Clase("Tonificacion",60,"Alta",12));
		claseRepository.save(new Clase("Baile",90,"Alta",13));
		
		//Usuarios
		userRepository.save(new User("user", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "admin", "ROLE_ADMIN"));
		
		//Profesores
		profesorRepository.save(new Profesor("IVAN", "RUIZ", "SOLER", "74364294A", "RUIZSOLER@gmail.com"));
		profesorRepository.save(new Profesor("JOSE", "LUIS", "ROSIQUE", "23023982A", "LUISROSIQUE@gmail.com"));
		profesorRepository.save(new Profesor("MARCO", "ANTONIO", "JIMENEZ", "21492944A", "ANTONIOJIMENEZ@gmail.com"));
		profesorRepository.save(new Profesor("ELENA", "PATERNA", "MORAN", "14312215A", "PATERNAMORAN@gmail.com"));
		profesorRepository.save(new Profesor("ALBERT", "FERNANDEZ", "MONTALVO", "74366966A", "FERNANDEZMONTALVO@gmail.com"));
		
		//Socios
		socioRepository.save(new Socio("ALFONSO","JOSE","TOBARRA","7550239A","JOSETOBARRA@gmail.com"));
		socioRepository.save(new Socio("MARIA","JOSE","IZQUIERDO","5684469A","JOSEIZQUIERDO@gmail.com"));
		socioRepository.save(new Socio("DANIEL","MUÑOZ","PICON","77722638A","MUÑOZPICON@gmail.com"));
		socioRepository.save(new Socio("LUIS","ANTONIO","DELICADO","21678229A","ANTONIODELICADO@gmail.com"));
		socioRepository.save(new Socio("DIEGO","CRESPO","SALMERON","75246716A","CRESPOSALMERON@gmail.com"));
		
		return "validacion";
	}
}
