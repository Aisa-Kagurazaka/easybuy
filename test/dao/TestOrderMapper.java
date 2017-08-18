package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import pojo.Order;
import util.MyBatisUtil;

public class TestOrderMapper {

	//查询订单总数
	@Test
	public void testCount() {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(OrderMapper.class).count();
		System.out.println("一共查询到订单记录数：" + num);
	}

	//测试新增订单
	@Test
	public void testAddOrder(){
		Order order = new Order();
		order.setGoodsId(70);
		order.setUserId(1);
		order.setAmount(1);
		order.setTotal(55);
		int num = 0;
		SqlSession session = MyBatisUtil.createSqlSession();
		try {
			num = session.getMapper(OrderMapper.class).addOrder(order);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			num = 0;
		}
		System.out.println("成功增加了"+num+"条记录");
	}
	
}
