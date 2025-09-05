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
import services.StatusServices;
import services.TasksServices;

@WebServlet("")
public class HomeController extends HttpServlet{
	private StatusServices statusServices = new StatusServices();
	private TasksServices tasksServices = new TasksServices();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		List<Status> listStatus = statusServices.getAllStatus(); 
		List<Tasks> listTasks = tasksServices.getAllTasks(); 
		for (Status status : listStatus) {
			status.setTasksListStatus(tasksServices.getTasksByIdStatus(status.getId()));
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

		
		req.setAttribute("listStatus", listStatus);
		req.setAttribute("ListColorStatusText", ListColorStatusText);
		req.setAttribute("ListColorStatusProcess", ListColorStatusProcess);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
