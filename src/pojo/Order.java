package pojo;

public class Order {
	private int orderId;
	private int userId;
	private int goodsId;
	private int amount;
	private double total;
	private String orderTime;
	private String status;
	
	public Order(){
		super();
	}

	public Order(int orderId, int userId, int goodsId, int amount,
			double total, String orderTime, String status) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.amount = amount;
		this.total = total;
		this.orderTime = orderTime;
		this.status = status;
	}
	
	public Order(int userId, int goodsId, int amount, double total) {
		super();
		this.userId = userId;
		this.goodsId = goodsId;
		this.amount = amount;
		this.total = total;
	}

	public Order(int userId, int goodsId, int amount, double total,
			String orderTime, String status) {
		super();
		this.userId = userId;
		this.goodsId = goodsId;
		this.amount = amount;
		this.total = total;
		this.orderTime = orderTime;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
