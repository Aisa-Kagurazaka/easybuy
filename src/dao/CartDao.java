package dao;

import java.util.List;

import entity.Cart;

public interface CartDao {
	public int save(Cart cart);
	public List<Cart> getUnsettleCartByUserId(int userId);
	public int updateCart(Cart cart);
}
