package dao;

import pojo.Order;

public interface OrderMapper {
	
	/**
	 * ͳ�ƶ�������
	 * @return
	 */
	public int count();
	
	/**
	 * ����һ��������¼
	 * @param order
	 * @return
	 */
	public int addOrder(Order order);
	
}
