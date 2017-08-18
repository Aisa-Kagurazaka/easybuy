package dao;

import pojo.Order;

public interface OrderMapper {
	
	/**
	 * 统计订单总数
	 * @return
	 */
	public int count();
	
	/**
	 * 新增一条订单记录
	 * @param order
	 * @return
	 */
	public int addOrder(Order order);
	
}
