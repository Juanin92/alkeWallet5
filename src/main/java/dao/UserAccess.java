package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.*;
import model.User;

/**
 * Clase que implementa la interfaz UserDAO.
 * Esta clase se encarga de realizar las operaciones de base de datos para los usuarios.
 */
public class UserAccess implements UserDAO{
	
	private String sql = "";
	private Connection con = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	private User usuario = null;
	
	/**
	 * Método para guardar un usuario en la base de datos.
	 * @param usuario El usuario a guardar.
	 * @return El número de filas afectadas.
	 */
	@Override
	public int guardar(User usuario) {
		int row = 0;
		// Consulta SQL para insertar un usuario
		sql = "insert into usuarios(nombre,apellido,correo,contrasena,saldo) values (?,?,?,?,?)";

		con = DBConnection.getConexion();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, usuario.getNombre());
			pstm.setString(2, usuario.getApellido());
			pstm.setString(3, usuario.getCorreo());
			pstm.setString(4, usuario.getContrasena());
			pstm.setDouble(5, usuario.getSaldo());
			row = pstm.executeUpdate();
			pstm.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return row;
	}
	
	/**
	 * Método para depositar dinero en la cuenta de un usuario.
	 * @param monto El monto a depositar.
	 * @param usuarioID El ID del usuario.
	 * @return El número de filas afectadas.
	 */
	@Override
	public int depositar(Double monto, int usuarioID) {
		int row = 0;
		// Consulta SQL para actualizar el saldo de un usuario
		sql = "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?";
		con = DBConnection.getConexion();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDouble(1, monto);
			pstm.setInt(2, usuarioID);
			row = pstm.executeUpdate();
			pstm.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return row;
	}
	
	/**
	 * Método para retirar dinero de la cuenta de un usuario.
	 * @param monto El monto a retirar.
	 * @param usuarioID El ID del usuario.
	 * @return El número de filas afectadas.
	 */
	@Override
	public int retirar(Double monto, int usuarioID) {
		int row = 0;
		sql = "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?";
		con = DBConnection.getConexion();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setDouble(1, monto);
			pstm.setInt(2, usuarioID);
			row = pstm.executeUpdate();
			pstm.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return row;
	}
	
	/**
	 * Método para obtener un usuario de la base de datos por correo y contraseña.
	 * @param correo El correo del usuario.
	 * @param contrasena La contraseña del usuario.
	 * @return El usuario obtenido.
	 */
	@Override
	public User obtenerUsuario(String correo, String contrasena) {
		sql = "select * from usuarios where correo = ? and contrasena = ?";
		con = DBConnection.getConexion();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, correo);
			pstm.setString(2, contrasena);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				usuario = new User();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setSaldo(rs.getDouble("saldo"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return usuario;
	}

	/**
	 * Método para obtener un usuario de la base de datos por ID.
	 * @param usuarioID El ID del usuario.
	 * @return El usuario obtenido.
	 */
	@Override
	public User obtenerUsuarioPorID(int usuarioID) {
		sql = "select * from usuarios where id = ?";
		con = DBConnection.getConexion();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, usuarioID);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				usuario = new User();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setContrasena(rs.getString("contrasena"));
				usuario.setSaldo(rs.getDouble("saldo"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return usuario;
	}

}
