package mediasoft.education.kvv.cinematograph.rest;

import mediasoft.education.kvv.cinematograph.dto.TagDto;
import mediasoft.education.kvv.cinematograph.service.TagService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rest/tag")
public class TagRest {

    private TagService tagService;

    @Inject
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TagDto create(TagDto tagDto){
        return tagService.create(tagDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TagDto getById(@PathParam("id") Long id) {
        return tagService.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TagDto> getAll(){
        return tagService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TagDto deleteById(@PathParam("id") Long id){
        return tagService.deleteById(id);
    }

}
