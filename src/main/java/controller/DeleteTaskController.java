package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.TasksServices;

@WebServlet(name="DeleteTaskController", urlPatterns = "/task-delete")
public class DeleteTaskController extends HttpServlet{
	private TasksServices tasksServices = new TasksServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int deleteId = Integer.parseInt(req.getParameter("id-delete"));
		if (deleteId != 0) {
			tasksServices.deleteTask(deleteId);
			resp.sendRedirect(req.getContextPath() + "/tasks");
			return;
		}
		req.getRequestDispatcher("/task.jsp").forward(req, resp);
	}
}
