package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Status;
import entity.Tasks;
import services.StatusServices;
import services.TasksServices;


@WebServlet(name = "ProfileController", urlPatterns = "/profile")
public class ProfileController extends HttpServlet{
	private TasksServices tasksServices = new TasksServices();
	private StatusServices statusServices = new StatusServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] listCookie = req.getCookies();
		int idUser = 0;
		if (listCookie != null) {
			for (Cookie cookie : listCookie) {
				if (cookie.getName().equals("idUser")) {
					idUser = Integer.parseInt(cookie.getValue());
				}
			}
		}
		List<Tasks> listTasks = tasksServices.getTaskByIdUser(idUser);
		
	
//		COUNT FOR WIDTH COUNT TASKS STATUS
		List<Status> listStatus = statusServices.getAllStatus(); 
		List<Tasks> listTasksAll = tasksServices.getAllTasks(); 

		for (Status status  : listStatus) {
			// so luong status trong task
			int statusCountInTask = 0;
			
			for ( Tasks tasksCurrent : listTasksAll) {
				if (tasksCurrent.getStatus_id() == status.getId()) {
					statusCountInTask+=1;
				}
			}
			double totalStatusType = listStatus.size(); // 3
			double totalStatusTask = listTasksAll.size(); // so luong tasks
			
			// width cua process trong profile
			int statusWidth = (int) Math.round(statusCountInTask / totalStatusTask * 100 ) ;
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
		req.setAttribute("listStatus", listStatus);
		req.setAttribute("listTasks", listTasks);
		req.getRequestDispatcher("/profile.jsp").forward(req, resp);
	}
}
