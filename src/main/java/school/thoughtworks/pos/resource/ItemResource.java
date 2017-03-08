package school.thoughtworks.pos.resource;

import org.apache.ibatis.session.SqlSession;
import school.thoughtworks.pos.bean.Item;
import school.thoughtworks.pos.mapper.ItemMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/items")
public class ItemResource {

    @Inject
    private ItemMapper itemMapper;

    @Inject
    private SqlSession session;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        Map<String, Object> result = new HashMap<>();

        List<Item> originItems = itemMapper.findAll();

        List<Map> items = originItems
                .stream()
                .map(item -> item.toMap())
                .collect(Collectors.toList());

        result.put("items", items);
        result.put("totalCount", items.size());

        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertItem(Item item) {
        itemMapper.insertItem(item);
        session.commit();
        Map result = new HashMap();
        result.put("uri", "items/" + item.getId());
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItemById(@PathParam("id") Integer id) {
        itemMapper.deleteItemById(id);
        session.commit();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(Item item) {
        itemMapper.updateItem(item);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") Integer id) {
        Item item = itemMapper.getItemById(id);
        if (null == item){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(item.toMap()).build();
    }


}
