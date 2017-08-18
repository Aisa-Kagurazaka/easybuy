package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	
	static{
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取SqlSession对象
	 * @return
	 */
	public static SqlSession createSqlSession(){
		return factory.openSession(false); //关闭自动提交事务
	}
	
	/**
	 * 关闭SqlSession
	 * @return
	 */
	public static void closeSqlSession(SqlSession sqlSession){
		if(null != sqlSession){
			sqlSession.close();
		}
	}
}
