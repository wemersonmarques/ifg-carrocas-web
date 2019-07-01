package br.edu.ifg.carrocasweb.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;

@Repository
@Transactional
public abstract class AbstractGenericDAO<T>{

	@PersistenceContext
	protected EntityManager entityManager;

	public void salvar(T t) {
		entityManager.persist(t);
	}

	public T consultarPorId(Class<T> clazz, long id) {
		T t = (T) entityManager.find(clazz, id);
		return t;
	}

	public List<T> consultarTodos(Class clazz) {
		if (clazz.equals(Anuncio.class)) {
			return (List<T>) entityManager.createQuery("SELECT u FROM " + clazz.getSimpleName() + " u WHERE u.ativo = true").getResultList();	
		}
		return (List<T>) entityManager.createQuery("SELECT u FROM " + clazz.getSimpleName() + " u").getResultList();
	}
	
	public void deletar(T t) {
		entityManager.remove(t);
	}

	public void atualizar(T t) {
		entityManager.merge(t);
	}
	
	public abstract boolean existe (String param);

}
