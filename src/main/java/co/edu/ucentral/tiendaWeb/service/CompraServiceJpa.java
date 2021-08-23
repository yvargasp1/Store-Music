package co.edu.ucentral.tiendaWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import co.edu.ucentral.tiendaWeb.model.Banco;
import co.edu.ucentral.tiendaWeb.model.Compras;
import co.edu.ucentral.tiendaWeb.repository.CompraRepository;
@Service
@Primary
public class CompraServiceJpa implements CompraService{
@Autowired
private CompraRepository repo;
@Override
public List<Compras> ListarCompras() {
		
		return repo.findAll();
	}
@Override
public void guardar(Compras compra) {
	
	repo.save(compra);
	
}
}
