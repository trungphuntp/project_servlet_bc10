package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Status;

public class StatusRepository {
	public List<Status> findAllStatus() {
		String query = "SELECT * FROM status s;";
		List<Status> listStatus = new ArrayList<Status>();
		Connection connection = MySQLConfig.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Status status = new Status();
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getString("name"));
				listStatus.add(status);
			}
			
		} catch (Exception e) {
			System.out.println("StatusRepository : " + e.getMessage());
		}
		
		return listStatus;
	}
}
