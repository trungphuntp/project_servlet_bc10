package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Tasks;
import services.TasksServices;

@WebServlet(name="TaskTableController" , urlPatterns = "/tasks")
public class TaskTableController extends HttpServlet{
	private TasksServices tasksServices = new TasksServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Tasks> listTasks = tasksServices.getAllTasks();
		if (!listTasks.isEmpty()) {
			req.setAttribute("listTasks", listTasks);
		}
		req.getRequestDispatcher("/task.jsp").forward(req, resp);
	}
}
