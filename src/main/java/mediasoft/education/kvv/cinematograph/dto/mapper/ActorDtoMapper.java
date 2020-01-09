package mediasoft.education.kvv.cinematograph.dto.mapper;

import mediasoft.education.kvv.cinematograph.dto.ActorDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.entity.Actor;
import mediasoft.education.kvv.cinematograph.entity.Movie;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class ActorDtoMapper {

    public ActorDto getDto(Actor actor) {
        ActorDto actorDto = new ActorDto();
        actorDto.setId(actor.getId());
        actorDto.setName(actor.getName());
        actorDto.setInfoUrl(actor.getInfoUrl());
        actorDto.setMovies(defineMoviesDto(actor.getMovies()));
        return actorDto;
    }

    private Set<MovieDto> defineMoviesDto(Set<Movie> movies) {
        Set<MovieDto> movieDtos = new HashSet<>();
        movies.forEach(movie -> {
            MovieDto movieDto = new MovieDto();
            movieDto.setId(movie.getId());
            movieDtos.add(movieDto);
        });
        return movieDtos;
    }

}
