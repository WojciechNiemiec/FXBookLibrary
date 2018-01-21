package com.wniemiec.booklibrary.business.abstracts;

import com.wniemiec.booklibrary.business.book.Book;
import com.wniemiec.booklibrary.business.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public void saveOrUpdate(E object) {
        invokeInSession(session -> session.saveOrUpdate(object));
    }

    public void delete(K id) {
        invokeInSession((session) -> session.delete(session.load(getEntityClass(), id)));
    }

    public final E findById(K id) {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();

        try {
            return session.get(getEntityClass(), id);
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public final List<T> findAll() {
        return findAll(Specifications.where((root, criteriaQuery, criteriaBuilder) ->
                        criteriaBuilder.conjunction()), MAX_RESULTS);
    }

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

            query.select(getConstruct(root, criteriaBuilder))
                    .distinct(true)
                    .where(specification.toPredicate(root, query, criteriaBuilder));

            List<T> result = entityManager.createQuery(query)
                    .setMaxResults(maxResults)
                    .getResultList();

            return decorateResults(result, entityManager);

        } finally {
            transaction.commit();
            session.close();
        }
    }

    private Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    private void invokeInSession(Consumer<Session> body) {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();

        body.accept(session);

        transaction.commit();
        session.close();
    }

    protected List<T> decorateResults(List<T> undecorated, EntityManager entityManager) {
        return undecorated;
    }

    protected abstract Class<E> getEntityClass();

    protected abstract Class<T> getTargetDTOClass();

    public abstract CompoundSelection<T> getConstruct(Root<E> root, CriteriaBuilder cb);
}