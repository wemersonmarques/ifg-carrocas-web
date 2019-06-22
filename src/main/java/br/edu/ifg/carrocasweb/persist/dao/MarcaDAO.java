package br.edu.ifg.carrocasweb.persist.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifg.carrocasweb.model.veiculo.Marca;

@Repository
@Transactional
public class MarcaDAO extends AbstractGenericDAO{

	@Override
	public boolean existe(String param) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Marca> consultarPorNome(String marca) {
		List<Marca> marcas;
		
		try {
			marcas = entityManager.createQuery("SELECT u FROM Marca u WHERE lower(u.nome) LIKE lower(:nome)").setParameter("nome", "%" + marca + "%").getResultList();
		} catch (NoResultException e) {
			marcas = null;
		}
		return marcas;
	}

}
