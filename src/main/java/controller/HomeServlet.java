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

/**
 * Servlet HomeServlet que maneja las peticiones a la ruta "/home".
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Dispatcher para redirigir las peticiones
	RequestDispatcher dispatcher = null;
	// DAO para acceder a los datos de los usuarios
	UserDAO usuarioDAO = new UserAccess();
	
	/**
	 * Método doGet que se ejecuta al recibir una petición GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Obtiene la sesión actual
		HttpSession session = request.getSession(false);
		// Si la sesión es nula, redirige a "login"
		if(session == null) {
			response.sendRedirect("login");
		} else {
			// Obtiene el ID del usuario de la sesión
			Object id = session.getAttribute("id");
			// Si el ID es nulo, redirige a "login"
			if(id == null) {
				response.sendRedirect("login");
			} else {	
				// Obtiene el usuario de la base de datos y lo guarda en la sesión
				int id2 = (int) session.getAttribute("id");
				User usuario = usuarioDAO.obtenerUsuarioPorID(id2);
				session.setAttribute("usuario", usuario);
				// Redirige a "home.jsp"
				dispatcher = request.getRequestDispatcher("home.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}


	
       

}
