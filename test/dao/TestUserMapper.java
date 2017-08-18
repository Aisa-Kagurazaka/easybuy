package dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import pojo.User;
import util.MyBatisUtil;

public class TestUserMapper {

	//���Բ����û���
	@Test
	public void testCount() {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(UserMapper.class).count();
		System.out.println("���ҵ��û���¼����"+num);
	}

	//���Ը����û�ID�����û�
	@Test
	public void testGetUserById(){
		SqlSession session = MyBatisUtil.createSqlSession();
		User user = session.getMapper(UserMapper.class).getUserById(6);
		System.out.println(user);
	}
	
	//���������û�
	@Test
	public void testAddUser(){
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = 0;
		try {
			User user = new User();
			user.setUserName("С��");
			user.setPwd("123");
			num = session.getMapper(UserMapper.class).addUser(user);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
		System.out.println("�ɹ�����" + num + "���û���Ϣ��");
	}
	
	//�����û��ĵ�¼
	@Test
	public void testLogin(){
		SqlSession session = MyBatisUtil.createSqlSession();
		User user = session.getMapper(UserMapper.class).login("123", "123");
		System.out.println(user);
	}
	
	//����û����Ƿ����
	@Test
	public void testCheckUserName(){
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(UserMapper.class).checkUserName("admin6");
		System.out.println(num);
	}
	
}
