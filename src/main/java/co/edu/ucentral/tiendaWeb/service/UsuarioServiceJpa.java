package co.edu.ucentral.tiendaWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ucentral.tiendaWeb.model.Usuario;
import co.edu.ucentral.tiendaWeb.repository.UsuarioRepository;



@Service
public class UsuarioServiceJpa implements UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public void guardarUsuario(Usuario usuario) {
		repo.save(usuario);

	}

	@Override
	public void eliminarUsuario(Integer idUsuario) {
		repo.deleteById(idUsuario);

	}

	@Override
	public List<Usuario> listarUsuarios() {
		return repo.findAll();
	}

	@Override
	public Usuario bucarPorUserName(String username) {
		
		return repo.findByUsername(username);
	}

}
