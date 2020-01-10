package mediasoft.education.kvv.cinematograph.rest;

import mediasoft.education.kvv.cinematograph.dto.ActorDto;
import mediasoft.education.kvv.cinematograph.service.ActorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rest/actor")
public class ActorRest {

    private ActorService actorService;

    @Inject
    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ActorDto create(ActorDto actorDto){
        return actorService.create(actorDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ActorDto getById(@PathParam("id") Long id) {
        return actorService.getById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ActorDto update(@PathParam("id") Long id, ActorDto newData){
        return actorService.updateInfo(id, newData);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActorDto> getAll(){
        return actorService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ActorDto deleteById(@PathParam("id") Long id){
        return actorService.deleteById(id);
    }

}
