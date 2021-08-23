package co.edu.ucentral.tiendaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ucentral.tiendaWeb.model.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Usuario findByUsername(String username);
}
