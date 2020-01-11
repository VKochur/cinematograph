package mediasoft.education.kvv.cinematograph.dto.mapper;

import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.dto.UserDto;
import mediasoft.education.kvv.cinematograph.entity.Comment;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class CommentDtoMapper implements DtoMapper<Comment, CommentDto>{

    @Override
    public CommentDto getDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());

        commentDto.setDateTime(comment.getDateTime());

        if (comment.getMovie() != null) {
            MovieDto movieDto = new MovieDto();
            movieDto.setId(comment.getMovie().getId());
            commentDto.setMovie(movieDto);
        }

        if (comment.getParent() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(comment.getParent().getId());
            commentDto.setOwner(userDto);
        }

        if (comment.getParent() != null) {
            CommentDto parentDto = new CommentDto();
            parentDto.setId(comment.getParent().getId());
            commentDto.setParent(parentDto);
        }

        commentDto.setChildren(defineCommentDto(comment.getChildren()));
        return commentDto;
    }

    @Override
    public Comment getEntityForCreation(CommentDto dataForCreateNewEntity) {
        Comment comment = new Comment();
        comment.setText(dataForCreateNewEntity.getText());
        return comment;
    }

    private List<CommentDto> defineCommentDto(List<Comment> children) {
        List<CommentDto> commentDtos = new LinkedList<>();
        children.forEach(child -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(child.getId());
            commentDtos.add(commentDto);
        });
        return commentDtos;
    }
}
