package br.edu.ifg.carrocasweb.persist.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AnuncioDAO extends AbstractGenericDAO {

	@Override
	public boolean existe(String param) {
		// TODO Auto-generated method stub
		return false;
	}

}
