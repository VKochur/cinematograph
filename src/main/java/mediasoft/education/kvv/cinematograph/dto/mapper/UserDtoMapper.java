package mediasoft.education.kvv.cinematograph.dto.mapper;

import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.dto.UserDto;
import mediasoft.education.kvv.cinematograph.entity.Comment;
import mediasoft.education.kvv.cinematograph.entity.Movie;
import mediasoft.education.kvv.cinematograph.entity.User;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class UserDtoMapper {

    public UserDto getDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setComments(defineCommentDto(user.getComments()));
        userDto.setAddedMoviesByUser(defineMoviesDto(user.getAddedMoviesByUser()));
        return userDto;
    }

    private List<MovieDto> defineMoviesDto(List<Movie> addedMoviesByUser) {
        List<MovieDto> movieDtos = new LinkedList<>();
        addedMoviesByUser.forEach(movie -> {
            MovieDto movieDto = new MovieDto();
            movieDto.setId(movie.getId());
            movieDtos.add(movieDto);
        });
        return movieDtos;
    }

    private List<CommentDto> defineCommentDto(List<Comment> comments) {
        List<CommentDto> commentDtos = new LinkedList<>();
        comments.forEach(comment -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDtos.add(commentDto);
        });
        return commentDtos;
    }
}
