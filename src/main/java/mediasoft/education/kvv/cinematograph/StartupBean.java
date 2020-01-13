package mediasoft.education.kvv.cinematograph;

import mediasoft.education.kvv.cinematograph.dao.MovieDao;
import mediasoft.education.kvv.cinematograph.entity.Actor;
import mediasoft.education.kvv.cinematograph.entity.Movie;
import mediasoft.education.kvv.cinematograph.entity.Tag;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class StartupBean {

    @Inject
    MovieDao movieDao;

    @PostConstruct
    public void init() {
        Movie[] movies = new Movie[]{
                getMovieInstance("Брилиантовая рука", "Советская комедия", null),
                getMovieInstance("12 стульев", "Советская комедия по рассказу Ильфа и Петрова, 4 серии", null),
                getMovieInstance("12 стульев", "Советская комедия по рассказу Ильфа и Петрова, 2 серии", null),
                getMovieInstance("Собачье сердце", "Советское кино. По рассказу Булгакова", null),
                getMovieInstance("Старики разбойники", "Советское кино", null),
                getMovieInstance("Терминатор", "USA, 1984", null)
        };

        Actor[] actors = new Actor[]{
                getActorInstance("Миронов Андрей", null),
                getActorInstance("Юрий Никулин", null),
                getActorInstance("Анатолий Папанов", null),
                getActorInstance("Евстигнеев", null),
                getActorInstance("Гомиашвили", null),
                getActorInstance("Арнольд Шварценнегер", null),
                getActorInstance("Крачковская", null)
        };

        Tag[] tags = new Tag[]{
                getTagInstance("Советское"),
                getTagInstance("Зарубежное"),
                getTagInstance("Фантастика"),
                getTagInstance("Комедия"),
                getTagInstance("Боевик"),
                getTagInstance("Драма")
        };

        createRelation(movies[0], actors[0], actors[1], actors[2], actors[3]);
        createRelation(movies[1], actors[0], actors[2]);
        createRelation(movies[2], actors[4], actors[6]);
        createRelation(movies[3], actors[3]);
        createRelation(movies[4], actors[0], actors[1], actors[3]);
        createRelation(movies[5], actors[5]);

        createRelation(tags[0], movies[0], movies[1], movies[2], movies[3], movies[4]);
        createRelation(tags[1], movies[5]);
        createRelation(tags[2], movies[5]);
        createRelation(tags[3], movies[0], movies[1], movies[2], movies[3], movies[4]);
        createRelation(tags[4], movies[5]);
        createRelation(tags[5], movies[3]);

        createInDb(movies);
    }

    private void createInDb(Movie[] movies) {
        for (Movie movie : movies) {
            movieDao.create(movie);
        }
    }

    private void createRelation(Tag tag, Movie... movies) {
        for (Movie movie : movies) {
            tag.addMovie(movie);
        }
    }

    private void createRelation(Movie movie, Actor... actors) {
        for (Actor actor : actors) {
            movie.addActor(actor);
        }
    }

    private Movie getMovieInstance(String name, String description, String url) {
        final String WIKI_URL = "https://ru.wikipedia.org/wiki/";
        Movie movie = new Movie();
        movie.setName(name);
        movie.setName(name);
        movie.setDescription(description);
        movie.setUrl((url == null) ? WIKI_URL + name : url);
        return movie;
    }

    private Actor getActorInstance(String name, String url) {
        final String WIKI_URL = "https://ru.wikipedia.org/wiki/";
        Actor actor = new Actor();
        actor.setName(name);
        actor.setInfoUrl((url == null) ? WIKI_URL + name : url);
        return actor;
    }

    private Tag getTagInstance(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }

}
