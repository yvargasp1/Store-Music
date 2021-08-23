package co.edu.ucentral.tiendaWeb.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.ucentral.tiendaWeb.model.Compras;
import co.edu.ucentral.tiendaWeb.model.Usuario;
import co.edu.ucentral.tiendaWeb.service.CompraService;


import co.edu.ucentral.tiendaWeb.service.UsuarioService;



@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CompraService compraService;
	
	
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("usuarios", usuarioService.listarUsuarios());
		return "usuarios/listaUsuarios";
	}
	
	@PostMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer idUsuario, RedirectAttributes attributes  ) {
		
		
		
		usuarioService.eliminarUsuario(idUsuario);
		
		attributes.addFlashAttribute("msg", "Usuario eliminado");
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/buscaradmin")
	public String listarComprasadmin(@ModelAttribute("buscaradmin") Compras producto, Model model) {
		System.out.println("Buscando por: " + producto);
		
	
		List<Compras> lista = compraService.ListarCompras();
		
	
		model.addAttribute("compra", lista);
		
			return "usuarios/historialAdmin";
		}
	
}