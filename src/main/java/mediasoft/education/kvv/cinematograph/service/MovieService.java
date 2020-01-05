package mediasoft.education.kvv.cinematograph.service;

import mediasoft.education.kvv.cinematograph.entity.Movie;

import java.util.List;

public interface MovieService {

    Movie create(Movie movie);

    Movie update(Movie movie);

    Movie getById(Long id);

    List<Movie> findAll();

    Movie deleteById(Long id);
}
