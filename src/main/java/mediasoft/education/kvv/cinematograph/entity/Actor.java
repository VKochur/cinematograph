package mediasoft.education.kvv.cinematograph.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "actors")
public class Actor implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String infoUrl;

    @ManyToMany(mappedBy = "actors",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private List<Movie> movies;

    public Actor() {
        movies = new LinkedList<>();
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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }


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
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", infoUrl='" + infoUrl + '\'' + "}";
        //+", movies's count = " + movies.size() + " }'";
    }
}
