package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.News;

public interface NewsMapper {
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	public int count();
	
	/**
	 * ��������ID��������
	 * @param newsId
	 * @return
	 */
	public News getNewsById(@Param("newsId") Integer newsId);
	
	/**
	 * ��ҳ�������ż�¼
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<News> getByPage(@Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);
	
	/**
	 * ��������
	 * @param news
	 * @return
	 */
	public int updateNews(News news);
	
	/**
	 * ����IDɾ������
	 * @param newsId
	 * @return
	 */
	public int delNews(@Param("newsId") Integer newsId);
	
}
