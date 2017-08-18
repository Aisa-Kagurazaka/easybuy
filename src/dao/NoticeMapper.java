package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Notice;

public interface NoticeMapper {

	/**
	 * ��ѯ������ܼ�¼��
	 * @return
	 */
	public int count();

	/**
	 * ���ݹ���ID���ҹ���
	 * @param noticeId
	 * @return
	 */
	public Notice getNoticeById(@Param("noticeId") Integer noticeId);

	/**
	 * ��ҳ���ҹ���
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Notice> getNoticeByPage(@Param("offset") Integer offset,
			@Param("pageSize") Integer pageSize);

}
