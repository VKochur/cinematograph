package mediasoft.education.kvv.cinematograph.dao.impl;

import mediasoft.education.kvv.cinematograph.dao.MovieDao;
import mediasoft.education.kvv.cinematograph.entity.Movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotSupportedException;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class MovieDaoImpl extends BasicDaoImpl<Movie> implements MovieDao {

    @PersistenceContext
    private EntityManager entityManager;

    public MovieDaoImpl() {
        super(Movie.class);
    }

    @Override
    public List<Movie> getByAtLeastOneActor(List<Long> actorIds) {
        String pql = "select m " +
                "from Movie m " +
                "join m.actors a " +
                "where a.id in :actorIds";
        Query query = entityManager.createQuery(pql);
        query.setParameter("actorIds", actorIds);
        return query.getResultList();
    }

    @Override
    public List<Movie> getByAtLeastOneTag(List<Long> tagIds) {
        String pql = "select distinct m " +
                "from Movie m " +
                "join m.tags t " +
                "where t.id in :tagIds";
        Query query = entityManager.createQuery(pql);
        query.setParameter("tagIds", tagIds);
        return query.getResultList();
    }

    @Override
    public List<Movie> getByAllTags(List<Long> tagIds) {
        /*
        String pql = "select m " +
                "from Movie m " +
                "join m.tags t " +
                "where not exists t.id not in (select)";
        Query query = entityManager.createQuery(pql);
        query.setParameter("actorIds", actorIds);
        return query.getResultList();
         */
        throw new IllegalStateException("not implemented yet");
    }
}
