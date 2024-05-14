package dao;

import model.User;

/**
 * Interfaz UserDAO que define los métodos que deben ser implementados para el acceso a datos de los usuarios.
 */
public interface UserDAO {

	public int guardar(User usuario);
	public int depositar(Double monto, int usuarioID);
	public int retirar(Double monto, int usuarioID);
	public User obtenerUsuario(String correo, String contrasena);
	public User obtenerUsuarioPorID(int usuarioID);
}
