package mediasoft.education.kvv.cinematograph.service;

import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface MovieService {

    /**
     * create new movie
     * @param movie container about new name, description, url
     * @return created movie with specific info in db
     */
    MovieDto create(MovieDto movie);

    /**
     * update info
     * @param existedMovieId movie's key in db
     * @param newData container about new name, description, url
     * @return updated movie
     * @throws NoSuchElementException if movie not existed
     */
    MovieDto updateInfo(Long existedMovieId, MovieDto newData);

    /**
     * add comment
     * @param existedMovieId movie's key in db
     * @param commentDto container info about text
     * @return created comment's dto
     * @throws NoSuchElementException if movie not existed
     */
    CommentDto addComment(Long existedMovieId, CommentDto commentDto);

    /**
     * delete comment and sub comments
     * @param existedMovieId movie's key in db.
     * @param commentId comment's key in db.
     * @return deleted comment's dto
     * @throws NoSuchElementException if movie not existed
     * @throws IllegalArgumentException if specific movie doesn't contain specific comment
     */
    CommentDto removeCommentById(Long existedMovieId, Long commentId);

    /**
     * add actor to movie
     * @param existedMovieId
     * @param existedActorId
     * @return updated movie
     * @throws NoSuchElementException if movie or actor not existed
     */
    MovieDto addActorById(Long existedMovieId, Long existedActorId);

    /**
     * remove actor from movie
     * @param existedMovieId
     * @param existedActorId
     * @return updated movie
     * @throws NoSuchElementException if movie not existed
     */
    MovieDto removeActorById(Long existedMovieId, Long existedActorId);

    /**
     * add tags to movie
     * @param existedMovieId
     * @param existedTagIds tags' ids
     * @return
     * @throws NoSuchElementException if movie not existed
     */
    MovieDto addTagsByIds(Long existedMovieId, List<Long> existedTagIds);

    /**
     * remove tags from movie
     * @param existedMovieId
     * @param existedTagIds tags' ids
     * @return
     * @throws NoSuchElementException if movie not existed
     */
    MovieDto removeTagsByIds(Long existedMovieId, List<Long> existedTagIds);

    /**
     * get movie by key in db
     * @param id
     * @return
     * @throws NoSuchElementException if movie not found
     */
    MovieDto getById(Long id);

    /**
     * get all movies from db
     * @return
     */
    List<MovieDto> findAll();

    /**
     * remove movie by key
     * @param id
     * @return deleted movie
     * @throws NoSuchElementException if movies not found
     */
    MovieDto deleteById(Long id);

    /**
     * get movies that have name like specific ignore case: %name%
     *
     * @param name
     * @return list ordered by name
     */
    List<MovieDto> getBySimilarName(String name);

    /**
     * list movies, there movie contains at least one specific actor
     * @param actorIds
     * @return
     */
    List<MovieDto> getByAtLeastOneActor(List<Long> actorIds);

    /**
     * list movies, there movie contains all specific tags
     * @param tagIds
     * @return
     */
    List<MovieDto> getByAllTag(List<Long> tagIds);
}
