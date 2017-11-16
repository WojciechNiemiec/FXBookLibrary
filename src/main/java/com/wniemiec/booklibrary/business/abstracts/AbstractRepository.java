package com.wniemiec.booklibrary.business.abstracts;

import com.wniemiec.booklibrary.business.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class having basic search utilities
 *
 * @param <E> Entity class
 * @param <K> Primary key class
 * @param <T> Default target DTO class
 */
public abstract class AbstractRepository<E, K extends Serializable, T> {

    private final static Integer MAX_RESULTS = 100;

    private EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void save(E object) {
        invokeInSession(session -> session.save(object));
    }

    public void saveAll(Collection<E> objects) {
        invokeInSession(session -> objects.forEach(session::save));
    }

    public void update(E object) {
        invokeInSession(session -> session.persist(object));
    }

    public abstract void delete(K id);

    public final List<T> findAll(Specification<E> specification) {
        return findAll(specification, MAX_RESULTS);
    }

    public final List<T> findAll(Specification<E> specification, Integer maxResults) {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(getTargetDTOClass());
            Root<E> root = query.from(getEntityClass());

            query.select(getConstruct(root, query, criteriaBuilder));
                    //.where(specification.toPredicate(root, query, criteriaBuilder));

            return entityManager.createQuery(query)
                    .setMaxResults(maxResults)
                    .getResultList();

        } finally {
            transaction.commit();
            session.close();
        }
    }

    protected abstract Class<E> getEntityClass();

    protected abstract Class<T> getTargetDTOClass();

    public final void invokeInSession(Consumer<Session> body) {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();

        body.accept(session);

        transaction.commit();
        session.close();
    }

    private Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public abstract CompoundSelection<T> getConstruct(Root<E> root, CriteriaQuery<T> query, CriteriaBuilder cb);
}