package mediasoft.education.kvv.cinematograph.dto;

import mediasoft.education.kvv.cinematograph.entity.Movie;

import java.util.Set;

public class TagDto {

    private Long id;
    private String name;

    private Set<MovieDto> movies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieDto> movies) {
        this.movies = movies;
    }
}
