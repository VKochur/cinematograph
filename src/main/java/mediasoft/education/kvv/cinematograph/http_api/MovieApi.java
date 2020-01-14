package mediasoft.education.kvv.cinematograph.http_api;

import mediasoft.education.kvv.cinematograph.dto.CommentDto;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.service.MovieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/movie")
public class MovieApi {

    private MovieService movieService;

    @Inject
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @PUT
    @Path("/{idMovie}/actor/add/{idActor}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieDto addActor(@PathParam("idMovie") Long idMovie, @PathParam("idActor") Long idActor) {
        return movieService.addActorById(idMovie, idActor);
    }

    @PUT
    @Path("/{idMovie}/actor/remove/{idActor}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieDto removeActor(@PathParam("idMovie") Long idMovie, @PathParam("idActor") Long idActor) {
        return movieService.removeActorById(idMovie, idActor);
    }

    @PUT
    @Path("/{idMovie}/comment/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CommentDto addComment(@PathParam("idMovie") Long idMovie, CommentDto commentDto) {
        return movieService.addComment(idMovie, commentDto);
    }

    @DELETE
    @Path("/{idMovie}/comment/remove/{idComment}")
    @Produces(MediaType.APPLICATION_JSON)
    public CommentDto removeComment(@PathParam("idMovie") Long idMovie, @PathParam("idComment") Long idComment) {
        return movieService.removeCommentById(idMovie, idComment);
    }

    @PUT
    @Path("/{idMovie}/tag/add/ids")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MovieDto addTagsByIds(@PathParam("idMovie") Long idMovie, List<Long> tagIds) {
        return movieService.addTagsByIds(idMovie, tagIds);
    }

    @PUT
    @Path("/{idMovie}/tag/add/ids")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public MovieDto removeTagsByIds(@PathParam("idMovie") Long idMovie, List<Long> tagIds) {
        return movieService.removeTagsByIds(idMovie, tagIds);
    }

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getMoviesBySimilarName(@QueryParam("value") String name) {
        return movieService.getBySimilarName(name);
    }

    @PUT
    @Path("/getByActors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getMoviesByActors(List<Long> actorIds) {
        return movieService.getByAtLeastOneActor(actorIds);
    }

}
