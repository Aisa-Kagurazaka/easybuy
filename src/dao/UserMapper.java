package dao;

import org.apache.ibatis.annotations.Param;

import pojo.User;

public interface UserMapper {
	
	/**
	 * �����û�����
	 * @return
	 */
	public int count();
	
	/**
	 * �����û�ID�����û���
	 * @param userId
	 * @return
	 */
	public User getUserById(@Param("userId") Integer userId);
	
	/**
	 * �����û�
	 * @param user
	 * @return
	 */
	public int addUser(User user);
	
	/**
	 * ��¼�����û���������ƥ���û�
	 * @param name
	 * @param pwd
	 * @return
	 */
	public User login(@Param("userName") String name, @Param("pwd") String pwd);
	
	/**
	 * �ж��û��Ƿ��Ѿ�����
	 * @param name
	 * @return
	 */
	public int checkUserName(@Param("userName") String name);
}
