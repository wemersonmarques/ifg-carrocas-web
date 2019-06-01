package br.edu.ifg.carrocasweb.persist.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifg.carrocasweb.model.usuario.Usuario;

@Repository
@Transactional
public class UsuarioDAO extends AbstractGenericDAO<Usuario>{

	public UsuarioDAO() {
		
	}
	
	@Override
	public boolean existe(String login) {		
		return consultarPorLogin(login) != null ? true : false;
	}
	
	public Usuario consultarPorLogin(String login) {
		Usuario u;
		
		try {
			u = (Usuario) entityManager.createQuery("SELECT u FROM Usuario u WHERE u.login = :login").setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			u = null;
		}
		return u;
	}
	
	
	public List<Usuario> consultarPorNome(String nome) {
		List<Usuario> usuarios;
		
		try {
			usuarios = entityManager.createQuery("SELECT u FROM Usuario u WHERE lower(u.nome) LIKE lower(:nome)").setParameter("nome", "%" + nome + "%").getResultList();
		} catch (NoResultException e) {
			usuarios = null;
		}
		return usuarios;
	}
	
}
