package entity;

public class Users {
	private int id;
	private String email;
	private String password;
	private String fullname;
	private String avatar;
	private int role_id;
	private String role_desc;
	
	
	public Users(int id, String email, String password, String fullname, String avatar, int role_id, String role_desc) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.role_id = role_id;
		this.role_desc = role_desc;
	}

	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	
	
	
}
