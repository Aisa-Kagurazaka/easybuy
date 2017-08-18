package dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.NoticeMapper;
import pojo.Notice;
import util.MyBatisUtil;

public class NoticeDaoImpl {

	/**
	 * ���ݹ���ID���ҹ���
	 * @param noticeId
	 * @return
	 */
	public Notice getNoticeById(int noticeId) {
		SqlSession session = MyBatisUtil.createSqlSession();
		Notice notice = session.getMapper(NoticeMapper.class).getNoticeById(noticeId);
		return notice;
	}

	/**
	 * ��ȡ���µ�num������
	 * @param num
	 * @return
	 */
	public List<Notice> getSeveralLatestNotice(int num){
		SqlSession session = MyBatisUtil.createSqlSession();
		List<Notice> noticeList = session.getMapper(NoticeMapper.class).getNoticeByPage(0, num);
		return noticeList;
	}
	
}
