package dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.NewsMapper;
import pojo.News;
import util.MyBatisUtil;

public class NewsDaoImpl {

	/**
	 * 获取最新的num条新闻
	 * @param num
	 * @return
	 */
	public List<News> getSeveralLatestNews(int num) {
		SqlSession session = MyBatisUtil.createSqlSession();
		List<News> newsList = session.getMapper(NewsMapper.class).getByPage(0, num);
		return newsList;
	}

	/**
	 * 根据新闻ID查找新闻
	 * @param newsId
	 * @return
	 */
	public News getNewsById(int newsId) {
		SqlSession session = MyBatisUtil.createSqlSession();
		News news = session.getMapper(NewsMapper.class).getNewsById(newsId);
		return news;
	}

	/**
	 * 分页查找新闻列表
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
	 * 更新新闻
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
	 * 根据新闻ID删除新闻
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
	 * 根据每页显示条数，计算总页数
	 * @param pageSize
	 * @return
	 */
	public int getTotalPage(int pageSize) {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(NewsMapper.class).count();
		return (num % pageSize == 0) ? (num/pageSize):(num/pageSize +1);
	}

}
