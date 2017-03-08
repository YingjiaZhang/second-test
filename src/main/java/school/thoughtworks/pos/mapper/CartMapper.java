package school.thoughtworks.pos.mapper;

import org.apache.ibatis.annotations.Param;
import school.thoughtworks.pos.bean.Cart;

import java.util.List;

public interface CartMapper {
    List<Cart> findAll();

    int insertCart(Cart cart);

    int deleteCartById(Integer id);

    int updateCart(Cart cart);

    Cart getCartById(Integer id);

    int insertItemCart(@Param("itemId") Integer itemId,@Param("cartId") Integer cartId);
}