package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import pojo.User;
import util.MyBatisUtil;

public class TestUserMapper {

	//测试查找用户数
	@Test
	public void testCount() {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(UserMapper.class).count();
		System.out.println("查找到用户记录数："+num);
	}

	//测试根据用户ID查找用户
	@Test
	public void testGetUserById(){
		SqlSession session = MyBatisUtil.createSqlSession();
		User user = session.getMapper(UserMapper.class).getUserById(6);
		System.out.println(user);
	}
	
	//测试新增用户
	@Test
	public void testAddUser(){
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = 0;
		try {
			User user = new User();
			user.setUserName("小宝");
			user.setPwd("123");
			num = session.getMapper(UserMapper.class).addUser(user);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
		System.out.println("成功新增" + num + "条用户信息！");
	}
	
	//测试用户的登录
	@Test
	public void testLogin(){
		SqlSession session = MyBatisUtil.createSqlSession();
		User user = session.getMapper(UserMapper.class).login("123", "123");
		System.out.println(user);
	}
	
	//检查用户名是否存在
	@Test
	public void testCheckUserName(){
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(UserMapper.class).checkUserName("admin6");
		System.out.println(num);
	}
	
}
