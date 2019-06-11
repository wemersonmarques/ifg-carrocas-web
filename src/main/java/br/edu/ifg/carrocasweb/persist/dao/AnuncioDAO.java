package br.edu.ifg.carrocasweb.persist.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;

@Repository
@Transactional
public class AnuncioDAO extends AbstractGenericDAO {

	@Override
	public boolean existe(String param) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Anuncio> consultarPorTitulo(String titulo) {
		List<Anuncio> anuncios;
		
		try {
			anuncios = entityManager.createQuery("SELECT a FROM Anuncio a WHERE lower(a.nome) LIKE lower(:nome)").setParameter("titulo", "%" + titulo + "%").getResultList();
		} catch (NoResultException e) {
			anuncios = null;
		}
		return anuncios;
	}

}
