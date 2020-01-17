package mediasoft.education.kvv.cinematograph.servlet;

import mediasoft.education.kvv.cinematograph.service.TagService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TagServlet", urlPatterns = "/tag")
public class TagServlet extends HttpServlet {

    @Inject
    private TagService tagService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "jsp/tag.jsp";
        req.setAttribute("infoAboutTagList","All tags");
        req.setAttribute("tags", tagService.findAllAndOrderByName(true));
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
