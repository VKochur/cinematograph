package mediasoft.education.kvv.cinematograph.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tags")
public class Tag implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, updatable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "tag_movie_relations",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;

    public Tag() {
        movies = new HashSet<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
