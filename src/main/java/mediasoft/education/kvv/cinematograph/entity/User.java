package mediasoft.education.kvv.cinematograph.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String login;

    @OneToMany
    private List<Movie> addedMoviesByUser;

    @OneToMany
    private List<Comment> comments;

    public User() {
        addedMoviesByUser = new LinkedList<>();
        comments = new LinkedList<>();
    }

    public void addMovieAsLoadedByUser(Movie movie) {
        if (addedMoviesByUser.contains(movie)) {
            return;
        } else {
            addedMoviesByUser.add(movie);
            movie.updateOwner(this);
        }
    }

    public void removeMovieAsLoadedByUser(Movie movie) {
        if (!addedMoviesByUser.contains(movie)) {
            return;
        } else {
            addedMoviesByUser.remove(movie);
            movie.removeOwner();
        }
    }

    public void addComment(Comment comment) {
        if (comments.contains(comment)) {
            return;
        } else {
            comments.add(comment);
            comment.updateOwner(this);
        }
    }

    public void removeComment(Comment comment) {
        if (!comments.contains(comment)) {
            return;
        } else {
            comments.remove(comment);
            comment.removeOwner();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Movie> getAddedMoviesByUser() {
        return addedMoviesByUser;
    }

    public void setAddedMoviesByUser(List<Movie> addedMoviesByUser) {
        this.addedMoviesByUser = addedMoviesByUser;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
