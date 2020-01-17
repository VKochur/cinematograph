package mediasoft.education.kvv.cinematograph.servlet.util;


import mediasoft.education.kvv.cinematograph.dto.ActorDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.dto.TagDto;
import mediasoft.education.kvv.cinematograph.service.ActorService;
import mediasoft.education.kvv.cinematograph.service.MovieService;
import mediasoft.education.kvv.cinematograph.service.TagService;
import mediasoft.education.kvv.cinematograph.util.Pair;

import javax.ejb.EJBException;
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
    private final String ACTOR_NAME_PARAM = "actor_name_contains";
    private final String TAGS_PARAM = "tags";

    @Inject
    private MovieService movieService;

    @Inject
    private TagService tagService;

    @Inject
    private ActorService actorService;

    //todo: simplify (if.., several return)
    @Override
    public Pair<String, List<MovieDto>> defineMovies(HttpServletRequest req) {
        StringBuilder info = new StringBuilder();

        String[] stringsMovieIds = req.getParameterMap().get(ID_MOVIE_PARAM);
        if (stringsMovieIds != null) {
            String idMovie = stringsMovieIds[0];
            try {
                long id =  Long.parseLong(idMovie);
                MovieDto byId = movieService.getById(id);
                info.append("movies by id = " + idMovie);
                return new Pair(info.toString(), Arrays.asList(byId));
            } catch (NumberFormatException e) {
                //wrong data
                return new Pair<>("wrong format movie's id = " + idMovie, Collections.EMPTY_LIST);
            } catch (NoSuchElementException e) {
                info.append("not found movie by id = " + idMovie);
            } catch (EJBException e) {
                 if (e.getCausedByException() instanceof NoSuchElementException) {
                     return new Pair<>("not found movie by id = " + idMovie, Collections.EMPTY_LIST);
                 } else {
                     throw e;
                 }
            }
        }

        String[] movieNames = req.getParameterMap().get(MOVIE_NAME_PARAM);
        if (movieNames != null) {
            String movieName = movieNames[0];
            List<MovieDto> bySimilarName = movieService.getBySimilarName(movieName);
            info.append("\n" + "movies, whose name contains '" + movieName + "'");
            return new Pair(info.toString(), bySimilarName);
        }


        String[] actorNames = req.getParameterMap().get(ACTOR_NAME_PARAM);
        if (actorNames != null) {
            String actorName = actorNames[0];
            List<ActorDto> bySimilarName = actorService.getBySimilarName(actorName);
            List<Long> actorIds = new LinkedList<>();
            bySimilarName.forEach(actorDto -> actorIds.add(actorDto.getId()));
            info.append("\n " + "movies, whose actor's name's contains '" + actorName +"'");
            List<MovieDto> byAtLeastOneActor = movieService.getByAtLeastOneActor(actorIds);
            return new Pair<>(info.toString(), byAtLeastOneActor);
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


    private Pair<String, List<MovieDto>> defaulListMovies(HttpServletRequest req) {
        return new Pair("No movies.\n" +
                " Can't process query: '" + req.getQueryString() + "'", Collections.EMPTY_LIST);
    }
}
