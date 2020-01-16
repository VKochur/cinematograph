package mediasoft.education.kvv.cinematograph.servlet;

import mediasoft.education.kvv.cinematograph.dto.ActorDto;
import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.service.ActorService;
import mediasoft.education.kvv.cinematograph.service.CommentService;
import mediasoft.education.kvv.cinematograph.service.MovieService;
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

        String id = req.getParameterMap().get("id")[0];
        MovieDto movie = movieService.getById(Long.parseLong(id));

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

        String path = "jsp/movie.jsp";
        req.setAttribute("movie", movie);
        req.setAttribute("actors", fullActors);

        //define comments
        List<CommentDto> comments = movie.getComments();
        List<Long> commentsIds = new LinkedList<>();
        comments.forEach(commentDto -> commentsIds.add(commentDto.getId()));

        String codeForShowComments = util.defineHtmlCodeForCommentsView(commentsIds);
        System.out.println(codeForShowComments);
        req.setAttribute("htmlCodeForComments", codeForShowComments);
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/form.jsp").forward(req, resp);
    }

}
