package mediasoft.education.kvv.cinematograph.servlet;

import mediasoft.education.kvv.cinematograph.entity.Actor;
import mediasoft.education.kvv.cinematograph.entity.Comment;
import mediasoft.education.kvv.cinematograph.entity.Movie;
import mediasoft.education.kvv.cinematograph.service.ActorService;
import mediasoft.education.kvv.cinematograph.service.CommentService;
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
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@WebServlet(name = "HelloMovieActor", urlPatterns = "/movie_comment")
public class MovieComntServlet extends HttpServlet {

    @Inject
    private MovieService movieService;

    @Inject
    private CommentService commentService;

    @Inject
    private ActorService actorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("hello from MovieComntServlet.\n");
        stringBuilder.append("INFO:\n");

        Movie movie1 = movie();
        movie1 = movieService.create(movie1);
        Comment comment1 = comment();
        Comment comment2 = comment();
        comment1 = commentService.create(comment1);
        comment2 = commentService.create(comment2);
        movie1.addComment(comment1);
        movie1.addComment(comment2);
        movie1 = movieService.update(movie1);

        System.err.println(movie1);

        stringBuilder.append(movieService.getById(movie1.getId()).getComments().toString() + "\n");

        movie1.removeComment(comment1);
        movieService.update(movie1);
        stringBuilder.append("1: " + movieService.getById(movie1.getId()).getComments().toString() + "\n");

        commentService.deleteById(comment2.getId());
        stringBuilder.append(movieService.getById(movie1.getId()).getComments().toString() + "\n");
        stringBuilder.append("2: " + movieService.getById(movie1.getId()).getComments().toString() + "\n");

        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.println(stringBuilder.toString());

 */
    }

    private Movie movie() {
        Movie movie = new Movie();
        movie.setName("movie_" + UUID.randomUUID().toString());
        return movie;
    }

    private Comment comment() {
        Comment comment = new Comment();
        comment.setText("comment_" + UUID.randomUUID().toString());
        comment.setDateTime(LocalDateTime.now());
        return comment;
    }

}
