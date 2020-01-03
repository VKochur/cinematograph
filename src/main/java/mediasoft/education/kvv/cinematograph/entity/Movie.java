package mediasoft.education.kvv.cinematograph.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private String url;

    @OneToMany
    private List<Comment> comments;

    @ManyToMany
    private List<Actor> actors;

    @ManyToOne
    private User owner;

    public Movie() {
        comments = new LinkedList<>();
        actors = new LinkedList<>();
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

    public void addComment(Comment comment) {
        if (comments.contains(comment)) {
            return;
        } else {
            comments.add(comment);
            comment.updateMovie(this);
        }
    }

    public void removeComment(Comment comment) {
        if (!comments.contains(comment)) {
            return;
        } else {
            comments.remove(comment);
            comment.removeMovie();
        }
    }

    public void addActor(Actor actor) {
        if (actors.contains(actor)) {
            return;
        } else {
            actors.add(actor);
            actor.addMovies(this);
        }
    }

    public void removeActor(Actor actor) {
        if (!actors.contains(actor)) {
            return;
        } else {
            actors.remove(actor);
            actor.removeMovie(this);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void updateOwner(User user) {
        if (!Objects.equals(owner, user)) {
            removeOwner();
            owner = user;
            user.addMovieAsLoadedByUser(this);
        }
    }

    public void removeOwner() {
        if (owner != null) {
            owner.removeMovieAsLoadedByUser(this);
            owner = null;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}