package mediasoft.education.kvv.cinematograph.service.impl;

import mediasoft.education.kvv.cinematograph.dao.MovieDao;
import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.dto.mapper.MovieDtoMapper;
import mediasoft.education.kvv.cinematograph.entity.Movie;
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

    @Inject
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Inject
    public void setMovieDtoMapper(MovieDtoMapper movieDtoMapper) {
        this.movieDtoMapper = movieDtoMapper;
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
            Movie updatedMovie = movieDao.update(movieByDb);
            return movieDtoMapper.getDto(updatedMovie);
        }
    }

    @Override
    public MovieDto addComment(Long existedMovieId, CommentDto commentDto) {
        throw new NotSupportedException("not implements yet");
    }

    @Override
    public MovieDto removeCommentById(Long existedMovieId, Long commentId) {
        throw new NotSupportedException("not implements yet");
    }

    @Override
    public MovieDto addActorById(Long existedMovieId, Long existedActorId) {
        throw new NotSupportedException("not implements yet");
    }

    @Override
    public MovieDto removeActorById(Long existedMovieId, Long existedActorId) {
        throw new NotSupportedException("not implements yet");
    }

    @Override
    public MovieDto addTagsByIds(Long existedMovieId, List<Long> existedTagIds) {
        throw new NotSupportedException("not implements yet");
    }

    @Override
    public MovieDto removeTagsByIds(Long existedMovieId, List<Long> existedTagIds) {
        throw new NotSupportedException("not implements yet");
    }

    @Override
    public MovieDto getById(Long id) throws NoSuchElementException {
        Movie byId = movieDao.getById(id);
        if (byId != null) {
            return movieDtoMapper.getDto(byId);
        } else {
            throw new NoSuchElementException();
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
