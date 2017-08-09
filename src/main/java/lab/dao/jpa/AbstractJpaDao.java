package lab.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class AbstractJpaDao {

	@SuppressWarnings("WeakerAccess")
	protected EntityManagerFactory emf;

	protected <T> T mapEntityManager(Function<EntityManager, T> entityManagerMapper) {
        EntityManager em = null;
        try {
            return entityManagerMapper.apply(
                    em = emf.createEntityManager());
        } finally {
            if (em != null)
                em.close();
        }
    }

    protected void withEntityManager(Consumer<EntityManager> entityManagerConsumer) {
        EntityManager em = emf.createEntityManager();
        entityManagerConsumer.accept(em);
        em.close();
    }

    protected void withEntityManagerInTransaction(Consumer<EntityManager> entityManagerConsumer) {
        withEntityManager(entityManager -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManagerConsumer.accept(entityManager);
            transaction.commit();
        });
    }

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

}