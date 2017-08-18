package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.GoodsDao;
import entity.Goods;

public class GoodsDaoImpl extends BaseDao implements GoodsDao {

	@Override
	public int addGoods(Goods goods) {
		int num = 0;
		String goodsName = goods.getGoodsName();
		int classifyId = goods.getClassifyId();
		double price = goods.getPrice();
		String brand = goods.getBrand();
		String path = goods.getPath();
		int inventory = goods.getInventory();
		String describe = goods.getDescribe();
		StringBuffer sqlBuf = new StringBuffer("insert into goods(goodsName,classifyId,price,brand,path,inventory,`describe`)");
		sqlBuf.append("values(?,?,?,?,?,?,?)");
		String sql = sqlBuf.toString();
		Object[] param = {goodsName,classifyId,price,brand,path,inventory,describe};
		num = super.update(sql, param);
		return num;
	}
	
	@Override
	public Goods getGoodsById(int goodsId){
		Goods goods = null;
		String sql = "select * from goods where goodsId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goodsId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String goodsName = rs.getString("goodsName");
				double price = rs.getDouble("price");
				String brand = rs.getString("brand");
				int classifyId = rs.getInt("classifyId");
				String path = rs.getString("path");
				int inventory = rs.getInt("inventory");
				String describe = rs.getString("describe");
				goods = new Goods(goodsId, goodsName, classifyId, price, brand, path, inventory, describe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return goods;
	}
	@Override
	public List<Goods> getByClassifyId(int classifyId) {
		List<Goods> goodsList = new ArrayList<Goods>();
		String sql = "select * from goods where classifyId=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classifyId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int goodsId = rs.getInt("goodsId");
				String goodsName = rs.getString("goodsName");
				double price = rs.getDouble("price");
				String brand = rs.getString("brand");
				String path = rs.getString("path");
				int inventory = rs.getInt("inventory");
				String describe = rs.getString("describe");
				Goods go = new Goods(goodsId, goodsName, classifyId, price, brand, path, inventory, describe);
				goodsList.add(go);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return goodsList;
	}
	
	@Override
	public List<Goods> getByPage(int page){
		List<Goods> goodsList = new ArrayList<Goods>();
		String sql = "select * from goods limit ?,12";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*12);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int goodsId = rs.getInt("goodsId");
				String goodsName = rs.getString("goodsName");
				int classifyId = rs.getInt("classifyId");
				double price = rs.getDouble("price");
				String brand = rs.getString("brand");
				String path = rs.getString("path");
				int inventory = rs.getInt("inventory");
				String describe = rs.getString("describe");
				Goods go = new Goods(goodsId, goodsName, classifyId, price, brand, path, inventory, describe);
				goodsList.add(go);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return goodsList;
	}
	
	@Override
	public List<Goods> getByClassifyIdAndPage(int classifyId, int page) {
		List<Goods> goodsList = new ArrayList<Goods>();
		String sql = "select * from goods where classifyId=? limit ?,12";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classifyId);
			pstmt.setInt(2, (page-1)*12);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int goodsId = rs.getInt("goodsId");
				String goodsName = rs.getString("goodsName");
				double price = rs.getDouble("price");
				String brand = rs.getString("brand");
				String path = rs.getString("path");
				int inventory = rs.getInt("inventory");
				String describe = rs.getString("describe");
				Goods go = new Goods(goodsId, goodsName, classifyId, price, brand, path, inventory, describe);
				goodsList.add(go);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return goodsList;
	}

	@Override
	public int getTotalPages() {
		int num = 0;
		int n = 0;
		String sql = "select count(goodsId) as n from goods";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				n = rs.getInt("n");
			}
			num = (n%12==0)?(n/12):(n/12+1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return num;
	}

	@Override
	public int getTotalPagesByClassifyId(int classifyId) {
		int num = 0;
		int n = 0;
		String sql = "select count(goodsId) as n from goods where classifyid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classifyId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				n = rs.getInt("n");
			}
			num = (n%12==0)?(n/12):(n/12+1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return num;
	}

	@Override
	public List<Goods> getTop12HotGoods() {
		List<Goods> goodsList = new ArrayList<Goods>();
		String sql = "select * from goods where isHot=1 limit 0,12";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int goodsId = rs.getInt("goodsId");
				String goodsName = rs.getString("goodsName");
				int classifyId = rs.getInt("classifyId");
				double price = rs.getDouble("price");
				String brand = rs.getString("brand");
				String path = rs.getString("path");
				int inventory = rs.getInt("inventory");
				String describe = rs.getString("describe");
				Goods go = new Goods(goodsId, goodsName, classifyId, price, brand, path, inventory, describe);
				goodsList.add(go);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return goodsList;
	}

	@Override
	public List<Goods> getTop8OffGoods() {
		List<Goods> goodsList = new ArrayList<Goods>();
		String sql = "select * from goods where isOff=1 limit 0,8";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int goodsId = rs.getInt("goodsId");
				String goodsName = rs.getString("goodsName");
				int classifyId = rs.getInt("classifyId");
				double price = rs.getDouble("price");
				String brand = rs.getString("brand");
				String path = rs.getString("path");
				int inventory = rs.getInt("inventory");
				String describe = rs.getString("describe");
				Goods go = new Goods(goodsId, goodsName, classifyId, price, brand, path, inventory, describe);
				goodsList.add(go);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return goodsList;
	}

	@Override
	public int updateFully(Goods goods) {
		int num = 0;
		int goodsId = goods.getGoodsId();
		String goodsName = goods.getGoodsName();
		int classifyId = goods.getClassifyId();
		double price = goods.getPrice();
		String brand = goods.getBrand();
		String path = goods.getPath();
		int inventory = goods.getInventory();
		String describe = goods.getDescribe();
		String sql = "update goods set goodsName=?,classifyId=?,price=?,brand=?,path=?,inventory=?,`describe`=? where goodsId=?";
		Object[] param = {goodsName, classifyId, price, brand, path, inventory, describe, goodsId};
		num = super.update(sql, param);
		return num;
	}

	@Override
	public int updateButNotUpdatePic(Goods goods) {
		int num = 0;
		int goodsId = goods.getGoodsId();
		String goodsName = goods.getGoodsName();
		int classifyId = goods.getClassifyId();
		double price = goods.getPrice();
		String brand = goods.getBrand();
		int inventory = goods.getInventory();
		String describe = goods.getDescribe();
		String sql = "update goods set goodsName=?,classifyId=?,price=?,brand=?,inventory=?,`describe`=? where goodsId=?";
		Object[] param = {goodsName, classifyId, price, brand, inventory, describe, goodsId};
		num = super.update(sql, param);
		return num;
	}

	@Override
	public int del(int id) {
		String sql = "delete from goods where goodsId=?";
		Object[] param = {id};
		return super.update(sql, param);
	}
	
//	public static void main(String[] args) {
//		System.out.println("getByClassifyId: ");
//		List<Goods> goodsList = new GoodsDaoImpl().getByClassifyId(2001);
//		for (int i = 0; i < goodsList.size(); i++) {
//			System.out.println(goodsList.get(i));
//		}
//		
//		System.out.println("getTotalPages: ");
//		System.out.println(new GoodsDaoImpl().getTotalPages());
//		
//		System.out.println("getTotalPagesByClassifyId: ");
//		System.out.println(new GoodsDaoImpl().getTotalPagesByClassifyId(2002));
//		
//		System.out.println("getByPage: ");
//		List<Goods> goodsList2 = new GoodsDaoImpl().getByPage(1);
//		for (int i = 0; i < goodsList2.size(); i++) {
//			System.out.println(goodsList2.get(i));
//		}
//		
//		System.out.println("getByClassifyIdAndPage: ");
//		List<Goods> goodsList3 = new GoodsDaoImpl().getByClassifyIdAndPage(1000,1);
//		for (int i = 0; i < goodsList3.size(); i++) {
//			System.out.println(goodsList3.get(i));
//		}
//	}
	
}
