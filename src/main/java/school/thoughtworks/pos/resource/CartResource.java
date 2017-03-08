package school.thoughtworks.pos.resource;

import org.apache.ibatis.session.SqlSession;
import school.thoughtworks.pos.bean.Cart;
import school.thoughtworks.pos.mapper.CartMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/carts")
public class CartResource {

    @Inject
    private CartMapper cartMapper;

    @Inject
    private SqlSession session;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        Map<String, Object> result = new HashMap<>();

        List<Cart> originCategories = cartMapper.findAll();

        List<Map> carts = originCategories
                .stream()
                .map(cart -> cart.toMap())
                .collect(Collectors.toList());

        result.put("carts", carts);
        result.put("totalCount", carts.size());

        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCartById(Cart cart) {
        cartMapper.insertCart(cart);
        session.commit();
        Map result = new HashMap();
        result.put("uri", "carts/" + cart.getId());
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItemById(@PathParam("id") Integer id) {
        cartMapper.deleteCartById(id);
        session.commit();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCart(Cart cart) {
        cartMapper.updateCart(cart);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCartById(@PathParam("id") Integer id) {
        Cart cart = cartMapper.getCartById(id);
        if (null == cart){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(cart.toMap()).build();
    }

    @POST
    @Path("/{cartId}/items/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertItemCart(@PathParam("cartId") Integer cartId,@PathParam("itemId") Integer itemId) {
        cartMapper.insertItemCart(itemId,cartId);
        session.commit();
        return Response.status(Response.Status.CREATED).build();
    }


}
