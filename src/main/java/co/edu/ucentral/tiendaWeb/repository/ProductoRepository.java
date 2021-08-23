package co.edu.ucentral.tiendaWeb.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ucentral.tiendaWeb.model.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
}
