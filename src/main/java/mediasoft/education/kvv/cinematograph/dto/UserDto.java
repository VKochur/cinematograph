package mediasoft.education.kvv.cinematograph.dto;

import java.util.List;

public class UserDto {

    private Long id;
    private String login;

    private List<MovieDto> addedMoviesByUser;
    private List<CommentDto> comments;

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

    public List<MovieDto> getAddedMoviesByUser() {
        return addedMoviesByUser;
    }

    public void setAddedMoviesByUser(List<MovieDto> addedMoviesByUser) {
        this.addedMoviesByUser = addedMoviesByUser;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
