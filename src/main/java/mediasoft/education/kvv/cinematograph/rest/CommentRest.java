package mediasoft.education.kvv.cinematograph.rest;

import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rest/comment")
public class CommentRest {

    private CommentService commentService;

    @Inject
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CommentDto create(CommentDto actorDto) {
        return commentService.create(actorDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommentDto getById(@PathParam("id") Long id) {
        return commentService.getById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CommentDto update(@PathParam("id") Long id, CommentDto newData) {
        return commentService.updateInfo(id, newData);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommentDto> getAll() {
        return commentService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommentDto deleteById(@PathParam("id") Long id) {
        return commentService.deleteById(id);
    }
}
