package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet LogoutServlet que maneja las peticiones a la ruta "/logout".
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Método doGet que se ejecuta al recibir una petición GET.
	 * Este método se encarga de invalidar la sesión actual y redirigir al usuario a la página de login.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtiene la sesión actual
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login");
	}
}
