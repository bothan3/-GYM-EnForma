package controller;

import java.io.IOException;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;

import model.Noticia;
import model.Comentario;

@Controller
@EntityScan(basePackages = "model")
@RequestMapping("/")
public class WebController {

	@Autowired
	private NoticiasRepository noticiasRepository;

	@Autowired
	private ComentarioRepository comentariosRepository;

	@PostConstruct
	public void init() {
			noticiasRepository.save(new Noticia("Cierre por COVID-19", "Debido a la situación extraordinaria que estamos sufriendo, el gimnasio cerrará hasta junio."));
			noticiasRepository.save(new Noticia("Nueva clase de cardio", "Este miercoles a las 18.00 tendremos una clase de cario indoor de spinning en nuestro canal youtube"));
			noticiasRepository.save(new Noticia("Nueva clase de baile", "Este jueves tendremos una clase de baile en nuestro canal youtube"));
			noticiasRepository.save(new Noticia("Nueva clase de crossfit", "Este viernes tendremos una crossfit de cario indoor de spinning en nuestro canal youtube"));
			noticiasRepository.save(new Noticia("Proxima apertura", "Es posible que la apertura del gimnasio se retrase a 2021. Sentimos las molestias"));
	}

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

	@GetMapping("/noticia/{id}")
	public String mostrarPortada(Model model, @PathVariable long id) {

		Optional<Noticia> noticia = noticiasRepository.findById(id);

		if (noticia.isPresent()) {
			model.addAttribute("noticia", noticia.get());
			model.addAttribute("comentarios", noticia.get().getComentarios());
		}

		return "/tablon/noticia";
	}

	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		return "login";
	}

	@GetMapping("/loginerror")
	public String loginerror() {
		return "loginerror";
	}

}
