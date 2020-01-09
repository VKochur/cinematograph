package mediasoft.education.kvv.cinematograph.dto.mapper;

import mediasoft.education.kvv.cinematograph.dto.*;
import mediasoft.education.kvv.cinematograph.entity.Actor;
import mediasoft.education.kvv.cinematograph.entity.Comment;
import mediasoft.education.kvv.cinematograph.entity.Movie;
import mediasoft.education.kvv.cinematograph.entity.Tag;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Stateless
public class MovieDtoMapper {

    public MovieDto getDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());

        if (movie.getOwner() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(movie.getOwner().getId());
        }

        movieDto.setDescription(movie.getDescription());
        movieDto.setUrl(movie.getUrl());

        movieDto.setActors(defineActorsDto(movie.getActors()));
        movieDto.setComments(defineCommentsDto(movie.getComments()));
        movieDto.setTags(defineTagsDto(movie.getTags()));

        return movieDto;
    }

    private Set<TagDto> defineTagsDto(Set<Tag> tags) {
        Set<TagDto> tagDtos = new HashSet<>();
        tags.forEach(tag -> {
            TagDto tagDto = new TagDto();
            tagDto.setId(tag.getId());
            tagDtos.add(tagDto);
        });
        return tagDtos;
    }

    private List<CommentDto> defineCommentsDto(List<Comment> comments) {
        List<CommentDto> commentDtos = new LinkedList<>();
        comments.forEach(comment -> {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDtos.add(commentDto);
        });
        return commentDtos;
    }

    private Set<ActorDto> defineActorsDto(Set<Actor> actors) {
        Set<ActorDto> actorDtos = new HashSet<>();
        actors.forEach(actor -> {
            ActorDto actorDto = new ActorDto();
            actorDto.setId(actor.getId());
            actorDtos.add(actorDto);
        });
        return actorDtos;
    }
}
