package mediasoft.education.kvv.cinematograph.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mediasoft.education.kvv.cinematograph.api.input.TagInput;
import mediasoft.education.kvv.cinematograph.dto.TagDto;
import mediasoft.education.kvv.cinematograph.service.TagService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rest/tag")
@Api(tags = {"CRUD for tag"})
public class TagRest {

    private TagService tagService;

    @Inject
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "create new tag")
    public TagDto create(TagInput tagInput){
        TagDto tagDto = new TagDto();
        tagDto.setName(tagInput.getName());
        return tagService.create(tagDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get by id")
    public TagDto getById(@PathParam("id") Long id) {
        return tagService.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get all")
    public List<TagDto> getAll(){
        return tagService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "delete by id")
    public TagDto deleteById(@PathParam("id") Long id){
        return tagService.deleteById(id);
    }
}
