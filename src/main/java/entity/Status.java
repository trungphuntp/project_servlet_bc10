package entity;

public class Status {
	private int id;
	private String name;
	private int widthStatus;
	
	public Status() {
		// TODO Auto-generated constructor stub
	}

	

	public Status(int id, String name, int widthStatus) {
		super();
		this.id = id;
		this.name = name;
		this.widthStatus = widthStatus;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public int getWidthStatus() {
		return widthStatus;
	}



	public void setWidthStatus(int widthStatus) {
		this.widthStatus = widthStatus;
	}
	
	
}
