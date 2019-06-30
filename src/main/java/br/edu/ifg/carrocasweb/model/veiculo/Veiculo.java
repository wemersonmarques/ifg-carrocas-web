package br.edu.ifg.carrocasweb.model.veiculo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "veiculos")
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	private Marca marca;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column
	private long quilometragem;
	
	@Column
	private String motorizacao;
	
	@Transient
	private String marcaIddd;
	
	public Marca getMarca() {
		return marca;
	}
	
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public long getQuilometragem() {
		return quilometragem;
	}
	
	public void setQuilometragem(long quilometragem) {
		this.quilometragem = quilometragem;
	}
	public String getMotorizacao() {
		return motorizacao;
	}
	
	public void setMotorizacao(String motorizacao) {
		this.motorizacao = motorizacao;
	}
	
	public String getMarcaIddd() {
		return this.marcaIddd;
	}
	
	public void setMarcaIddd(String marcaId) {
		this.marcaIddd = marcaId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
