package co.edu.ucentral.tiendaWeb.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.ucentral.tiendaWeb.model.Categoria;
import co.edu.ucentral.tiendaWeb.model.Perfil;
import co.edu.ucentral.tiendaWeb.model.Producto;
import co.edu.ucentral.tiendaWeb.model.Usuario;
import co.edu.ucentral.tiendaWeb.service.CategoriaService;
import co.edu.ucentral.tiendaWeb.service.ProductoService;
import co.edu.ucentral.tiendaWeb.service.UsuarioService;



@Controller
public class HomeController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	
	
	
	
	@GetMapping("/")
	public String mostrarHome(Categoria categoria, Model model) {

		return "home";
	}
	
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth , HttpSession session) {
		
		String username= auth.getName();
		System.out.println("usuario"+username);
		for(GrantedAuthority rol: auth.getAuthorities()) {
			
			System.out.println("Rol:"+rol.getAuthority());
			
		}
		
		if(session.getAttribute("usuario") == null) {
			
			Usuario usuario  = usuarioService.bucarPorUserName(username);
			usuario.setPassword(null);
			System.out.println("Usuario:"+usuario);
			session.setAttribute("usuario", usuario);
			
			
		}
		
		return "redirect:/";
		
		
		
	}
	
	 
	 
	 
	@GetMapping("/login")
	public String ingresar(Usuario usuario) {
		return "formLogin";
	}
	
	@PostMapping("/login")
	public String ingresarlOG(Usuario usuario) {
		return "redirect:/";
	}
	
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario) {
		String pwdPlano = usuario.getPassword();
		String pwdEncrip = passwordEncoder.encode(pwdPlano);
		usuario.setPassword(pwdEncrip);
		usuario.setFechaRegistro(new Date());
		usuario.setEstatus(1);
		Perfil perfil = new Perfil();
		perfil.setId(3);
		usuario.agregarPerfil(perfil);
		usuarioService.guardarUsuario(usuario);
		return "redirect:/";
		
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
	
		return "redirect:/login";
	}
	
	@GetMapping("/bcrypt/{texto}")
	//no muestra la pagina
	@ResponseBody
	public String encriptar(@PathVariable("texto") String texto) {
		return texto + "-->"+passwordEncoder.encode(texto);
		
		
	}
	
	@GetMapping("/search")
	public String listarProductos(@ModelAttribute("search") Producto producto, Model model) {
		System.out.println("Buscando por: " + producto);
		Example<Producto> example = Example.of(producto);
		List<Producto> lista = productoService.buscarByExample(example);
		model.addAttribute("productos", lista);
		return "/home";
	}
	
	
	@ModelAttribute
	public void setGenericos(Model model) {
		Producto productoSearch = new Producto();
		
		model.addAttribute("productos", productoService.listarProductos());
		model.addAttribute("categorias", categoriaService.listarCategorias());
		model.addAttribute("search", productoSearch);
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	
}
