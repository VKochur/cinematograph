package mediasoft.education.kvv.cinematograph.dao.impl;

import mediasoft.education.kvv.cinematograph.dao.MovieDao;
import mediasoft.education.kvv.cinematograph.entity.Movie;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;


@Stateless
public class MovieDaoImpl extends BasicDaoImpl<Movie> implements MovieDao {
    public MovieDaoImpl() {
        this(Movie.class);
    }

    public MovieDaoImpl(Class<Movie> entityClass) {
        super(entityClass);
    }
}
