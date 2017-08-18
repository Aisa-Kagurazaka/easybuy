package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Notice;

public interface NoticeMapper {

	/**
	 * 查询公告的总记录数
	 * @return
	 */
	public int count();

	/**
	 * 根据公告ID查找公告
	 * @param noticeId
	 * @return
	 */
	public Notice getNoticeById(@Param("noticeId") Integer noticeId);

	/**
	 * 分页查找公告
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Notice> getNoticeByPage(@Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

}
