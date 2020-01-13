package mediasoft.education.kvv.cinematograph.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Actor")
@Table(name = "actors")
public class Actor implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "info_url")
    private String infoUrl;

    @ManyToMany(mappedBy = "actors",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private Set<Movie> movies = new HashSet<>();

    public void addMovies(Movie movie) {
        if (movies.contains(movie)) {
            return;
        } else {
            movies.add(movie);
            movie.addActor(this);
        }
    }

    public void removeMovie(Movie movie) {
        if (!movies.contains(movie)) {
            return;
        } else {
            movies.remove(movie);
            movie.removeActor(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor otherActor = (Actor) o;

        return (id != null) && id.equals(otherActor.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", infoUrl='" + infoUrl + '\'' + "}";
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

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

}
