package mediasoft.education.kvv.cinematograph.servlet;

import mediasoft.education.kvv.cinematograph.entity.Actor;
import mediasoft.education.kvv.cinematograph.entity.Movie;
import mediasoft.education.kvv.cinematograph.service.ActorService;
import mediasoft.education.kvv.cinematograph.service.MovieService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "HelloMovie", urlPatterns = "/movie")
public class MovieServlet extends HttpServlet {

    @Inject
    private MovieService movieService;

    @Inject
    private ActorService actorService;

    @PersistenceContext
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello from MovieServlet.\n");
        stringBuilder.append("INFO:\n");

        Movie movie =  movie();
        movie.addActor(actor());
        movie.addActor(actor());

        stringBuilder.append("\ncreate new movie with new actors in db\n");
        Movie movie1 = movieService.create(movie);
        stringBuilder.append("movie1: ").append(movie.toString()).append("\n");
        stringBuilder.append("movie1's actors: ").append(movie.getActors().toString()).append("\n");
        stringBuilder.append("existed in db? movie1 from db by id: ").append(movieService.getById(movie1.getId())).append("\n");

        stringBuilder.append("\ncreate new movie without actors\n");
        Movie movie2 = movieService.create(movie());
        stringBuilder.append("movie2: ").append(movie2.toString()).append("\n");
        stringBuilder.append("create new actor without movie\n");
        Actor actor = actorService.create(actor());
        stringBuilder.append("actor: ").append(actor.toString()).append("\n");
        movie2.addActor(actor);

        stringBuilder.append("\nadd into existed movie existed actor\n");
        movieService.update(movie2);
        movieService.update(movie2);
        Movie movie2ById = movieService.getById(movie2.getId());
        stringBuilder.append("movie2ById = by id movie2: ").append(movie2ById.toString()).append("\n");
        List<Actor> actors = movie2ById.getActors();
        stringBuilder.append("actors: " + actors.toString()).append("\n");

        stringBuilder.append("\ndelete movie by id: movie2.id");
        stringBuilder.append("\nsearch: " + movieService.getById(movie2.getId()));
        stringBuilder.append("\ndelete " + movieService.deleteById(movie2.getId()));
        try {
            stringBuilder.append("\nrepeat search: " + movieService.getById(movie2.getId()));
        } catch (Exception e)
        {
            stringBuilder.append(e.getMessage());
        }
        stringBuilder.append("\n" + actorService.findAll().toString());

        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.println(stringBuilder.toString());
    }

    private Actor actor() {
        Actor actor = new Actor();
        actor.setName("actor_" + UUID.randomUUID().toString());
        return actor;
    }

    private Movie movie() {
        Movie movie = new Movie();
        movie.setName("movie_" + UUID.randomUUID().toString());
        return movie;
    }


}
