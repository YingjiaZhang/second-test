package school.thoughtworks.pos.resource;

import school.thoughtworks.pos.mapper.ItemMapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class RootResource {

    @Inject
    private ItemMapper itemMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRootInfo() {
        Map<String, String> result = new HashMap<>();
        result.put("items", "/items");

        return Response.status(Response.Status.OK).entity(result).build();
    }

}
