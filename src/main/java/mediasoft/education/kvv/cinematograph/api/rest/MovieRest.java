package mediasoft.education.kvv.cinematograph.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mediasoft.education.kvv.cinematograph.api.input.MovieInput;
import mediasoft.education.kvv.cinematograph.dto.MovieDto;
import mediasoft.education.kvv.cinematograph.service.MovieService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rest/movie")
@Api(tags = {"CRUD for movie"})
public class MovieRest {

    private MovieService movieService;

    @Inject
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "create new")
    public MovieDto create(MovieInput movieInput) {
        MovieDto forCreation = new MovieDto();
        forCreation.setName(movieInput.getName());
        forCreation.setDescription(movieInput.getDescription());
        forCreation.setUrl(movieInput.getUrl());
        return movieService.create(forCreation);
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "get by id")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieDto getById(@PathParam("id") Long id) {
        return movieService.getById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "update existed")
    public MovieDto update(@PathParam("id") Long id, MovieInput movieInput) {
        MovieDto newData = new MovieDto();
        newData.setName(movieInput.getName());
        newData.setDescription(movieInput.getDescription());
        newData.setUrl(movieInput.getUrl());
        return movieService.updateInfo(id, newData);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get all")
    public List<MovieDto> getAll() {
        return movieService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "delete existed")
    public MovieDto deleteById(@PathParam("id") Long id) {
        return movieService.deleteById(id);
    }

}
