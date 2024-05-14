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

/**
 * Servlet RegistrarServlet que maneja las peticiones a la ruta "/registrarse".
 */
@WebServlet("/registrarse")
public class RegistrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Método doGet que se ejecuta al recibir una petición GET.
	 * Este método se encarga de redirigir al usuario a la página de login.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
       
	/**
	 * Método doPost que se ejecuta al recibir una petición POST.
	 * Este método se encarga de registrar un nuevo usuario en la base de datos.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User usuario = null;
		UserDAO usuarioDAO = new UserAccess();
		RequestDispatcher dispatcher = null;
		
		// Obtiene los datos del formulario de registro
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String contrasena = request.getParameter("contrasena");
		int operacion;
		
		try {
			// Crea un nuevo usuario y lo guarda en la base de datos
			usuario = new User(nombre,apellido,correo,contrasena,0.0);
			operacion = usuarioDAO.guardar(usuario);
			
			// Si la operación fue exitosa, redirige al usuario a la página de inicio
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
