package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

import dao.UserAccess;
import dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;  
	UserDAO usuarioDAO = new UserAccess();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		
		User usuario = usuarioDAO.obtenerUsuario(correo, contrasena);
		
		if(usuario != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("id", usuario.getId());
			response.sendRedirect("home");
		} else {	
			request.setAttribute("status", "failed");
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
}
}