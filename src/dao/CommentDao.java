package dao;

import java.util.List;

import entity.Comment;

public interface CommentDao {
	public int getTotalPage(int pageSize);
	public List<Comment> getByPage(int page, int pageSize);
	public Comment getCommentById(int id);
	public int save(Comment comm);
	public int del(int id);
	public int update(Comment comm);
}
