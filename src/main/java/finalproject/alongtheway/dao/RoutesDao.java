//package finalproject.alongtheway.dao;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//
//
//@Repository
//@Transactional
//public class RoutesDao {
//	
//	@PersistenceContext
//	private EntityManager em;
//
//	
//	public List<Routes> findAll() {
//		return em.createQuery("FROM Item", Routes.class).getResultList();
//
//	}
//	public Routes findById(Long id) {
//		return em.find(Routes.class, id);
//	}
//
//	public void create(Routes routes) {
//		em.persist(routes);
//	}
//	
//	public void update(Routes routes) {
//		em.merge(routes);
//	}
//	
//	public void delete(Long id) {
//		em.remove(em.getReference(Item.class, id));
//	}
//}
//
//}
