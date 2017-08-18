package dao.impl;

import org.apache.ibatis.session.SqlSession;

import dao.UserMapper;
import pojo.User;
import util.MyBatisUtil;

public class UserDaoImpl{

	/**
	 * ��¼�����û���������ƥ���û�
	 * @param name
	 * @param pwd
	 * @return
	 */
	public User login(String name, String pwd) {
		SqlSession session = MyBatisUtil.createSqlSession();
		User user = session.getMapper(UserMapper.class).login(name, pwd);
		return user;
	}

	/**
	 * �����û�ID�����û�
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId) {
		SqlSession session = MyBatisUtil.createSqlSession();
		User user = session.getMapper(UserMapper.class).getUserById(userId);
		return user;
	}

	/**
	 * �����û�
	 * @param user
	 * @return
	 */
	public int addUser(User user) {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = 0;
		try {
			num = session.getMapper(UserMapper.class).addUser(user);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		}
		return num;
	}

	/**
	 * �ж��û��Ƿ��Ѿ�����
	 * @param name
	 * @return
	 */
	public int checkUserName(String name) {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(UserMapper.class).checkUserName(name);
		return num;
	}
	
}
