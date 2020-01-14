package mediasoft.education.kvv.cinematograph.http_api;

import mediasoft.education.kvv.cinematograph.dto.TagDto;
import mediasoft.education.kvv.cinematograph.service.TagService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/tag")
public class TagApi {

    private TagService tagService;

    @Inject
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TagDto> getAllOrderByName() {
        return tagService.findAllAndOrderByName(true);
    }

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TagDto> getWithNameLikeAs(@QueryParam("value") String name) {
        return tagService.findWithNameLikeAs(name);
    }
}
