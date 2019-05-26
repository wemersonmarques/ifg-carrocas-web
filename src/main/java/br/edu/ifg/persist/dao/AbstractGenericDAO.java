package br.edu.ifg.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractGenericDAO<T>{

	protected static EntityManager entityManager;
	
	public AbstractGenericDAO () {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("carrocasweb");
		entityManager = factory.createEntityManager();
	}

	public void salvar(T t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	public T consultarPorId(Class<T> clazz, long id) {
		T t = (T) entityManager.find(clazz, id);
		return t;
	}

	public List<T> consultarTodos(Class clazz) {
		return (List<T>) entityManager.createQuery("SELECT u FROM " + clazz.getSimpleName() + " u").getResultList();
	}
	public void deletar(T t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	public void atualizar(T t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}
	
	public abstract boolean existe (String param);

}
