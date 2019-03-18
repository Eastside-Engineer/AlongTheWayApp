package finalproject.alongtheway.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoutesDao {

	@PersistenceContext
	private EntityManager em;

	public List<Route> findAll() {
		return em.createQuery("FROM Route", Route.class).getResultList();

	}

	public Route findById(Long id) {
		return em.find(Route.class, id);
	}

	public void create(Route route) {
		em.persist(route);
	}

	public void update(Route route) {
		em.merge(route);
	}

	public void delete(Long id) {
		em.remove(em.getReference(Route.class, id));
	}
}
