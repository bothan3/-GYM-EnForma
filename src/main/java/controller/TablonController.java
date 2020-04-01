package controller;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Noticia;
import model.Comentario;
import model.Profesor;
import model.Socio;

@Controller
@EntityScan(basePackages = "model")
@RequestMapping("/tablon")
public class TablonController {

	@Autowired
	private NoticiasRepository noticiasRepository;
	
	@Autowired
	private ComentarioRepository comentariosRepository;
	
	
	@PostConstruct
	public void init() {
		for(int i = 0; i<5; i++){
			Noticia articulo= new Noticia("New"+i, "My new product"+1);

			articulo.addComentario(new Comentario("Cool", "Pepe"));
			articulo.addComentario(new Comentario("Very cool", "Juan"));
			
			noticiasRepository.save(articulo);
		}
		
	}
	
	@GetMapping("/portada")
	public String mostrarPortada(Model model) {
		
		model.addAttribute("noticias", noticiasRepository.findAll());
		
		return "/tablon/portada";
	}
	
	@GetMapping("/noticia/{id}")
	public String mostrarPortada(Model model, @PathVariable long id) {
		
		Optional<Noticia> noticia = noticiasRepository.findById(id);
		
		if(noticia.isPresent()) {
			model.addAttribute("noticia", noticia.get());
			model.addAttribute("comentarios", noticia.get().getComentarios());
		}
				
		return "/tablon/noticia";
	}
	
	
	
}
