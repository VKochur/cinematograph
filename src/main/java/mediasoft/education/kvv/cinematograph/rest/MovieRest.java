package mediasoft.education.kvv.cinematograph.rest;

import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.service.MovieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rest/movie")
public class MovieRest {

    private MovieService movieService;

    @Inject
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MovieDto create(MovieDto movieDto){
        return movieService.create(movieDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieDto getById(@PathParam("id") Long id) {
        return movieService.getById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MovieDto update(@PathParam("id") Long id, MovieDto newData){
        return movieService.updateInfo(id, newData);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getAll(){
        return movieService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieDto deleteById(@PathParam("id") Long id){
        return movieService.deleteById(id);
    }

}
