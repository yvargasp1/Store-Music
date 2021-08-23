package co.edu.ucentral.tiendaWeb.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.ucentral.tiendaWeb.model.Categoria;
import co.edu.ucentral.tiendaWeb.model.Producto;
import co.edu.ucentral.tiendaWeb.repository.ProductoRepository;
import co.edu.ucentral.tiendaWeb.service.CategoriaService;
import co.edu.ucentral.tiendaWeb.service.ProductoService;



@Controller
@RequestMapping("/productos")
public class ProductoCotroller {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	//@GetMapping("/index")
	@RequestMapping(value= "/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Producto> lista = productoService.listarProductos();
		model.addAttribute("empleos", lista);
		return "productos/listado";
	}
	
	//@GetMapping("/index")
	@RequestMapping(value= "/indexPaginate", method=RequestMethod.GET)
	public String mostrarIndex(Model model, Pageable page) {
		Page<Producto> lista = productoService.buscarProducto(page);
		model.addAttribute("productos", lista);
		return "productos/listado";
	}

	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crearEmpleo(Producto empleo, Model model) {
		List<Categoria> lista = categoriaService.listarCategorias();
		model.addAttribute("categorias", lista);
		return "productos/formProducto";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(@RequestParam("file") MultipartFile foto, Producto producto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "productos/formProducto";
		}
		
		if(!foto.isEmpty()) {
			
			
			//Path directorioImagenes =  Paths.get("src//main//resources//static//images");
			//String rutaabsoluta = directorioImagenes.toFile().getAbsolutePath();
			String rutaabsoluta = "C://Producto//recursos";

			try {
				byte[] byteimg = foto.getBytes();
				Path rutacompleta= Paths.get(rutaabsoluta+"//"+foto.getOriginalFilename());
				Files.write(rutacompleta, byteimg);
				producto.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
					
		}
		productoService.guardar(producto);
		System.out.println("Producto " + producto);
		attributes.addFlashAttribute("msg", "Registro guardo");
		return "redirect:/productos/indexPaginate";
	}
	
	@RequestMapping(value="/view/{id}", method=RequestMethod.GET)
	public String mostrarProducto(@PathVariable("id") int idProducto, Model model) {
		Producto producto = productoService.buscarPorId(idProducto);
		model.addAttribute("producto", producto);
		return "productos/formProducto";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editarProducto(@PathVariable("id") int idProducto, Model model) {
		Producto producto = productoService.buscarPorId(idProducto);
		model.addAttribute("producto", producto);
		List<Categoria> lista = categoriaService.listarCategorias();
		model.addAttribute("categorias", lista);
		return "productos/formProducto";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String eliminarProducto(@PathVariable("id") int idProducto, Model model) {
		productoService.borrar(idProducto);
		return "redirect:/productos/indexPaginate";
	}
	
	@InitBinder	
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false) );
	}
}
