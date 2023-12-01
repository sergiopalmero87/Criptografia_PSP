package mainAES;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 3526483192707204517L;
	
	private String nombre, password;

	public Usuario() {
		super();
	}

	public Usuario(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", password=" + password + "]";
	}
	
	

}
