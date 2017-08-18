package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.CommentDao;
import entity.Comment;

public class CommentDaoImpl extends BaseDao implements CommentDao {
	
	@Override
	public int getTotalPage(int pageSize) {
		int num = 0;
		int totalPages = 1;
		String sql = "select count(commentId) as n from comment";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt("n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		if(pageSize<1) pageSize = 1;
		totalPages = (num%pageSize==0) ? (num/pageSize) : (num/pageSize+1);
		return totalPages;
	}

	@Override
	public List<Comment> getByPage(int page, int pageSize) {
		List<Comment> list = new ArrayList<Comment>();
		String sql = "select commentId, title, content, author, time from `comment` ORDER BY time desc limit ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*pageSize);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int commentId = rs.getInt("commentId");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String author = rs.getString("author");
				String time = rs.getString("time");
				Comment comm = new Comment(commentId, title, content, author, time);
				list.add(comm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public int save(Comment comm) {
		int num = 0;
		String title = comm.getTitle();
		String content = comm.getContent();
		String author = comm.getAuthor();
		String sql = "insert into `comment`(title,content,author) values(?,?,?)";
		Object[] param = {title, content,author};
		num = super.update(sql, param);
		return num;
	}

	@Override
	public int del(int id) {
		String sql = "delete from `comment` where commentId=?";
		Object[] param = {id};
		return super.update(sql, param);
	}

	@Override
	public Comment getCommentById(int id) {
		Comment comment = null;
		String sql = "select * from `comment` where commentId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String title = rs.getString("title");
				String content = rs.getString("content");
				String author = rs.getString("author");
				String time = rs.getString("time");
				comment = new Comment(id, title, content, author, time);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return comment;
	}

	@Override
	public int update(Comment comment) {
		String sql = "update `comment` set content=?,author=?,time=? where commentId=?";
		int commentId = comment.getCommentId();
		String content = comment.getContent();
		String author = comment.getAuthor();
		String time = comment.getTime();
		Object[] param = {content,author,time,commentId};
		return super.update(sql, param);
	}
	
//	public static void main(String[] args) {
//		CommentDaoImpl impl = new CommentDaoImpl();
//		System.out.println(impl.getTotalPage(5));
//		CommentDaoImpl impl2 = new CommentDaoImpl();
//		List<Comment> list = impl2.getByPage(2, 5);
//		for(Comment comm : list){
//			System.out.println(comm);
//		}
//	}
}
