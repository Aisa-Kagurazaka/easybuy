package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.News;

public interface NewsMapper {
	
	/**
	 * 获取新闻总数
	 * @return
	 */
	public int count();
	
	/**
	 * 根据新闻ID查找新闻
	 * @param newsId
	 * @return
	 */
	public News getNewsById(@Param("newsId") Integer newsId);
	
	/**
	 * 分页查找新闻纪录
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<News> getByPage(@Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);
	
	/**
	 * 更新新闻
	 * @param news
	 * @return
	 */
	public int updateNews(News news);
	
	/**
	 * 根据ID删除新闻
	 * @param newsId
	 * @return
	 */
	public int delNews(@Param("newsId") Integer newsId);
	
}
