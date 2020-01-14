package mediasoft.education.kvv.cinematograph.dao.impl;

import mediasoft.education.kvv.cinematograph.dao.BasicDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BasicDaoImpl<T> implements BasicDao<T> {
    private final Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public BasicDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> getList() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T update(T entity) {
        T merge = em.merge(entity);
        return merge;
    }

    @Override
    public T delete(T entity) {
        em.remove(entity);
        return entity;
    }

    @Override
    public T getById(long id) {
        return em.find(entityClass, id);
    }

    @Override
    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public List<T> findWhereFieldLikeAsSpecificAndOrderByOther(String fieldName, String fieldValue, boolean caseIgnore, String forSortFieldName, boolean asc) {
        String clazzName = entityClass.getSimpleName();

        String from = " select e  from " + clazzName + " e ";
        String where = ((caseIgnore)? ("where UPPER(e."+fieldName+")") : ("where e." + fieldName)) + " like :fieldValue";
        String order = " order by e." + forSortFieldName + " " + ((asc)? "asc" : "desc");

        Query query = em.createQuery(from + where + order);

        query.setParameter("fieldValue", (caseIgnore)? fieldValue.toUpperCase() : fieldValue);
        return query.getResultList();
    }
}


