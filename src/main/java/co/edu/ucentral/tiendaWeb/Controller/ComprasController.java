package co.edu.ucentral.tiendaWeb.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.ucentral.tiendaWeb.model.Banco;

import co.edu.ucentral.tiendaWeb.model.Compras;
import co.edu.ucentral.tiendaWeb.model.Producto;
import co.edu.ucentral.tiendaWeb.model.Usuario;
import co.edu.ucentral.tiendaWeb.service.BancoService;

import co.edu.ucentral.tiendaWeb.service.CompraService;
import co.edu.ucentral.tiendaWeb.service.ProductoService;
import co.edu.ucentral.tiendaWeb.service.UsuarioService;
@Controller

@RequestMapping("/compras")
public class ComprasController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProductoService productoService;
	

	@Autowired
	private BancoService bancoService;
	
	@Autowired
	private CompraService compraService;
	
	

	
	public boolean buscarEncarro(List<Producto> lista , Integer producto){
		
		boolean bandera = false;
		for(int i=0 ; i<lista.size();i++) {
			
			if(lista.get(i).getId().equals(producto)) {
				
				return bandera=true;
			}
		}
		
		return bandera;
		
		
	}
	
	
	@RequestMapping(value="/agregar/{id}", method=RequestMethod.GET)
	public String comprar(@PathVariable("id") Integer idproducto ,  Model model , HttpSession session , RedirectAttributes attributes) {
		
		Producto producto = productoService.buscarPorId(idproducto);
		
		List<Producto> lista =  new ArrayList<Producto>();
		

		if(session.getAttribute("carrito")!=null) {
			
		lista = (List<Producto>) session.getAttribute("carrito");
		if(buscarEncarro(lista, idproducto) ==true) {
			attributes.addFlashAttribute("msg", "Ya esta en tu carrito");
			return "redirect:/";
		}else {
			lista.add(producto);	
			attributes.addFlashAttribute("msg", "Agregado exitosamente !!");
			session.setAttribute("carrito",lista);
		}
		}else {
			
			lista.add(producto);
			attributes.addFlashAttribute("msg", "Agregado exitosamente !!");
			session.setAttribute("carrito",lista);	
		}
		
		
	
		return "redirect:/";
	}
	

	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String eliminarProducto(@PathVariable("id") int idProducto, Model model , HttpSession session , RedirectAttributes attributes) {
		
		List<Producto> lista =  new ArrayList<Producto>();
		lista = (List<Producto>) session.getAttribute("carrito");
		
		boolean bandera = false;
		
		for(int i=0 ; i<lista.size();i++) {
			
			if(lista.get(i).getId().equals(idProducto)) {
				
				lista.remove(i);
				bandera=true;
				attributes.addFlashAttribute("msg2", "Removido exitosamente");
				return "redirect:/";
			}
		}
		
		if(bandera==false) {
			
			attributes.addFlashAttribute("msg2", "F");
			
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/comprarproducto/{id}", method=RequestMethod.GET)
	public String Confirmarcompra(@PathVariable("id") int idProducto, Model model , Authentication auth ,RedirectAttributes attributes  ) {
		
	
		String username= auth.getName();
		
		Usuario usuario  = usuarioService.bucarPorUserName(username);
		
		boolean bandera  = false;
		boolean compra  =false;
		boolean banderabanco = false;
		boolean banderap= false;
		
			
		Compras obj =  new Compras();
		
		Producto producto = productoService.buscarPorId(idProducto);
		List<Banco> lista = bancoService.ListarCuentas();
		
		List<Compras> listadecompra = compraService.ListarCompras();
		List<Usuario> listauser =  usuarioService.listarUsuarios();
		System.out.println(lista);
		
		System.out.println(producto);
		
		System.out.println("usuario"+username);
	
		
		
	
			
		for(int i =0;i<listadecompra.size();i++) {
			
			
			if(listadecompra.get(i).getIdusuario().getId().equals(usuario.getId())){
				
				for(int j=0;j<listadecompra.size();j++) {
					
					if(listadecompra.get(j).getIdproductos().getId().equals(producto.getId())){
						
						compra=true;
						j=listadecompra.size();
						
						
					}
				}
				if(compra==true) {
					
				for(int k=0 ; k<listadecompra.size();k++) {
					
				if(listadecompra.get(k).getIdproductos().getId().equals(producto.getId() ) && listadecompra.get(k).getIdusuario().getId().equals(usuario.getId())) {
					k=listadecompra.size();
					banderap=true;
					attributes.addFlashAttribute("msg", "Ya tienes este producto");
					return "redirect:/compras/search";
				}
				
				}
				}
				
				if(banderap==false) {
					
				for(int i2=0 ; i2<lista.size();i2++) {
				if(lista.get(i2).getIdusuarios().getId().equals(usuario.getId())) {
				
				if(lista.get(i2).getSaldo()>=producto.getPrecio()) {
				lista.get(i2).setSaldo(lista.get(i2).getSaldo()-producto.getPrecio());
				
				obj.setIdproductos(producto);
				obj.setIdusuario(usuario);
				obj.setFecha(new Date());
				bancoService.guardar(lista.get(i2));
				
				compraService.guardar(obj);
				
				
				
				
				bandera=true;
				i2=lista.size();
				attributes.addFlashAttribute("msg", "Compra realizada");
				
				return "redirect:/compras/search";
				
				
				}else {
					attributes.addFlashAttribute("msg", "Verifique sus fondos del banco");
					return "redirect:/compras/search";
				}
				}
				}
				
				}
				
				
				}
		}
		
		if(bandera==false) {
					
					for(int l=0;l<listauser.size();l++) {
						
					if(listauser.get(l).getId().equals(usuario.getId())) {
						banderabanco=true;
					}
					if(banderabanco==true)
						
					for(int h=0;h<lista.size();h++) { 
					if(lista.get(h).getSaldo()>=producto.getPrecio() && lista.get(h).getIdusuarios().getId().equals(usuario.getId())) {
							
					lista.get(h).setSaldo(lista.get(h).getSaldo()-producto.getPrecio());
							
					obj.setIdproductos(producto);
					obj.setIdusuario(usuario);
					obj.setFecha(new Date());
					
					Banco banco =  new Banco();
					banco.setId(lista.get(h).getId());
					banco.setIdusuarios(usuario);
					banco.setSaldo(lista.get(h).getSaldo());
					
					h=lista.size();
					
				
					bancoService.guardar(banco);
					
					compraService.guardar(obj);
					
					
					attributes.addFlashAttribute("msg", "Compra realizada");
				
					
					return "redirect:/compras/search";
					
						
					}
					}
					}


				}
		


		
		if(bandera==true) {
			
		attributes.addFlashAttribute("msg", "Compra realizada");
	
		
		return "redirect:/compras/search";
		
		}
			
		
		return "revisar";
		
		
	}
	
	
	@GetMapping("/search")
	public String listarProductos(@ModelAttribute("search") Producto producto, Model model , HttpSession session) {
		List<Producto> lista =  new ArrayList<Producto>();
		if(session.getAttribute("carrito")!=null) {
		
			lista = (List<Producto>) session.getAttribute("carrito");
		}
		model.addAttribute("productos", lista);
		return "compras/carrito";
	}
	
	
	
	@GetMapping("/buscarcompras")
	public String listarCompras(@ModelAttribute("buscarcompras") Compras producto, Model model, Authentication auth,RedirectAttributes attributes) {
		System.out.println("Buscando por: " + producto);
		
		String username= auth.getName();
		
		Usuario usuario  = usuarioService.bucarPorUserName(username);
	
		
		List<Compras> lista = compraService.ListarCompras();
		
		boolean bandera=false;
	
		ArrayList<Compras>  listadecompra =  new ArrayList<Compras>(); 
		
		for(int i = 0 ; i< lista.size(); i++) {
			
			if(lista.get(i).getIdusuario().getId().equals(usuario.getId())&&lista.get(i).getIdproductos().getId()!=null) {
				

			
					
					listadecompra.add( lista.get(i));
					
					
					bandera=true;
				
			
					
		}
		}
		
		
		if(bandera==true) {
			model.addAttribute("compra", listadecompra);
			return "compras/historial";
			
		}else {
			attributes.addFlashAttribute("msg", "Todavia no tienes productos comprados");
			return "compras/historial";
		}
		
	}
	
	
	
	
		
	
	
	
	
	
	

}
