package entity;

import java.util.ArrayList;
import java.util.List;

public class Status {
	private int id;
	private String name;
	private int widthStatus;
	private List<Tasks> tasksListStatus;

	public Status() {
		this.tasksListStatus = new ArrayList<Tasks>();
	}

	public Status(int id, String name, int widthStatus) {
		super();
		this.id = id;
		this.name = name;
		this.widthStatus = widthStatus;
		this.tasksListStatus = new ArrayList<Tasks>();
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

	public List<Tasks> getTasksListStatus() {
		return tasksListStatus;
	}

	public void setTasksListStatus(List<Tasks> tasksListStatus) {
		this.tasksListStatus = tasksListStatus;
	}
	
	

}
