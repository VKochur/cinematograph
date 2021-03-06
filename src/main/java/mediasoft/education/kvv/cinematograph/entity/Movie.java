package mediasoft.education.kvv.cinematograph.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity(name = "Movie")
@Table(name = "movies")
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    // @NotNull
    @Column(nullable = false)
    private String name;

    private String description;

    // @NotNull
    // @Column(nullable = false)
    private String url;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new LinkedList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "movie_actor_relations",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors = new HashSet<>();

    @ManyToOne
    private User owner;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "movies")
    private Set<Tag> tags = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie otherMovie = (Movie) o;

        return (id != null) && (id.equals(otherMovie.getId()));
    }

    @Override
    public int hashCode() {
        return 31;
    }

    /**
     *
     * @param comment
     * @return true if added, false not
     */
    public boolean addComment(Comment comment) {
        if (comments.contains(comment)) {
            return false;
        } else {
            comments.add(comment);
            comment.updateMovie(this);
            return true;
        }
    }

    /**
     *
     * @param comment
     * @return true if removed, false not
     */
    public boolean removeComment(Comment comment) {
        if (!comments.contains(comment)) {
            return false;
        } else {
            comments.remove(comment);
            comment.removeMovie();
            return true;
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

    public void addTag(Tag tag) {
        if (tags.contains(tag)) {
            return;
        } else {
            tags.add(tag);
            tag.addMovie(this);
        }
    }

    public void removeTag(Tag tag) {
        if (!tags.contains(tag)) {
            return;
        } else {
            tags.remove(tag);
            tag.removeMovie(this);
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' + "}";
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


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

}
