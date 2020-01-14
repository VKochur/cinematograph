package mediasoft.education.kvv.cinematograph.dao.impl;

import mediasoft.education.kvv.cinematograph.dao.TagDao;
import mediasoft.education.kvv.cinematograph.entity.Tag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TagDaoImpl extends BasicDaoImpl<Tag> implements TagDao {

    @PersistenceContext
    private EntityManager entityManager;

    public TagDaoImpl() {
        super(Tag.class);
    }

    @Override
    public List<Tag> findByIdsAndOrderByName(List<Long> existedTagIds) {
        String pql =
                "select t" +
                        " from Tag t" +
                        " where t.id in :ids" +
                        " order by t.name";
        TypedQuery<Tag> query = entityManager.createQuery(pql, Tag.class);
        query.setParameter("ids", existedTagIds);
        return query.getResultList();
    }

    @Override
    public List<Tag> findAllAndOrderByName(Boolean asc) {
        String pql =
                "select t" +
                        " from Tag t" +
                        " order by " +
                        " t.name " +
                        ((asc) ? "asc" : "desc");
        return entityManager.createQuery(pql).getResultList();
    }
}
