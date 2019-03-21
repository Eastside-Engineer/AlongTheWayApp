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
		if (route.getStops() != null) {
			for (Stop stop : route.getStops()) {
				stop.setId(null);
				stop.setRoute(route);
			}

		}
		em.persist(route);

	}

	public void update(Route route) {
		for (int i = 0; i < route.getStops().size(); i++) {
			Stop stop = route.getStops().get(i);
			if (stop.getId() != null) {
				stop = em.find(Stop.class, stop.getId());
				route.getStops().set(i, stop);
			} else {
				stop.setRoute(route);
			}
		}
		em.merge(route);
	}

	public void delete(Long id) {
		em.remove(em.getReference(Route.class, id));
	}
}
