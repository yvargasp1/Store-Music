package co.edu.ucentral.tiendaWeb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="compras")

public class Compras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@OneToOne
	@JoinColumn(name="idusuario")
	private Usuario idusuario;
	@OneToOne
	@JoinColumn(name="idproductos")
	private Producto idproductos;
	
	private Date fecha;
	
	
	public Usuario getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Usuario idusuario) {
		this.idusuario = idusuario;
	}
	public Producto getIdproductos() {
		return idproductos;
	}
	public void setIdproductos(Producto idproductos) {
		this.idproductos = idproductos;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Compras [id=" + id + ", idusuario=" + idusuario + ", idproductos=" + idproductos + ", fecha=" + fecha
				+ "]";
	}
	

	
	
	
	
	
	
	
}
