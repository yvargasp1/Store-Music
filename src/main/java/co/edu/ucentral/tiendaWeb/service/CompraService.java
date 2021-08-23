package co.edu.ucentral.tiendaWeb.service;

import java.util.List;

import co.edu.ucentral.tiendaWeb.model.Banco;
import co.edu.ucentral.tiendaWeb.model.Compras;

public interface CompraService{

	public List<Compras> ListarCompras();
	public void guardar(Compras cuenta);
}
