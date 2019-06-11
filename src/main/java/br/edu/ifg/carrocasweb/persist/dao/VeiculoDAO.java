package br.edu.ifg.carrocasweb.persist.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifg.carrocasweb.model.veiculo.Veiculo;

@Repository
@Transactional
public class VeiculoDAO extends AbstractGenericDAO {

	@Override
	public boolean existe(String param) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Veiculo> consultarPorModelo(String titulo) {
		List<Veiculo> veiculos;
		
		try {
			veiculos = entityManager.createQuery("SELECT a FROM Veiculo a WHERE lower(a.nome) LIKE lower(:nome)").setParameter("titulo", "%" + titulo + "%").getResultList();
		} catch (NoResultException e) {
			veiculos = null;
		}
		return veiculos;
	}

}
