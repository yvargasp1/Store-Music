package co.edu.ucentral.tiendaWeb.service;

import java.util.List;

import co.edu.ucentral.tiendaWeb.model.Usuario;

public interface UsuarioService {

	public void guardarUsuario(Usuario usuario);
	public void eliminarUsuario(Integer idUsuario);
	public List<Usuario> listarUsuarios();
	public Usuario bucarPorUserName(String username);
}
