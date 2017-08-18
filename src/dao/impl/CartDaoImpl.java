package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.CartDao;
import entity.Cart;

public class CartDaoImpl extends BaseDao implements CartDao {

	@Override
	public int save(Cart cart) {
		String sql = "insert into cart(userId, goodsId, amount) values(?,?,?)";
		Integer userId = cart.getUserId();
		Integer goodsId = cart.getGoodsId();
		Integer amount = cart.getAmount();
		Object[] param = {userId, goodsId, amount};
		return super.update(sql, param);
	}

	@Override
	public List<Cart> getUnsettleCartByUserId(int userId) {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "select * from cart where userId=? and status='ÒÑÏÂµ¥'";
		Connection conn = super.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Integer cartId = rs.getInt("cartId");
				Integer goodsId = rs.getInt("goodsId");
				Integer amount = rs.getInt("amount");
				Cart cart = new Cart(cartId, userId, goodsId, amount);
				cartList.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public int updateCart(Cart cart) {
		Integer cartId = cart.getCartId();
		Integer userId = cart.getUserId();
		Integer goodsId = cart.getGoodsId();
		Integer amount = cart.getAmount();
		String sql = "update cart set userId=?, goodsId=?, amount=? where cartId=?";
		Object[] param = {userId, goodsId, amount, cartId};
		return super.update(sql, param);
	}

}
