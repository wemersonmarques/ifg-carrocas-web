package br.edu.ifg.carrocasweb.persist.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;
import br.edu.ifg.carrocasweb.model.usuario.Usuario;

@Repository
@Transactional
public class AnuncioDAO extends AbstractGenericDAO {

	@Override
	public boolean existe(String id) {
		
		Anuncio anuncio = entityManager.find(Anuncio.class, Long.valueOf(id));
		if (anuncio != null) {
			return true;
		}
		return false;

	}

	public List<Anuncio> consultarPorTitulo(String titulo) {
		List<Anuncio> anuncios;

		try {
			anuncios = entityManager.createQuery("SELECT a FROM Anuncio a WHERE lower(a.titulo) LIKE lower(:titulo)")
					.setParameter("titulo", "%" + titulo + "%").getResultList();
		} catch (NoResultException e) {
			anuncios = null;
		}
		return anuncios;
	}

	public List<Anuncio> consultarPorUsuario(Usuario usuario) {
		List<Anuncio> anuncios;

		try {
			anuncios = entityManager.createQuery("SELECT a FROM Anuncio a INNER JOIN Usuario u ON a.usuario = u WHERE u.id = :id").setParameter("id", usuario.getId()).getResultList();
		} catch (NoResultException e) {
			anuncios = null;
		}
		return anuncios;
	}
	
	public List<Anuncio> consultarPorMarca(long idMarca) {
		List<Anuncio> anuncios;

		try {
			anuncios = entityManager.createQuery(
					"SELECT a FROM Anuncio a INNER JOIN Veiculo v ON a.veiculo = v INNER JOIN Marca m ON v.marca = m WHERE m.id = :id")
					.setParameter("id", idMarca).getResultList();
		} catch (NoResultException e) {
			anuncios = null;
		}

		return anuncios;
	}
	
	

}
