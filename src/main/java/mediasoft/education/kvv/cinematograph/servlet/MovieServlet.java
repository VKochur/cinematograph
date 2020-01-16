package mediasoft.education.kvv.cinematograph.servlet;

import mediasoft.education.kvv.cinematograph.dto.ActorDto;
import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.service.ActorService;
import mediasoft.education.kvv.cinematograph.service.MovieService;
import mediasoft.education.kvv.cinematograph.servlet.jsp_data.MovieOutput;
import mediasoft.education.kvv.cinematograph.servlet.util.Util;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "MovieServlet", urlPatterns = "/movie")
public class MovieServlet extends HttpServlet {

    @Inject
    private MovieService movieService;

    @Inject
    private ActorService actorService;

    @Inject
    private Util util;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<MovieDto> movieDtos = defineMovies(req);
        List<MovieOutput> movieOutputs = new ArrayList<>(movieDtos.size());
        for (MovieDto movie : movieDtos) {

            //get actor's ids only
            Set<ActorDto> actors = movie.getActors();
            //with fields' value
            Set<ActorDto> fullActors = new LinkedHashSet<>();
            //get info
            for (ActorDto actorDto : actors) {
                Long idActor = actorDto.getId();
                ActorDto actorWithData = actorService.getById(idActor);
                fullActors.add(actorWithData);
            }

            //define comments
            List<CommentDto> comments = movie.getComments();
            List<Long> commentsIds = new LinkedList<>();
            comments.forEach(commentDto -> commentsIds.add(commentDto.getId()));
            String codeForShowComments = util.defineHtmlCodeForCommentsView(commentsIds);

            MovieOutput movieOutput = new MovieOutput();
            movieOutput.setData(movie);
            movieOutput.setActors(fullActors);
            movieOutput.setHtmlCodeComments(codeForShowComments);

            movieOutputs.add(movieOutput);
        }

        String path = "jsp/movie.jsp";
        req.setAttribute("movies", movieOutputs);
        req.getRequestDispatcher(path).forward(req, resp);
    }

    private List<MovieDto> defineMovies(HttpServletRequest req) {
        String id = req.getParameterMap().get("id")[0];
        MovieDto movie1 = movieService.getById(1L);
        MovieDto movie2 = movieService.getById(19L);
        return Arrays.asList(movie1, movie2);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/form.jsp").forward(req, resp);
    }

}
