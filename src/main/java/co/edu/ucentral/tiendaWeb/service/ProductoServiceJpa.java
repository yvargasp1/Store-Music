package co.edu.ucentral.tiendaWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.ucentral.tiendaWeb.model.Producto;
import co.edu.ucentral.tiendaWeb.repository.ProductoRepository;



@Service
@Primary
public class ProductoServiceJpa implements ProductoService {

	@Autowired
	private  ProductoRepository repo;
	@Override
	public List<Producto> listarProductos() {
		
		return repo.findAll();
	}

	@Override
	public Producto buscarPorId(Integer idProducto) {
		Optional<Producto> optional= repo.findById(idProducto);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public Optional<Producto> buscarPorId2(Integer idProducto) {
		 
		
		return repo.findById(idProducto);
	}
	@Override
	public void guardar(Producto producto) {
		repo.save(producto);

	}
	
	

	@Override
	public void borrar(Integer idproducto) {
		repo.deleteById(idproducto);

	}

	@Override
	public List<Producto> buscarByExample(Example<Producto> example) {
		return repo.findAll(example);
	}

	@Override
	public Page<Producto> buscarProducto(Pageable page) {
		return repo.findAll(page);
	}

}
