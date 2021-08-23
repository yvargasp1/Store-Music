package co.edu.ucentral.tiendaWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import co.edu.ucentral.tiendaWeb.model.Banco;
import co.edu.ucentral.tiendaWeb.model.Producto;
import co.edu.ucentral.tiendaWeb.repository.BancoRepository;
import co.edu.ucentral.tiendaWeb.repository.UsuarioRepository;

@Service
@Primary
public class BancoServiceJpa implements BancoService{
	
	@Autowired
	private BancoRepository repo;
	
	@Override
	public List<Banco> buscarByExample(Example<Banco> example) {
		return repo.findAll(example);
	}
	@Override
	
	public List<Banco> ListarCuentas() {
		
		return repo.findAll();
	}
	@Override
	public Banco buscarPorId(Integer idProducto) {
		Optional<Banco> optional= repo.findById(idProducto);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Override
	public void guardar(Banco cuenta) {
		repo.save(cuenta);

	}
	
	

}
