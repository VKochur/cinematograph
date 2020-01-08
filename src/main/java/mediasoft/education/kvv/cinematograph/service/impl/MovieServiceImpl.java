package mediasoft.education.kvv.cinematograph.service.impl;

import mediasoft.education.kvv.cinematograph.dao.MovieDao;
import mediasoft.education.kvv.cinematograph.entity.Movie;
import mediasoft.education.kvv.cinematograph.service.MovieService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Stateless
public class MovieServiceImpl implements MovieService {

    @Inject
    private MovieDao movieDao;

    @Override
    public Movie create(Movie movie) {
        return movieDao.create(movie);
    }

    @Override
    public Movie update(Movie movie) {
        return movieDao.update(movie);
    }

    @Override
    public Movie getById(Long id) {
        Movie byId = movieDao.getById(id);
        if (byId != null) {
            //get actors
            byId.getActors().size();
            return byId;
        } else {
            throw new IllegalArgumentException("not found movie by id = " + id);
        }
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> list = movieDao.getList();
        //get actors
        list.forEach(movie -> movie.getActors().size());
        return list;
    }

    @Override
    public Movie deleteById(Long id) {
        Movie byId = this.getById(id);
        return movieDao.delete(byId);
    }
}
