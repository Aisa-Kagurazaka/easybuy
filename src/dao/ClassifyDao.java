package dao;

import java.util.List;

import entity.Classify;

public interface ClassifyDao {
	public Classify getClassifyById(int classifyId);
	public List<Classify> getAllFatherClass();
	public List<Classify> getClassByFatherId(int fatherId);
	public List<Classify> getTopSecondClass();
	public int updateClassify(Classify classify);
	public int checkFatherClassifyName(String name);
	public int checkSecondClassifyName(String name, int fatherId);
	public int addFatherClassify(String classifyName);
	public int addSecondClassify(String classifyName, int fatherId);
	public int getMaxFatherClassifyId();
	public boolean isClassifyContainsGoods(int classifyId);
	public boolean isFatherClassifyHaveSubclassify(int classifyId);
	public int delClassify(int classifyId);
}
