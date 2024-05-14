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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;
	UserDAO usuarioDAO = new UserAccess();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("login");
		} else {
			Object id = session.getAttribute("id");
			
			if(id == null) {
				response.sendRedirect("login");
			} else {	
				int id2 = (int) session.getAttribute("id");
				User usuario = usuarioDAO.obtenerUsuarioPorID(id2);
				session.setAttribute("usuario", usuario);
				dispatcher = request.getRequestDispatcher("home.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}


	
       

}
