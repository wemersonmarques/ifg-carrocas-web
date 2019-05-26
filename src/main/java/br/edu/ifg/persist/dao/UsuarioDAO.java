package br.edu.ifg.persist.dao;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import br.edu.ifg.model.usuario.Usuario;

@Repository
public class UsuarioDAO extends AbstractGenericDAO{

	
	public UsuarioDAO() {
		
	}
	
	@Override
	public boolean existe(String login) {		
		return consultarPorLogin(login) != null;
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
}
