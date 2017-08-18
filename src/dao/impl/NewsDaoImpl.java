package dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.NewsMapper;
import pojo.News;
import util.MyBatisUtil;

public class NewsDaoImpl {

	/**
	 * ��ȡ���µ�num������
	 * @param num
	 * @return
	 */
	public List<News> getSeveralLatestNews(int num) {
		SqlSession session = MyBatisUtil.createSqlSession();
		List<News> newsList = session.getMapper(NewsMapper.class).getByPage(0, num);
		return newsList;
	}

	/**
	 * ��������ID��������
	 * @param newsId
	 * @return
	 */
	public News getNewsById(int newsId) {
		SqlSession session = MyBatisUtil.createSqlSession();
		News news = session.getMapper(NewsMapper.class).getNewsById(newsId);
		return news;
	}

	/**
	 * ��ҳ���������б�
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<News> getByPage(int page, int pageSize) {
		Integer offset = (page-1)*pageSize;
		SqlSession session = MyBatisUtil.createSqlSession();
		List<News> newsList = session.getMapper(NewsMapper.class).getByPage(offset, pageSize);
		return newsList;
	}

	/**
	 * ��������
	 * @param news
	 * @return
	 */
	public int updateNews(News news) {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = 0;
		try {
			num = session.getMapper(NewsMapper.class).updateNews(news);
			session.commit();
		} catch (Exception e) {
			num = 0;
			session.rollback();
		}
		return num;
	}

	/**
	 * ��������IDɾ������
	 * @param id
	 * @return
	 */
	public int delNews(int id) {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = 0;
		try {
			num = session.getMapper(NewsMapper.class).delNews(id);
			session.commit();
		} catch (Exception e) {
			num = 0;
			session.rollback();
		}
		return num;
	}

	/**
	 * ����ÿҳ��ʾ������������ҳ��
	 * @param pageSize
	 * @return
	 */
	public int getTotalPage(int pageSize) {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(NewsMapper.class).count();
		return (num % pageSize == 0) ? (num/pageSize):(num/pageSize +1);
	}

}
