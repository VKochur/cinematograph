package mediasoft.education.kvv.cinematograph.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mediasoft.education.kvv.cinematograph.api.input.ActorInput;
import mediasoft.education.kvv.cinematograph.dto.ActorDto;
import mediasoft.education.kvv.cinematograph.service.ActorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rest/actor")
@Api(tags = {"CRUD for actor"})
public class ActorRest {

    private ActorService actorService;

    @Inject
    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "create new")
    public ActorDto create(ActorInput actorInput){
        ActorDto forCreation = new ActorDto();
        forCreation.setName(actorInput.getName());
        forCreation.setInfoUrl(actorInput.getInfoUrl());
        return actorService.create(forCreation);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get by id")
    public ActorDto getById(@PathParam("id") Long id) {
        return actorService.getById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "update existed")
    public ActorDto update(@PathParam("id") Long id, ActorInput actorInput){
        ActorDto newData = new ActorDto();
        newData.setName(actorInput.getName());
        newData.setInfoUrl(actorInput.getInfoUrl());
        return actorService.updateInfo(id, newData);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get all")
    public List<ActorDto> getAll(){
        return actorService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "delete existed")
    public ActorDto deleteById(@PathParam("id") Long id){
        return actorService.deleteById(id);
    }

}
