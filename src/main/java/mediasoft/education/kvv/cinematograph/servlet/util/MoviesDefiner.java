package mediasoft.education.kvv.cinematograph.servlet.util;


import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.util.Pair;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Solves which movies are under consideration, use request's data
 */
public interface MoviesDefiner {

    Pair<String, List<MovieDto>> defineMovies(HttpServletRequest req);
}
