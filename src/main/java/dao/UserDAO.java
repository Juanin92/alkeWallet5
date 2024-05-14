package dao;

import model.User;

public interface UserDAO {

	public int guardar(User usuario);
	public int depositar(Double monto, int usuarioID);
	public int retirar(Double monto, int usuarioID);
	public User obtenerUsuario(String correo, String contrasena);
	public User obtenerUsuarioPorID(int usuarioID);
}
