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
 * Servlet LoginServlet que maneja las peticiones a la ruta "/login".
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher = null;  
	UserDAO usuarioDAO = new UserAccess();
	
	/**
	 * Método doGet que se ejecuta al recibir una petición GET.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirige a "index.jsp"		
		dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * Método doPost que se ejecuta al recibir una petición POST.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Obtiene el correo y la contraseña del formulario
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		// Obtiene el usuario de la base de datos
		User usuario = usuarioDAO.obtenerUsuario(correo, contrasena);
		
		// Si el usuario existe, inicia una sesión y redirige a "home"
		if(usuario != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("id", usuario.getId());
			response.sendRedirect("home");
		} else {
			// Si el usuario no existe, muestra un mensaje de error y redirige a "index.jsp"
			request.setAttribute("status", "failed");
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
}
}