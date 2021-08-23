package co.edu.ucentral.tiendaWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import co.edu.ucentral.tiendaWeb.model.Categoria;
import co.edu.ucentral.tiendaWeb.repository.CategoriaRepository;



@Service
@Primary
public class CategoriaServiceJpa implements CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	@Override
	public List<Categoria> listarCategorias() {
		
		return repo.findAll();
	}

	@Override
	public Categoria buscarPorId(int idCategoria) {
		Optional<Categoria> optional = repo.findById(idCategoria);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Categoria categoria) {
		repo.save(categoria);
		
	}

	@Override
	public void borrar(Integer idCategoria) {
		repo.deleteById(idCategoria);
		
	}

}
