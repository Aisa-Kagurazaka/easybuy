package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.ClassifyDao;
import entity.Classify;

public class ClassifyDaoImpl extends BaseDao implements ClassifyDao {

	@Override
	public List<Classify> getAllFatherClass() {
		List<Classify> list = new ArrayList<Classify>();
		String sql = "select * from classify where fatherId=0 order by classifyId";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int classifyId = rs.getInt("classifyId");
				String classifyName = rs.getString("classifyName");
				int fatherId = rs.getInt("fatherId");
				Classify clas = new Classify(classifyId, classifyName, fatherId);
				list.add(clas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<Classify> getClassByFatherId(int fatherId) {
		List<Classify> list = new ArrayList<Classify>();
		String sql = "select * from classify where fatherId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fatherId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int classifyId = rs.getInt("classifyId");
				String classifyName = rs.getString("classifyName");
				Classify clas = new Classify(classifyId, classifyName, fatherId);
				list.add(clas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<Classify> getTopSecondClass() {
		List<Classify> list = new ArrayList<Classify>();
		StringBuffer sqlBuf = new StringBuffer("select * from classify where fatherId=");
		sqlBuf.append("(select classifyId from classify where fatherId=0 order by classifyId limit 1)");
		String sql = sqlBuf.toString();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int classifyId = rs.getInt("classifyId");
				String classifyName = rs.getString("classifyName");
				int fatherId = rs.getInt("fatherId");
				Classify clas = new Classify(classifyId, classifyName, fatherId);
				list.add(clas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public Classify getClassifyById(int classifyId) {
		Classify clas = null;
		String sql = "select * from classify where classifyId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classifyId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String classifyName = rs.getString("classifyName");
				int fatherId = rs.getInt("fatherId");
				clas = new Classify(classifyId, classifyName, fatherId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return clas;
	}

	@Override
	public int updateClassify(Classify classify) {
		int num = 0;
		int classifyId = classify.getClassifyId();
		String classifyName = classify.getClassifyName();
		int fatherId = classify.getFatherId();
		String sql = "update classify set classifyName=?, fatherId=? where classifyId=?";
		Object[] param = {classifyName, fatherId, classifyId};
		num = super.update(sql, param);
		return num;
	}

	@Override
	public int checkFatherClassifyName(String name) {
		int num = 0;
		String sql = "select count(classifyId) as n from classify where fatherId=0 and classifyName=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt("n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return num;
	}

	@Override
	public int addFatherClassify(String classifyName) {
		int num = 0;
		String sql = "insert into classify values(?,?,?)";
		int classifyId = ((new ClassifyDaoImpl().getMaxFatherClassifyId())+ 1);
		int fatherId = 0;
		Object[] param = {classifyId, classifyName, fatherId};
		num = super.update(sql, param);
		return num;
	}
	
	@Override
	public int addSecondClassify(String classifyName, int fatherId) {
		int num = 0;
		String sql = "insert into classify (classifyName,fatherId) values(?,?)";
		Object[] param = {classifyName, fatherId};
		num = super.update(sql, param);
		return num;
	}

	@Override
	public int getMaxFatherClassifyId() {
		int num = 0;
		String sql = "select max(classifyId) as n from classify where fatherId=0";
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
		return num;
	}

	@Override
	public int checkSecondClassifyName(String name, int fatherId) {
		int num = 0;
		String sql = "select count(classifyId) as n from classify where fatherId=? and classifyName=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fatherId);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt("n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return num;
	}

	@Override
	public boolean isClassifyContainsGoods(int classifyId) {
		boolean flag = false;
		String sql = "select count(goodsId) as n from goods where classifyId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classifyId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				int num = rs.getInt("n");
				if(num>0) flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return flag;
	}

	@Override
	public boolean isFatherClassifyHaveSubclassify(int classifyId) {
		boolean flag = false;
		String sql = "select count(classifyId) as n from classify where fatherId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classifyId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				int num = rs.getInt("n");
				if(num>0) flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeAll(conn, pstmt, rs);
		}
		return flag;
	}

	@Override
	public int delClassify(int classifyId) {
		int num = 0;
		String sql = "delete from classify where classifyId=?";
		Object[] param = {classifyId};
		num = super.update(sql, param);
		return num;
	}
	
//	public static void main(String[] args) {
//		ClassifyDaoImpl impl1 = new ClassifyDaoImpl();
//		List<Classify> list1 = impl1.getAllFatherClass();
//		System.out.println("getAllFatherClass: ");
//		for(Classify clas: list1){
//			System.out.println(clas);
//		}
//		
//		System.out.println("getClassByFatherId: ");
//		ClassifyDaoImpl impl2 = new ClassifyDaoImpl();
//		List<Classify> list2 = impl2.getClassByFatherId(2);
//		for(Classify clas: list2){
//			System.out.println(clas);
//		}
//		
//		System.out.println("getTopSecondClass: ");
//		ClassifyDaoImpl impl3 = new ClassifyDaoImpl();
//		List<Classify> list3 = impl3.getTopSecondClass();
//		for(Classify clas: list3){
//			System.out.println(clas);
//		}
//	}
	
//	public static void main(String[] args) {
//		System.out.println(new ClassifyDaoImpl().getMaxFatherClassifyId());
//	}
}
