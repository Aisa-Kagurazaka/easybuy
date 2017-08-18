package dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import pojo.News;
import util.MyBatisUtil;
import util.Tool;

public class TestNewsMapper {

	//测试查询新闻总记录数
	@Test
	public void testCount() {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(NewsMapper.class).count();
		System.out.println("新闻总记录数："+num);
	}

	//测试通过ID查找新闻
	@Test
	public void testGetNewsById(){
		SqlSession session = MyBatisUtil.createSqlSession();
		News news = session.getMapper(NewsMapper.class).getNewsById(85);
		System.out.println(news);
	}
	
	//测试分页查找新闻
	@Test
	public void testGetByPage(){
		SqlSession session = MyBatisUtil.createSqlSession();
		List<News> newsList = session.getMapper(NewsMapper.class).getByPage(0, 5);
		for(News n: newsList){
			System.out.println(n);
		}
	}
	
	//测试更新新闻
	@Test
	public void testUpdateNews(){
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = 0;
		News news = new News();
		news.setNewsId(85);
		//news.setNewsTitle("标题");
		//news.setAuthor("新闻网");
		news.setCreateDate(Tool.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		try {
			num = session.getMapper(NewsMapper.class).updateNews(news);
			session.commit();
		} catch (Exception e) {
			num = 0;
			session.rollback();
		}
		System.out.println("成功更新"+num+"条新闻");
	}
	
	
	//测试删除新闻
	@Test
	public void testDelNews(){
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = 0;
		try {
			num = session.getMapper(NewsMapper.class).delNews(85);
			session.commit();
		} catch (Exception e) {
			num = 0;
			session.rollback();
		}
		System.out.println("成功删除"+num+"条新闻");
	}
	
}
