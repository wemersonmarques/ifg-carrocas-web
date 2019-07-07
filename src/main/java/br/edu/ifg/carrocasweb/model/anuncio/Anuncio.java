package br.edu.ifg.carrocasweb.model.anuncio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import br.edu.ifg.carrocasweb.model.usuario.Usuario;
import br.edu.ifg.carrocasweb.model.veiculo.Veiculo;

@Entity
@Table(name = "anuncios")
public class Anuncio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 255)
	private String titulo;
	
	@CreationTimestamp
	private Date dataCadastro;
	
	@Column
	private Date dataExpiracao;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToOne
	private Veiculo veiculo;
	
	@Column(nullable = false)
	private boolean ativo = true;
	
	@Column
	private String telefone;

	@Transient
	private long idMarcaVeiculo;
	
	@Transient
	private String modeloVeiculo;
	
	@Transient
	private String motorizacaoVeiculo;
	
	@Transient
	private long quilometragemVeiculo;  

	public Anuncio() {}

	public Anuncio(String titulo, boolean ativo, String telefone) {
		this.titulo = titulo;
		this.ativo = ativo;
		this.telefone = telefone;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataExpiracao() {
		return dataExpiracao;
	}
	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public long getIdMarcaVeiculo() {
		return idMarcaVeiculo;
	}
	public void setIdMarcaVeiculo(long idMarcaVeiculo) {
		this.idMarcaVeiculo = idMarcaVeiculo;
	}
	public String getModeloVeiculo() {
		return modeloVeiculo;
	}
	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}
	public String getMotorizacaoVeiculo() {
		return motorizacaoVeiculo;
	}
	public void setMotorizacaoVeiculo(String motorizacaoVeiculo) {
		this.motorizacaoVeiculo = motorizacaoVeiculo;
	}
	public long getQuilometragemVeiculo() {
		return quilometragemVeiculo;
	}
	public void setQuilometragemVeiculo(long quilometragemVeiculo) {
		this.quilometragemVeiculo = quilometragemVeiculo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
