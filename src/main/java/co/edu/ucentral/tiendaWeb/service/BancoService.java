package co.edu.ucentral.tiendaWeb.service;

import java.util.List;

import org.springframework.data.domain.Example;

import co.edu.ucentral.tiendaWeb.model.Banco;

public interface BancoService {

	public List<Banco> buscarByExample(Example<Banco> example);

	public Banco buscarPorId(Integer idProducto);

	public List<Banco> ListarCuentas();

	public void guardar(Banco cuenta);

}
