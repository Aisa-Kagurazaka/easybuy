package pojo;

public class Cart {
	private Integer cartId;
	private Integer userId;
	private Integer goodsId;
	private Integer amount;
	private String status;
	
	public Cart() {
		super();
	}

	public Cart(Integer userId, Integer goodsId, Integer amount) {
		super();
		this.userId = userId;
		this.goodsId = goodsId;
		this.amount = amount;
	}

	public Cart(Integer cartId, Integer userId, Integer goodsId, Integer amount) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.amount = amount;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", goodsId="
				+ goodsId + ", amount=" + amount + "]";
	}
	
}
