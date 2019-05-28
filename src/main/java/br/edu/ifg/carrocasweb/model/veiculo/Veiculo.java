package br.edu.ifg.model.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "veiculos")
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Marca marca;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column
	private long quilometragem;
	
	@Column
	private String motorizacao;
	
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
	
}
