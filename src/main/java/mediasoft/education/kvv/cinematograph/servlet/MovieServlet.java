package mediasoft.education.kvv.cinematograph.servlet;

import mediasoft.education.kvv.cinematograph.entity.Actor;
import mediasoft.education.kvv.cinematograph.entity.Movie;
import mediasoft.education.kvv.cinematograph.service.ActorService;
import mediasoft.education.kvv.cinematograph.service.MovieService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "HelloMovie", urlPatterns = "/movie")
public class MovieServlet extends HttpServlet {

    @Inject
    private MovieService movieService;

    @Inject
    private ActorService actorService;

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

        stringBuilder.append("\ncreate new movie without actors\n");
        Movie movie2 = movieService.create(movie());
        stringBuilder.append("movie2: ").append(movie2.toString()).append("\n");
        stringBuilder.append("create new actor without movie");
        Actor actor = actorService.create(actor());
        stringBuilder.append("actor: ").append(actor.toString()).append("\n");
        movie2.addActor(actor);

        stringBuilder.append("\nadd into existed movie existed actor");
        movieService.update(movie2);
        stringBuilder.append("movie2: ").append(movie2.toString()).append("\n");

        stringBuilder.append("\nadd into existed actor existed movie");
        stringBuilder.append("movie1: ").append(movie.toString()).append("\n");
        stringBuilder.append("movie1's actors: ").append(movie.getActors().toString()).append("\n");

        stringBuilder.append("\nfind movie by id");
        Movie byId = movieService.getById(movie1.getId());
        stringBuilder.append("movies by id = movie1.id: ").append(byId.toString()).append("\n");
        stringBuilder.append("try get byId's actors: ");
        try {
            stringBuilder.append(byId.getActors().toString());
        } catch (Exception e) {
            stringBuilder.append("Excp:").append(e.getMessage());
        }

        stringBuilder.append("\nfind all movies");
        movieService.findAll().forEach((movieCur) ->stringBuilder.append(movieCur.toString()).append("\n"));

        stringBuilder.append("\nfind all actors");
        actorService.findAll().forEach((actorCur) ->stringBuilder.append(actorCur.toString()).append("\n"));


        stringBuilder.append("\ndelete movie by id: movie1.id");
        movieService.deleteById(movie1.getId());

        stringBuilder.append("\nafter delete movie1 find all movies");
        movieService.findAll().forEach((movieCur) ->stringBuilder.append(movieCur.toString()).append("\n"));

        stringBuilder.append("\nafter delete movie1 find all actors");
        actorService.findAll().forEach((actorCur) ->stringBuilder.append(actorCur.toString()).append("\n"));

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
