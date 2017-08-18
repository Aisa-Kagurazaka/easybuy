package dao;

import org.apache.ibatis.annotations.Param;

import pojo.User;

public interface UserMapper {
	
	/**
	 * 查找用户总数
	 * @return
	 */
	public int count();
	
	/**
	 * 根据用户ID查找用户数
	 * @param userId
	 * @return
	 */
	public User getUserById(@Param("userId") Integer userId);
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public int addUser(User user);
	
	/**
	 * 登录根据用户名和密码匹配用户
	 * @param name
	 * @param pwd
	 * @return
	 */
	public User login(@Param("userName") String name, @Param("pwd") String pwd);
	
	/**
	 * 判断用户是否已经存在
	 * @param name
	 * @return
	 */
	public int checkUserName(@Param("userName") String name);
}
