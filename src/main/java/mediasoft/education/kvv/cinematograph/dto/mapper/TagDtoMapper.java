package mediasoft.education.kvv.cinematograph.dto.mapper;

import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.dto.TagDto;
import mediasoft.education.kvv.cinematograph.entity.Movie;
import mediasoft.education.kvv.cinematograph.entity.Tag;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class TagDtoMapper {

    public TagDto getDto(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        tagDto.setMovies(defineMoviesDto(tag.getMovies()));
        return tagDto;
    }

    private Set<MovieDto> defineMoviesDto(Set<Movie> movies) {
        Set<MovieDto> moviesDtos = new HashSet<>();
        movies.forEach(movie -> {
            MovieDto movieDto = new MovieDto();
            movieDto.setId(movie.getId());
            moviesDtos.add(movieDto);
        });
        return moviesDtos;
    }
}
