package mediasoft.education.kvv.cinematograph.dao.impl;

import mediasoft.education.kvv.cinematograph.dao.MovieDao;
import mediasoft.education.kvv.cinematograph.entity.Movie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
}
