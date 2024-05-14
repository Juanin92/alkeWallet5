package model;

/**
 * Clase User que representa un usuario en el sistema.
 * @param nombre Nombre del usuario.
 * @param apellido Apellido del usuario.
 * @param correo Correo electrónico del usuario.
 * @param contrasena Contraseña del usuario.
 * @param saldo Saldo del usuario.
 */
public class User {

	private int id;
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasena;
	private Double saldo;
	
	//cosntructor por defecto
	public User() {
	}
	
	//constructor de la clase sin ID 
	public User(String nombre, String apellido, String correo, String contrasena, Double saldo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasena = contrasena;
		this.saldo = saldo;
	}

	public User(int id, String nombre, String apellido, String correo, String contrasena, Double saldo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasena = contrasena;
		this.saldo = saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", contrasena=" + contrasena + ", saldo=" + saldo + "]";
	}
}
