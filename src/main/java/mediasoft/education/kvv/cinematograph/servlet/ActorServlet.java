package mediasoft.education.kvv.cinematograph.servlet;

import mediasoft.education.kvv.cinematograph.dto.ActorDto;
import mediasoft.education.kvv.cinematograph.service.ActorService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ActorServlet", urlPatterns = "/actor")
public class ActorServlet extends HttpServlet {

    private static final String ACTOR_NAME = "name_contains";

    @Inject
    private ActorService actorService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] params = req.getParameterMap().get(ACTOR_NAME);
        List<ActorDto> actorDtoList;
        String infoAboutResolveActorList;
        if (params != null) {
            String partOfActorName = params[0];
            infoAboutResolveActorList = "actors' name's contains '" + partOfActorName +"'";
            actorDtoList = actorService.getBySimilarName(partOfActorName);
        } else {
            infoAboutResolveActorList = "all actors";
            actorDtoList = actorService.findAll();
        }
        String path = "jsp/actor.jsp";
        req.setAttribute("infoAboutActorList",infoAboutResolveActorList);
        req.setAttribute("actors", actorDtoList);
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
