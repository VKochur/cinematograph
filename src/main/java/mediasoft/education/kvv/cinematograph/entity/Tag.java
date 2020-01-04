package mediasoft.education.kvv.cinematograph.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    private List<Movie> movies;

    public Tag() {
        movies = new LinkedList<>();
    }

    public void addMovie(Movie movie) {
        if (movies.contains(movie)) {
            return;
        } else {
            movies.add(movie);
            movie.addTag(this);
        }
    }

    public void removeMovie(Movie movie) {
        if (!movies.contains(movie)) {
            return;
        } else {
            movies.remove(movie);
            movie.removeTag(this);
        }
    }

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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
