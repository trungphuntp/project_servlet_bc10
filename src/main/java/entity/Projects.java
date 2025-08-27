package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Projects {
	private int id;
	private String name;
	private Date dateStart;
	private Date dateEnd;
	
	
	
	public Projects(int id, String name, Date dateStart, Date dateEnd) {
		this.id = id;
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public Projects() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDateDDMMYYYY(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formatted = sdf.format(date);
		return formatted;
	}
	
}
