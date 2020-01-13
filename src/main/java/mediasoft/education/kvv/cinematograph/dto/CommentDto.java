package mediasoft.education.kvv.cinematograph.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDto {

    private Long id;
    private String text;
    private LocalDateTime dateTime;
    private UserDto owner;
    private MovieDto movie;
    private CommentDto parent;

    private List<CommentDto> children;

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public CommentDto getParent() {
        return parent;
    }

    public void setParent(CommentDto parent) {
        this.parent = parent;
    }

    public List<CommentDto> getChildren() {
        return children;
    }

    public void setChildren(List<CommentDto> children) {
        this.children = children;
    }
}
