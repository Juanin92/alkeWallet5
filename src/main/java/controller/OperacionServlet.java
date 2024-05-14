package controller;

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

@WebServlet("/operacion")
public class OperacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO usuarioDAO = new UserAccess();
	User usuario = null;
	int exitoso = 0;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				String operacionDepositar = request.getParameter("operacionDepositar");
				String operacionRetirar = request.getParameter("operacionRetirar");
				
				HttpSession session = request.getSession(false);
				int id = (int) session.getAttribute("id");
				
				usuario = (User) session.getAttribute("usuario");
		
				if (operacionDepositar != null) {
					Double monto = Double.parseDouble(request.getParameter("monto"));
					exitoso = usuarioDAO.depositar(monto, id);
					if(exitoso > 0) {
						session.setAttribute("status", "success");
						response.sendRedirect("home");
					} else {
						session.setAttribute("status", "failed");
						response.sendRedirect("home");
					}
				} else if (operacionRetirar != null) {
					Double monto = Double.parseDouble(request.getParameter("monto"));
					if(usuario.getSaldo() >= monto) {
						exitoso = usuarioDAO.retirar(monto, id);
						if(exitoso > 0) {
							session.setAttribute("status", "success");
							response.sendRedirect("home");
						} else {
							session.setAttribute("status", "failed");
							response.sendRedirect("home");
						}
					} else {
						session.setAttribute("status", "failed");
						response.sendRedirect("home");
					}
					
				}
		}
	

}
