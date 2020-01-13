package mediasoft.education.kvv.cinematograph.http_api;

import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/comment")
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
    public CommentDto addComment(@PathParam("idComment") Long idParentComment, CommentDto newComment){
        return commentService.addComment(idParentComment, newComment);
    }
}
