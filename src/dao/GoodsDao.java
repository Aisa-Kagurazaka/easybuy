package dao;

import java.util.List;

import entity.Goods;

public interface GoodsDao {
	public int addGoods(Goods goods);
	public Goods getGoodsById(int goodsId);
	public List<Goods> getByClassifyId(int classifyId);
	public List<Goods> getByPage(int page);
	public List<Goods> getByClassifyIdAndPage(int classifyId, int page);
	public int getTotalPages();
	public int getTotalPagesByClassifyId(int classifyId);
	public List<Goods> getTop12HotGoods();
	public List<Goods> getTop8OffGoods();
	public int updateFully(Goods goods);
	public int updateButNotUpdatePic(Goods goods);
	public int del(int id);
}
