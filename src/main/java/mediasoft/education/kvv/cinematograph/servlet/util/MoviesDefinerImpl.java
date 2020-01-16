package mediasoft.education.kvv.cinematograph.servlet.util;


import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.dto.TagDto;
import mediasoft.education.kvv.cinematograph.service.MovieService;
import mediasoft.education.kvv.cinematograph.service.TagService;
import mediasoft.education.kvv.cinematograph.util.Pair;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
@Stateless
public class MoviesDefinerImpl implements MoviesDefiner {

    private final String ID_MOVIE_PARAM = "movie_id";
    private final String MOVIE_NAME_PARAM = "movie_name_contains";
    private final String TAGS_PARAM = "tags";

    @Inject
    private MovieService movieService;

    @Inject
    private TagService tagService;

    //todo: simplify (if..)
    @Override
    public Pair<String, List<MovieDto>> defineMovies(HttpServletRequest req) {
        StringBuilder info = new StringBuilder();

        String[] stringsMovieIds = req.getParameterMap().get(ID_MOVIE_PARAM);
        if (stringsMovieIds != null) {
            String idMovie = stringsMovieIds[0];
            try {
                long id = Long.parseLong(idMovie);
                MovieDto byId = movieService.getById(id);
                info.append("movies by id = " + idMovie);
                return new Pair(info.toString(), Arrays.asList(byId));
            } catch (NumberFormatException e) {
                //wrong data, do nothing.
                info.append("wrong format movie's id = " + idMovie);
            } catch (NoSuchElementException e) {
                info.append("not found movie by id = " + idMovie);
            }
        }

        String[] movieNames = req.getParameterMap().get(MOVIE_NAME_PARAM);
        if (movieNames != null) {
            String movieName = movieNames[0];
            List<MovieDto> bySimilarName = movieService.getBySimilarName(movieName);
            info.append("\n" + "movies, whose name contains '" + movieName + "'");
            return new Pair(info.toString(), bySimilarName);
        }


        String[] tagsParam = req.getParameterMap().get(TAGS_PARAM);
        if (tagsParam != null) {
            String DELIMETER = ",";
            String[] tags = tagsParam[0].split(DELIMETER);
            List<Long> tagIds = new LinkedList<>();
            info.append("try get by tags:" + Arrays.toString(tags)).append("\n");
            for (String tagName : tags) {
                try {
                    TagDto byName = tagService.getByName(tagName);
                    tagIds.add(byName.getId());
                    info.append("defined tag = ").append(byName.getName()).append("\n");
                } catch (NoSuchElementException e) {
                    //not found by tag's name
                    //see next
                }
            }

            List<MovieDto> byAtLeastOneTag = movieService.getByAtLeastOneTag(tagIds);
            return new Pair<>(info.toString(), byAtLeastOneTag);
        }

        //and default
        return defaulListMovies(req);
    }


    private Pair<String,List<MovieDto>> defaulListMovies(HttpServletRequest req) {
        return new Pair("No movies.\n" +
                " Can't process query: '" +  req.getQueryString()+ "'", Collections.EMPTY_LIST);
    }
}
