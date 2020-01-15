package mediasoft.education.kvv.cinematograph.api.http_api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import mediasoft.education.kvv.cinematograph.api.input.CommentInput;
import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.service.MovieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/movie")
@Api(tags = {"api for movies"})
public class MovieApi {

    private MovieService movieService;

    @Inject
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @PUT
    @Path("/{idMovie}/actor/add/{idActor}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "add actor for movie")
    public MovieDto addActor(@ApiParam(required = true) @PathParam("idMovie") Long idMovie,
                             @ApiParam(required = true) @PathParam("idActor") Long idActor) {
        return movieService.addActorById(idMovie, idActor);
    }

    @PUT
    @Path("/{idMovie}/actor/remove/{idActor}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "remove actor from movie")
    public MovieDto removeActor(@ApiParam(required = true) @PathParam("idMovie") Long idMovie,
                                @ApiParam(required = true) @PathParam("idActor") Long idActor) {
        return movieService.removeActorById(idMovie, idActor);
    }

    @PUT
    @Path("/{idMovie}/comment/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "add comment to movie")
    public CommentDto addComment(@ApiParam(required = true) @PathParam("idMovie") Long idMovie,
                                 @ApiParam(required = true) CommentInput commentInput) {
        CommentDto commentDto = new CommentDto();
        commentDto.setText(commentInput.getComment());
        return movieService.addComment(idMovie, commentDto);
    }

    @DELETE
    @Path("/{idMovie}/comment/remove/{idComment}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "delete comment from movie")
    public CommentDto removeComment(@ApiParam(required = true) @PathParam("idMovie") Long idMovie,
                                    @ApiParam(required = true) @PathParam("idComment") Long idComment) {
        return movieService.removeCommentById(idMovie, idComment);
    }

    @PUT
    @Path("/{idMovie}/tag/add/ids")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "add tags for movie by ids")
    public MovieDto addTagsByIds(@ApiParam(required = true) @PathParam("idMovie") Long idMovie,
                                 @ApiParam(required = true) List<Long> tagIds) {
        return movieService.addTagsByIds(idMovie, tagIds);
    }

    @PUT
    @Path("/{idMovie}/tag/add/ids")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "remove tags with specific ids from movie")
    public MovieDto removeTagsByIds(@ApiParam(required = true) @PathParam("idMovie") Long idMovie,
                                    @ApiParam(required = true) List<Long> tagIds) {
        return movieService.removeTagsByIds(idMovie, tagIds);
    }

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get movies with similar specific name")
    public List<MovieDto> getMoviesBySimilarName(@QueryParam("value") String name) {
        return movieService.getBySimilarName(name);
    }

    @PUT
    @Path("/getByActors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get movies there plays at least on of specific actors. actors specific by ids")
    public List<MovieDto> getMoviesByActors(List<Long> actorIds) {
        return movieService.getByAtLeastOneActor(actorIds);
    }

    @PUT
    @Path("/getByTags")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get movies that match for all specific tags. tags specific by ids")
    public List<MovieDto> getMoviesByTags(List<Long> tagIds) {
        return movieService.getByAllTag(tagIds);
    }

}
