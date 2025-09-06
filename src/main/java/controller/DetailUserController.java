package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Status;
import entity.Tasks;
import entity.Users;
import services.StatusServices;
import services.TasksServices;
import services.UsersServices;

@WebServlet(name = "DetailUserController" ,  urlPatterns = "/users/user-detail")
public class DetailUserController extends HttpServlet{
	private UsersServices usersServices = new UsersServices();
	private TasksServices tasksServices = new TasksServices();
	private StatusServices statusServices = new StatusServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idUsers = 0;
		try {
			idUsers = Integer.parseInt(req.getParameter("id-user"));
		} catch (Exception e) {
			System.out.println("DetailUserController : " + e.getMessage());
		}
		
		Users users = new Users();
		if (idUsers > 0) {
			users = usersServices.getUsersById(idUsers);
		}
		List<Tasks> listTasks  = tasksServices.getTaskByIdUser(idUsers);
		List<Status> listStatus = statusServices.getAllStatus();
		
//		SET List tasks cho các status tương đương
//		ĐỒNG THỜI tính width và % cho html
		for (Status status : listStatus) {
			status.setTasksListStatus(tasksServices.getTasksByIdStatusAndIdUsers(status.getId(), idUsers));
			
			double countStatusTask = status.getTasksListStatus().size();
			double totalStatusTask = listTasks.size();
			int statusWidth = (int) Math.round(countStatusTask / totalStatusTask * 100 ) ;
			status.setWidthStatus(statusWidth);
		}
		
		List<String> ListColorStatusText = new ArrayList<>(
			    List.of("text-megna", "text-primary","text-danger")
			);
		List<String> ListColorStatusProcess = new ArrayList<String>(			    
				List.of("progress-bar-megna","progress-bar-primary","progress-bar-danger")
		);
		req.setAttribute("ListColorStatusText", ListColorStatusText);
		req.setAttribute("ListColorStatusProcess", ListColorStatusProcess);
		
		req.setAttribute("listTasks", listTasks);
		req.setAttribute("listStatus", listStatus);

		req.setAttribute("users", users);
		req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
	}
}
