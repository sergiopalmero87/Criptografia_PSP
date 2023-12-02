package usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;



public class Usuario {

    private String nombre;
    private String password;
     
    
    
    public Usuario(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}


	//Getters and Setters
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


	//Equals and hashcode
    @Override
	public int hashCode() {
		return Objects.hash(nombre, password);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(password, other.password);
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", password=" + password + "]";
	}
	
	
	public String generarHash(String passwordUser) throws NoSuchAlgorithmException{
		
		byte[] password = passwordUser.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(password);
		
		byte[] passwordHasheada = md.digest();
		String mensajePassword = new String(passwordHasheada);
		System.out.println("Resumen password 1 hash: " + mensajePassword);
		
		String password_HashBase64 = Base64.getEncoder().encodeToString(passwordHasheada);
		return password_HashBase64;
		
		
	}
	
	
    
}
