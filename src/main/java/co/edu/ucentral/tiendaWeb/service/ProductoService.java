package co.edu.ucentral.tiendaWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.ucentral.tiendaWeb.model.Producto;



public interface ProductoService {

	public List<Producto> listarProductos();
	public Producto buscarPorId(Integer idProducto);
	public void guardar(Producto empleo);
	public void borrar(Integer idEmpleo);
	public List<Producto> buscarByExample(Example<Producto> example);
	public Page<Producto> buscarProducto(Pageable page);
	public Optional<Producto> buscarPorId2(Integer idProducto);
}
