package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import pojo.Notice;
import util.MyBatisUtil;

public class TestNoticeMapper {

	//���Ի�ȡ��������
	@Test
	public void testCount() {
		SqlSession session = MyBatisUtil.createSqlSession();
		int num = session.getMapper(NoticeMapper.class).count();
		System.out.println("�ܹ�������"+num);
	}

	//���Ը��ݹ���ID��ȡ����
	@Test
	public void testGetNoticeById(){
		SqlSession session = MyBatisUtil.createSqlSession();
		Notice notice = session.getMapper(NoticeMapper.class).getNoticeById(1);
		System.out.println(notice);
	}
	
	//���Է�ҳ���ҹ���
	@Test
	public void testGetNoticeByPage(){
		SqlSession session = MyBatisUtil.createSqlSession();
		List<Notice> noticeList = session.getMapper(NoticeMapper.class).getNoticeByPage(0, 3);
		for(Notice n: noticeList){
			System.out.println(n);
		}
	}
	
}
