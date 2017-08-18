package pojo;

public class Goods {
	private int goodsId;
	private String goodsName;
	private int classifyId;
	private double price;
	private String brand;
	private String path;
	private int inventory;
	private String describe;
	public Goods(){
		super();
	}
	public Goods(String goodsName, int classifyId, double price, String brand,
			String path, int inventory, String describe) {
		super();
		this.goodsName = goodsName;
		this.classifyId = classifyId;
		this.price = price;
		this.brand = brand;
		this.path = path;
		this.inventory = inventory;
		this.describe = describe;
	}
	public Goods(int goodsId, String goodsName, int classifyId, double price, String brand,
			int inventory, String describe) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.classifyId = classifyId;
		this.price = price;
		this.brand = brand;
		this.inventory = inventory;
		this.describe = describe;
	}
	public Goods(int goodsId, String goodsName, int classifyId, double price, String brand,
			String path, int inventory, String describe) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.classifyId = classifyId;
		this.price = price;
		this.brand = brand;
		this.path = path;
		this.inventory = inventory;
		this.describe = describe;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(int classifyId) {
		this.classifyId = classifyId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName
				+ ", classifyId=" + classifyId + ", price=" + price
				+ ", brand=" + brand + ", path=" + path + ", inventory="
				+ inventory + ", describe=" + describe + "]";
	}
}
