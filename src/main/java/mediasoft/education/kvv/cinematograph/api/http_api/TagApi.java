package mediasoft.education.kvv.cinematograph.api.http_api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mediasoft.education.kvv.cinematograph.dto.TagDto;
import mediasoft.education.kvv.cinematograph.service.TagService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/tag")
@Api(tags = {"api for tags"})
public class TagApi {

    private TagService tagService;

    @Inject
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get all tags order by name")
    public List<TagDto> getAllOrderByName() {
        return tagService.findAllAndOrderByName(true);
    }

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get tags with name like specific")
    public List<TagDto> getWithNameLikeAs(@QueryParam("value") String name) {
        return tagService.findWithNameLikeAs(name);
    }
}
