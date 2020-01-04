package mediasoft.education.kvv.cinematograph.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    private LocalDateTime dateTime;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Comment parent;

    @OneToMany
    private List<Comment> children;

    public Comment() {
        children = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void updateMovie(Movie movie) {
        if (Objects.equals(this.movie, movie)) {
            return;
        } else {
            removeMovie();
            setMovie(movie);
            movie.addComment(this);
        }
    }

    public void removeMovie() {
        if (movie != null) {
            movie.removeComment(this);
            setMovie(null);
        }
    }

    public void addChild(Comment childComment) {
        if (children.contains(childComment)) {
            return;
        } else {
            children.add(childComment);
            childComment.updateParent(this);
        }
    }

    public void updateParent(Comment parentComment) {
        if (Objects.equals(parent, parentComment)) {
            return;
        } else {
            if (parent != null) {
                parent.removeChild(this);
            }
            parent = parentComment;
            parent.addChild(this);
        }
    }

    public void removeChild(Comment childComponent) {
        if (!children.contains(childComponent)) {
            return;
        } else {
            children.remove(childComponent);
            childComponent.removeParent();
        }
    }

    public void removeParent() {
        if (parent != null) {
            parent.removeChild(this);
            parent = null;
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void updateOwner(User user) {
        if (Objects.equals(owner, user)) {
            return;
        } else {
            removeOwner();
            owner = user;
            user.addComment(this);
        }
    }

    public void removeOwner() {
        if (owner != null) {
            owner.removeComment(this);
            owner = null;
        }
    }
}
