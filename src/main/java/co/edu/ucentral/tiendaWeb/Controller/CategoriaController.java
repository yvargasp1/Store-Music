package co.edu.ucentral.tiendaWeb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.ucentral.tiendaWeb.model.Categoria;
import co.edu.ucentral.tiendaWeb.service.CategoriaService;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	//@Qualifier("categoriaServiceJpa")
	private CategoriaService categoriaService;
	
	//GetMapping("/index")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = categoriaService.listarCategorias();
		model.addAttribute("categorias", lista);
		return "categorias/listadoCategorias";
	}
	
	//GetMapping("/create")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(Categoria categoria) {
		return "categorias/categoriaForm";
	}
	
	//GetMapping("/save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "categorias/categoriaForm";
		}
		categoriaService.guardar(categoria);
		attributes.addAttribute("msg", "Categoria guardad");
		return "redirect:/categorias/index";
	}
	
	@RequestMapping(value="/view/{id}")
	public String buscar(@PathVariable("id") int idCategoria, Model model) {
		Categoria categoria = categoriaService.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/categoriaForm";
	}
	
	
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idCategoria, Model model, RedirectAttributes attributes) {
		categoriaService.borrar(idCategoria);
		attributes.addFlashAttribute("msg", "Registro borrado");
		return "redirect:/categorias/index";
	}
	
}

