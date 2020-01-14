package mediasoft.education.kvv.cinematograph.dao;

import mediasoft.education.kvv.cinematograph.entity.Movie;

import java.util.List;


public interface MovieDao extends BasicDao<Movie>{

    List<Movie> getByAtLeastOneActor(List<Long> actorIds);

    List<Movie> getByAllTags(List<Long> tagIds);
}
