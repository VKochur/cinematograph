package mediasoft.education.kvv.cinematograph.service.impl;

import mediasoft.education.kvv.cinematograph.dao.ActorDao;
import mediasoft.education.kvv.cinematograph.dao.CommentDao;
import mediasoft.education.kvv.cinematograph.dao.MovieDao;
import mediasoft.education.kvv.cinematograph.dao.TagDao;
import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.dto.TagDto;
import mediasoft.education.kvv.cinematograph.dto.mapper.DtoMapper;
import mediasoft.education.kvv.cinematograph.dto.mapper.MovieDtoMapper;
import mediasoft.education.kvv.cinematograph.entity.Actor;
import mediasoft.education.kvv.cinematograph.entity.Comment;
import mediasoft.education.kvv.cinematograph.entity.Movie;
import mediasoft.education.kvv.cinematograph.entity.Tag;
import mediasoft.education.kvv.cinematograph.service.CommentService;
import mediasoft.education.kvv.cinematograph.service.MovieService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Stateless
public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao;

    private MovieDtoMapper movieDtoMapper;

    private ActorDao actorDao;

    private CommentService commentService;

    private CommentDao commentDao;

    private DtoMapper<Comment, CommentDto> commentDtoMapper;

    private TagDao tagDao;

    private DtoMapper<Tag, TagDto> tagTagDtoDtoMapper;

    @Inject
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Inject
    public void setMovieDtoMapper(MovieDtoMapper movieDtoMapper) {
        this.movieDtoMapper = movieDtoMapper;
    }

    @Inject
    public void setActorDao(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Inject
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Inject
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Inject
    public void setCommentDtoMapper(DtoMapper<Comment, CommentDto> commentDtoMapper) {
        this.commentDtoMapper = commentDtoMapper;
    }

    @Inject
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Inject
    public void setTagTagDtoDtoMapper(DtoMapper<Tag, TagDto> tagTagDtoDtoMapper) {
        this.tagTagDtoDtoMapper = tagTagDtoDtoMapper;
    }

    @Override
    public MovieDto create(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setName(movieDto.getName());
        movie.setDescription(movieDto.getDescription());
        movie.setUrl(movieDto.getUrl());
        Movie createdMovie = movieDao.create(movie);
        return movieDtoMapper.getDto(createdMovie);
    }

    @Override
    public MovieDto updateInfo(Long existedMovieId, MovieDto newData) {
        Movie movieByDb = movieDao.getById(existedMovieId);
        if (movieByDb == null) {
            throw new NoSuchElementException("not found movie by id = " + existedMovieId);
        } else {
            movieByDb.setName(newData.getName());
            movieByDb.setDescription(newData.getDescription());
            movieByDb.setUrl(newData.getUrl());
            //during transaction => without movieDao.update()
            return movieDtoMapper.getDto(movieByDb);
        }
    }

    @Override
    public CommentDto addComment(Long existedMovieId, CommentDto infoForCreationComment) {
        Movie movieById = movieDao.getById(existedMovieId);
        if (movieById == null) {
            throw new NoSuchElementException("not found movie by id = " + existedMovieId);
        } else {
            Long createdCommentId = commentService.create(infoForCreationComment).getId();
            Comment createdComment = commentDao.getById(createdCommentId);
            createdComment.setMovie(movieById);
            return commentDtoMapper.getDto(createdComment);
        }
    }

    @Override
    public CommentDto removeCommentById(Long existedMovieId, Long commentId) {
        Movie movie = movieDao.getById(existedMovieId);
        if (movie == null) {
            throw new NoSuchElementException("not found movie by id = " + existedMovieId);
        } else {
            Comment commentForDelete = commentDao.getById(commentId);
            if (movie.removeComment(commentForDelete)) {
                 return commentDtoMapper.getDto(commentForDelete);
            } else {
                throw new IllegalArgumentException("movie by id = " + existedMovieId + " not contains comment with id = " + commentId);
            }
        }
    }

    //todo addActor and removeActor are similar, probably refactoring is available
    @Override
    public MovieDto addActorById(Long existedMovieId, Long existedActorId) {
        System.out.println("-----inter-----");
        Movie movie = movieDao.getById(existedMovieId);
        if (movie == null) {
            throw new NoSuchElementException("not found movie by id = " + existedMovieId);
        } else {
            Actor actor = actorDao.getById(existedActorId);
            if (actor != null) {
                System.out.println("-----!!!!-----");
                movie.addActor(actor);
                System.out.println(movie.toString());
                System.out.println(movie.getActors().toString());
            } else {
                //if actor doesn't exist, do nothing.
            }

            return movieDtoMapper.getDto(movie);
        }
    }

    //todo addActor and removeActor are similar, probably refactoring is available
    @Override
    public MovieDto removeActorById(Long existedMovieId, Long existedActorId) {
        Movie movie = movieDao.getById(existedMovieId);
        if (movie == null) {
            throw new NoSuchElementException("not found movie by id = " + existedMovieId);
        } else {
            Actor actor = actorDao.getById(existedActorId);
            if (actor != null) {
                movie.removeActor(actor);
            } else {
                //if actor doesn't exist, do nothing.
            }
            return movieDtoMapper.getDto(movie);
        }
    }

    @Override
    public MovieDto addTagsByIds(Long existedMovieId, List<Long> existedTagIds) {
        Movie movie = movieDao.getById(existedMovieId);
        if (movie == null) {
            throw new NoSuchElementException("not found movie by id = " + existedMovieId);
        } else {
            List<Tag> byIdsAndOrderByName = tagDao.findByIdsAndOrderByName(existedTagIds);
            for (Tag tag : byIdsAndOrderByName) {
                movie.addTag(tag);
            }
            return movieDtoMapper.getDto(movie);
        }
    }

    @Override
    public MovieDto removeTagsByIds(Long existedMovieId, List<Long> existedTagIds) {
        Movie movie = movieDao.getById(existedMovieId);
        if (movie == null) {
            throw new NoSuchElementException("not found movie by id = " + existedMovieId);
        } else {
            List<Tag> byIdsAndOrderByName = tagDao.findByIdsAndOrderByName(existedTagIds);
            for (Tag tag : byIdsAndOrderByName) {
                movie.removeTag(tag);
            }
            return movieDtoMapper.getDto(movie);
        }
    }

    @Override
    public MovieDto getById(Long id) throws NoSuchElementException {
        Movie byId = movieDao.getById(id);
        if (byId != null) {
            return movieDtoMapper.getDto(byId);
        } else {
            throw new NoSuchElementException("not found movie by id = " + id);
        }
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> allMovies = movieDao.getList();
        List<MovieDto> allMovieDtos = new LinkedList<>();
        allMovies.forEach(movie -> allMovieDtos.add(movieDtoMapper.getDto(movie)));
        return allMovieDtos;
    }

    @Override
    public MovieDto deleteById(Long id) throws NoSuchElementException {
        Movie forDelete = movieDao.getById(id);
        if (forDelete == null) {
            throw new NoSuchElementException("not found movie by id = " + id);
        } else {
            movieDao.delete(forDelete);
            return movieDtoMapper.getDto(forDelete);
        }
    }

    @Override
    public List<MovieDto> getByAtLeastOneActor(List<Long> actorIds) {
        throw new NotSupportedException("not implements yet");
    }

    @Override
    public List<MovieDto> getByAllTag(List<Long> tagIds) {
        throw new NotSupportedException("not implements yet");
    }

}
