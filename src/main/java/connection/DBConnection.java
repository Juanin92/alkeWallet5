package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	private static final String URL = "jdbc:mysql://localhost:3306/alkeWallet5";
	private static final String USER = "root";
	private static final String PASSWORD = "12345678";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static Connection connection = null;
	
	
	public static Connection getConexion() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conectado");
		} catch (ClassNotFoundException | SQLException ex) {
			System.out.println("No conectado");
			ex.printStackTrace();
		}
		
		return connection;
	}
	
	public static void main(String[] args) {
		getConexion();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String consultaSQL = "select * from usuarios";
			ResultSet rs = stmt.executeQuery(consultaSQL);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String correo = rs.getString("correo");
				String contrasena = rs.getString("contrasena");
				Double saldo = rs.getDouble("saldo");
				System.out.println(id + nombre + apellido + correo + contrasena + saldo);
			}

			rs.close();
			stmt.close();
			connection.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

}
