package com.sami.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sami.entity.User;
import com.sami.service.UserService;

@WebServlet(urlPatterns = { "/" })
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	private static final String USER_LIST = "/views/list.jsp";
	private static final String USER_FORM = "/views/create.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getServletPath();
		switch (action) {
		case "/new":
			form(request, response);
			break;

		case "/insert":
			insert(request, response);
			break;

		case "/delete":
			delete(request, response);
			break;

		case "/edit":
			edit(request, response);
			break;

		case "/update":
			update(request, response);
			break;
		default:
			users(request, response);
			break;
		}
	}

	public void users(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<User> users = userService.getAllUser();
		request.setAttribute("users", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher(USER_LIST);
		dispatcher.forward(request, response);
	}

	public void form(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(USER_FORM);
		dispatcher.forward(request, response);
	}

	public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User user = new User(name, email, country);
		userService.save(user);
		response.sendRedirect(request.getContextPath() + "/");
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Long id = Long.parseLong(request.getParameter("id"));
		User existingUser = userService.getUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher(USER_FORM);
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		User user = new User(id, name, email, country);
		userService.update(user);
		response.sendRedirect(request.getContextPath() + "/");
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long id = Long.parseLong(request.getParameter("id"));
		userService.delete(id);
		response.sendRedirect(request.getContextPath() + "/");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
