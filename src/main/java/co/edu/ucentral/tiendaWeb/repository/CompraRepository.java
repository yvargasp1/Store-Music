package co.edu.ucentral.tiendaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ucentral.tiendaWeb.model.Compras;
import co.edu.ucentral.tiendaWeb.service.CompraService;

public interface CompraRepository extends JpaRepository<Compras, Integer> {

}
