package mainAES;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import usuario.Usuario;

public class MainAES {

	public static void main(String args[]) throws NoSuchAlgorithmException {

		// Creamos la contraseña a partir de la cual queremos crear su resumen.
		// Notese que lo pasamos a bytes ya que se necesita que la
		// información este así para poder crear su resumen hash.
		byte[] password1 = "123456789".getBytes();
		byte[] password2 = "987654321".getBytes();
		byte[] password3 = "0000".getBytes();

		
		// Creamos un objeto MessageDigest a través del método estático
		// getInstance() al que se le pasa el tipo de algoritmo que vamos a
		// utilizar.
		MessageDigest md = MessageDigest.getInstance("SHA-512");

		md.update(password1);

		md.update(password2);

		md.update(password3);
		

		// Ahora ejecutamos el método "digest()" de nuestro
		// objeto MessageDigest para obtener el resumen, que también
		// será una cadena de bytes.
		System.out.println("Creando las passwords hasheadas...");
		
		byte[] password1Hasheada = md.digest();
		String mensajePassword1 = new String(password1Hasheada);

		byte[] password2Hasheada = md.digest();
		String mensajePassword2 = new String(password1Hasheada);

		byte[] password3Hasheada = md.digest();
		String mensajePassword3 = new String(password1Hasheada);
		

		// Podemos pasarlo a la codificación BASE 64 si queremos reducir el alfa
		// alfabeto resultante. Puede ser util si queremos guardar la información
		// o enviar la información.
		String password1_HashBase64 = Base64.getEncoder().encodeToString(password1Hasheada);

		String password2_HashBase64 = Base64.getEncoder().encodeToString(password2Hasheada);

		String password3_HashBase64 = Base64.getEncoder().encodeToString(password3Hasheada);

		System.out.println("Contraseñas hasheadas creadas\n");

		List<Usuario> listaUsuarios = new ArrayList<>();

		// Usuarios en memoria con su nombre y contraseña hasheada
		Usuario u1 = new Usuario("Sergio", password1_HashBase64);
		Usuario u2 = new Usuario("David", password2_HashBase64);
		Usuario u3 = new Usuario("Iris", password3_HashBase64);

		// Guardamos los usuarios en una lista para poder acceder a ellos.
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
		listaUsuarios.add(u3);
		
		System.out.println(listaUsuarios);

		// Con esta variable controlamos las veces que el usuario puede poner los datos.
		int intentos = 3;

		// Pedimos los datos al usuario para que pueda acceder.
		do {
			Scanner sc = new Scanner(System.in);

			System.out.println("NAME: ");
			String nombreUser = sc.nextLine();

			//TODO: Arreglar esto.
			System.out.println("PASSWORD: ");
			String passwordUser = sc.nextLine();
			
			byte[] passwordUserByte = passwordUser.getBytes();
			md.update(passwordUserByte);
			byte[] passwordUserHasheada = md.digest();
			String mensajePasswordUser = new String(passwordUserHasheada);
			String passwordUserHashBase64 = Base64.getEncoder().encodeToString(passwordUserHasheada);
			
			
			// Por cada usuario que haya en la lista...
			for (Usuario u : listaUsuarios) {
				if (nombreUser.equals(u.getNombre())){
					System.out.println("Hola " + u.getNombre());
					System.out.println(passwordUser);
					System.out.println(passwordUserHashBase64);
					System.out.println(u.getPassword());

					try {
						// Creamos el generador a partir del cual obtendremos la clave simetrica.
						KeyGenerator generador = KeyGenerator.getInstance("AES");
						System.out.println("Se ha obtenido el generador de claves");

						// Generamos la clave simetrica.
						SecretKey clave = generador.generateKey();
						// Si se hiciera otra vez, obtendria otra clave DIFERENTE
						System.out.println("Se ha obtenido la clave");

						// Objeto que nos permitira encriptar o desencriptar a partir de una
						// clave
						Cipher cifrador = Cipher.getInstance("AES");
						System.out.println("Hemos obtenido el cifrador/descifrador\n");

						// Creamos el menu con su variable opcion para controlar lo que pide el usuario.
						// Creamos tambien la variable fraseOriginal para guardar en memoria la frase
						// del usuario.
						// Lo metemos todo dentro de un do-while para controlar la variable opcion.
						int opcion;
						String fraseOriginal = null;
						byte[] bytesFraseCifrada = null;

						do {
							System.out.println("---MENU---");
							System.out.println("1. Cifrar frase:");
							System.out.println("2. Descifrar frase:");
							System.out.println("3. Salir del programa:\n");

							System.out.println("Elige una opcion:");
							opcion = sc.nextInt();
							sc.nextLine();

							String fraseCifrada, fraseDescifrada;

							switch (opcion) {
							case 1:
								System.out.println("Escribe tu frase para encriptar:");
								fraseOriginal = sc.nextLine();
								// Ahora el cifrador lo configuramos para que use la clave simetrica
								// para encriptar
								cifrador.init(Cipher.ENCRYPT_MODE, clave);

								// El cifrador trabaja con bytes.
								byte[] bytesFraseOriginal = fraseOriginal.getBytes();
								System.out.println("Cifrando la frase...");

								// El cifrador devuelve una cadena de bytes
								bytesFraseCifrada = cifrador.doFinal(bytesFraseOriginal);
								fraseCifrada = new String(bytesFraseCifrada);
								System.out.println("Frase cifrada: " + fraseCifrada);
								break;

							case 2:
								// Ahora el cifrador lo configuramos para que use la clave simétrica
								// para desencriptar
								cifrador.init(Cipher.DECRYPT_MODE, clave);
								byte[] bytesFraseDescifrada = cifrador.doFinal(bytesFraseCifrada);
								fraseDescifrada = new String(bytesFraseDescifrada);
								System.out.println("Descifrando la frase...");
								System.out.println("Frase descifrada: " + fraseDescifrada);
								break;

							case 3:
								System.out.println("Saliendo del programa...");
								System.out.println("Adios.");
								break;

							default:
								System.out.println("Introduzca una opción válida.");
							}

						} while (opcion != 3);
						sc.close();
						return;

						// Simplificamos las excepciones capturando GeneralSecurityException
					} catch (GeneralSecurityException gse) {
						System.out.println("Algo ha fallado.." + gse.getMessage());
						gse.printStackTrace();
					}
				}
			}
			System.out.println("Usuario no encontrado");
			intentos--;
			System.out.println("Te quedan " + intentos + " intentos");

			if (intentos == 0) {
				System.out.println("Fin del programa. Adios");
				return;
			}

		} while (intentos > 0);
	}

	
	public String generarHash(String passwordUser) throws NoSuchAlgorithmException {

		byte[] password = passwordUser.getBytes();

		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(password);

		byte[] passwordHasheada = md.digest();
		String mensajePassword = new String(passwordHasheada);
		System.out.println("Resumen password hash: " + mensajePassword);

		String password_HashBase64 = Base64.getEncoder().encodeToString(passwordHasheada);
		return password_HashBase64;

	}

}
