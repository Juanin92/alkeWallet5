package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;

import dao.UserAccess;
import dao.UserDAO;

@WebServlet("/registrarse")
public class RegistrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User usuario = null;
		UserDAO usuarioDAO = new UserAccess();
		RequestDispatcher dispatcher = null;

		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		int operacion;
		
		try {
			usuario = new User(nombre,apellido,correo,contrasena,0.0);
			operacion = usuarioDAO.guardar(usuario);
			if(operacion > 0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("index.jsp");
			} else {
				request.setAttribute("status", "failed");
			}
		dispatcher.forward(request, response);
		} catch (Exception e)  {
			e.printStackTrace();
		}
		
	}

}
