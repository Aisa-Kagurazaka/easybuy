package pojo;

public class User {
	private Integer userId;
	private String userName;
	private String pwd;
	private String address;
	private String phone;
	private String email;
	private String sex;
	private Integer level;
	private Integer point;
	private Integer type;
	
	public User(){
		super();
	}
	
	public User(String userName, String pwd) {
		super();
		this.userName = userName;
		this.pwd = pwd;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", pwd="
				+ pwd + ", address=" + address + ", phone=" + phone
				+ ", email=" + email + ", sex=" + sex + ", level=" + level
				+ ", point=" + point + ", type=" + type + "]";
	}

}
