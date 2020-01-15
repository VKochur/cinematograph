package mediasoft.education.kvv.cinematograph.api.http_api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import mediasoft.education.kvv.cinematograph.api.input.CommentInput;
import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/comment")
@Api(tags = {"api for comments"})
public class CommentApi {

    private CommentService commentService;

    @Inject
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @PUT
    @Path("/{idComment}/comment/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "create new comment for existed")
    public CommentDto addComment(@ApiParam(required = true) @PathParam("idComment") Long idParentComment, CommentInput newComment){
        CommentDto forCreation = new CommentDto();
        forCreation.setText(newComment.getComment());
        return commentService.addComment(idParentComment, forCreation);
    }
}
