package mediasoft.education.kvv.cinematograph.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mediasoft.education.kvv.cinematograph.api.input.CommentInput;
import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rest/comment")
@Api(tags = {"CRUD for comment "})
public class CommentRest {

    private CommentService commentService;

    @Inject
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "create new comment")
    public CommentDto create(CommentInput commentInput) {
        CommentDto forCreation = new CommentDto();
        forCreation.setText(commentInput.getComment());
        return commentService.create(forCreation);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get comment by id")
    public CommentDto getById(@PathParam("id") Long id) {
        return commentService.getById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "update existed comment's text")
    public CommentDto update(@PathParam("id") Long id, CommentInput commentInput) {
        CommentDto newData = new CommentDto();
        newData.setText(commentInput.getComment());
        return commentService.updateInfo(id, newData);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get all comments from db")
    public List<CommentDto> getAll() {
        return commentService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "delete comment by specific id")
    public CommentDto deleteById(@PathParam("id") Long id) {
        return commentService.deleteById(id);
    }
}
