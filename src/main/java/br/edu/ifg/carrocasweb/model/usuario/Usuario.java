package br.edu.ifg.carrocasweb.model.usuario;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	private String sobrenome;
	
	@Column(nullable = false)
	private String email;
	
	@Column
	private Date dataCadastro;
	
	@Column
	private String telefoneContato;
	
	@OneToMany(mappedBy = "usuario")
	private List<Anuncio> anuncios;
	
	public Usuario() {
		
	}
	public Usuario (String login, String senha, String nome, String email) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String getTelefoneContato() {
		return telefoneContato;
	}
	
	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}
	
	public List<Anuncio> getAnuncios() {
		return anuncios;
	}
	
	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
}
