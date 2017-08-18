package dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import pojo.News;
import util.MyBatisUtil;
import util.Tool;

public class TestNewsMapper {

	//���Բ�ѯ�����ܼ�¼��
	@Test
	public void testCount() {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(NewsMapper.class).count();
		System.out.println("�����ܼ�¼����"+num);
	}

	//����ͨ��ID��������
	@Test
	public void testGetNewsById(){
		SqlSession session = MyBatisUtil.createSqlSession();
		News news = session.getMapper(NewsMapper.class).getNewsById(85);
		System.out.println(news);
	}
	
	//���Է�ҳ��������
	@Test
	public void testGetByPage(){
		SqlSession session = MyBatisUtil.createSqlSession();
		List<News> newsList = session.getMapper(NewsMapper.class).getByPage(0, 5);
		for(News n: newsList){
			System.out.println(n);
		}
	}
	
	//���Ը�������
	@Test
	public void testUpdateNews(){
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = 0;
		News news = new News();
		news.setNewsId(85);
		//news.setNewsTitle("����");
		//news.setAuthor("������");
		news.setCreateDate(Tool.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		try {
			num = session.getMapper(NewsMapper.class).updateNews(news);
			session.commit();
		} catch (Exception e) {
			num = 0;
			session.rollback();
		}
		System.out.println("�ɹ�����"+num+"������");
	}
	
	
	//����ɾ������
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
		System.out.println("�ɹ�ɾ��"+num+"������");
	}
	
}
