package co.edu.ucentral.tiendaWeb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="banco")
public class Banco {
	@Id
	
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="idusuarios")
	private Usuario idusuarios;
	@Column(name = "saldo")
	private Double saldo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getIdusuarios() {
		return idusuarios;
	}
	public void setIdusuarios(Usuario idusuarios) {
		this.idusuarios = idusuarios;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		return "Banco [id=" + id + ", idusuarios=" + idusuarios + ", saldo=" + saldo + "]";
	}
	
	
	

}
