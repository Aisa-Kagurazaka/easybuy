package dao.impl;

import org.apache.ibatis.session.SqlSession;

import util.MyBatisUtil;
import dao.OrderMapper;
import pojo.Order;

public class OrderDaoImpl {

	/**
	 * ����һ���µĶ�����¼
	 * @param order
	 * @return
	 */
	public int addOrder(Order order) {
		int num = 0;
		SqlSession session = MyBatisUtil.createSqlSession();
		try {
			num = session.getMapper(OrderMapper.class).addOrder(order);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			num = 0;
		}
		return num;
	}
	
}
