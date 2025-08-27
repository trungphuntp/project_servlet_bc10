package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Tasks {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private int user_id;
	private String nameUser;
	private int job_id;
	private String jobName;
	private int status_id;
	private String nameStatus;

	

	public Tasks(int id, String name, Date startDate, Date endDate, int user_id, String nameUser, int job_id,
			String jobName, int status_id, String nameStatus) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user_id = user_id;
		this.nameUser = nameUser;
		this.job_id = job_id;
		this.jobName = jobName;
		this.status_id = status_id;
		this.nameStatus = nameStatus;
	}

	public Tasks() {
		// TODO Auto-generated constructor stub
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	
	public String getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}

	public String getDateDDMMYYYY(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formatted = sdf.format(date);
		return formatted;
	}

}
