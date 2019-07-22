package br.edu.ifg.carrocasweb.persist.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;
import br.edu.ifg.carrocasweb.model.usuario.Usuario;

@Repository
@Transactional
public class AnuncioDAO<T> extends AbstractGenericDAO {

	@Override
	public boolean existe(String id) {
		
		Anuncio anuncio = entityManager.find(Anuncio.class, Long.valueOf(id));
		if (anuncio != null) {
			return true;
		}
		return false;

	}

	@Override
	public List<T> consultarTodos(Class clazz) {
		return (List<T>) entityManager.createQuery("SELECT u FROM " + clazz.getSimpleName() + " u WHERE u.ativo = true").getResultList();
	}

	public List<Anuncio> consultarPorTitulo(String titulo) {
		List<Anuncio> anuncios;

		try {
			anuncios = entityManager.createQuery("SELECT a FROM Anuncio a WHERE a.ativo = true AND lower(a.titulo) LIKE lower(:titulo)")
					.setParameter("titulo", "%" + titulo + "%").getResultList();
		} catch (NoResultException e) {
			anuncios = null;
		}
		return anuncios;
	}

	public List<Anuncio> consultarPorUsuario(Usuario usuario) {
		List<Anuncio> anuncios;

		try {
			anuncios = entityManager.createQuery("SELECT a FROM Anuncio a INNER JOIN Usuario u ON a.usuario = u WHERE a.ativo = true AND u.id = :id").setParameter("id", usuario.getId()).getResultList();
		} catch (NoResultException e) {
			anuncios = null;
		}
		return anuncios;
	}
	
	public List<Anuncio> consultarPorMarca(long idMarca) {
		List<Anuncio> anuncios;

		try {
			anuncios = entityManager.createQuery(
					"SELECT a FROM Anuncio a INNER JOIN Veiculo v ON a.veiculo = v INNER JOIN Marca m ON v.marca = m WHERE a.ativo = true AND m.id = :id")
					.setParameter("id", idMarca).getResultList();
		} catch (NoResultException e) {
			anuncios = null;
		}

		return anuncios;
	}
	
	public void inativar(Anuncio anuncio) {
		entityManager.createQuery("UPDATE Anuncio a SET a.ativo = false WHERE a.id = :id").setParameter("id", anuncio.getId()).executeUpdate();
	}

}
