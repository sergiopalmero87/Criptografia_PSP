package MainAES;

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

import Encriptar.Encriptar;
import Usuario.Usuario;

public class MainAES {

	public static void main(String args[]) throws NoSuchAlgorithmException {

		Encriptar e = new Encriptar();

		// OTRA FORMA DE HACERLO:
		/*
		 * // Creamos los objetos Usuario Usuario user1 = new Usuario("Tony", "admin");
		 * Usuario user2 = new Usuario("Steve", "Hail Hydra"); Usuario user3 = new
		 * Usuario("Peter", "maryjane");
		 * 
		 * // convertimos las contraseñas en bytes byte[] password1 =
		 * user1.getPassword().getBytes(); byte[] password2 =
		 * user2.getPassword().getBytes(); byte[] password3 =
		 * user3.getPassword().getBytes();
		 * 
		 * // Creamos un objeto MessageDigest a través del método estático //
		 * getInstance() al que se le pasa el tipo de algoritmo que vamos a // utilizar.
		 * MessageDigest md1 = MessageDigest.getInstance("SHA-512"); MessageDigest md2 =
		 * MessageDigest.getInstance("SHA-512"); MessageDigest md3 =
		 * MessageDigest.getInstance("SHA-512");
		 * 
		 * // Actualizamos las contraseñas de los usuarios y lo preparamos para
		 * convertirlo // a hash md1.update(password1); md2.update(password2);
		 * md3.update(password3);
		 * 
		 * // Ahora ejecutamos el método "digest()" para convertirlo a hash, pero esta
		 * en // binario. byte[] password1Hasheada = md1.digest(); byte[]
		 * password2Hasheada = md2.digest(); byte[] password3Hasheada = md3.digest();
		 * 
		 * // Lo pasamos a codificación BASE 64 para que sea mas legible. // Puede ser
		 * util si queremos guardar la información o enviar la información. String
		 * password1_HashBase64 = Base64.getEncoder().encodeToString(password1Hasheada);
		 * String password2_HashBase64 =
		 * Base64.getEncoder().encodeToString(password2Hasheada); String
		 * password3_HashBase64 = Base64.getEncoder().encodeToString(password3Hasheada);
		 * 
		 * // Creamos una lista donde almacenar los usuarios. List<Usuario>
		 * listaUsuarios = new ArrayList<>();
		 * 
		 * // cambiamos las contraseñas originales por las contraseñas hasheadas
		 * user1.setPassword(password1_HashBase64);
		 * user2.setPassword(password2_HashBase64);
		 * user3.setPassword(password3_HashBase64);
		 * 
		 * // Guardamos los usuarios en una lista para poder acceder a ellos.
		 * listaUsuarios.add(user1); listaUsuarios.add(user2); listaUsuarios.add(user3);
		 */

		// Creamos la contraseña a partir de la cual queremos crear su resumen.
		// Lo pasamos a bytes ya que se necesita que la información este así para poder
		// crear su resumen hash.

		byte[] password1 = "123456789".getBytes();
		byte[] password2 = "987654321".getBytes();
		byte[] password3 = "0000".getBytes();

		// Creamos un objeto MessageDigest a través del método estático
		// getInstance() al que se le pasa el tipo de algoritmo que vamos a utilizar.

		MessageDigest md1 = MessageDigest.getInstance("SHA-512");
		MessageDigest md2 = MessageDigest.getInstance("SHA-512");
		MessageDigest md3 = MessageDigest.getInstance("SHA-512");

		// Actualizamos las contraseñas de los usuarios y lo preparamos para convertirlo
		// a hash

		md1.update(password1);

		md2.update(password2);

		md3.update(password3);

		// Ahora ejecutamos el método "digest()" para convertirlo a hash, pero esta en
		// binario.

		byte[] password1Hasheada = md1.digest();

		byte[] password2Hasheada = md2.digest();

		byte[] password3Hasheada = md3.digest();

		// Lo pasamos a codificación BASE 64 para que sea mas legible.
		// Puede ser util si queremos guardar la información o enviar la información.

		String password1_HashBase64 = Base64.getEncoder().encodeToString(password1Hasheada);

		String password2_HashBase64 = Base64.getEncoder().encodeToString(password2Hasheada);

		String password3_HashBase64 = Base64.getEncoder().encodeToString(password3Hasheada);

		// Creamos una lista donde almacenar los usuarios.

		List<Usuario> listaUsuarios = new ArrayList<>();

		// Usuarios en memoria con su nombre y contraseña hasheada

		Usuario u1 = new Usuario("Sergio", password1_HashBase64);

		Usuario u2 = new Usuario("David", password2_HashBase64);

		Usuario u3 = new Usuario("Iris", password3_HashBase64);

		// Guardamos los usuarios en una lista para poder acceder a ellos.

		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
		listaUsuarios.add(u3);

		// Con esta variable controlamos las veces que el usuario puede poner los datos.
		int intentos = 3;

		// Pedimos los datos al usuario para que pueda acceder.
		do {
			Scanner sc = new Scanner(System.in);

			System.out.println("NAME: ");
			String nombreUser = sc.nextLine();

			System.out.println("\nPASSWORD: ");
			String passwordUser = sc.nextLine();

			// Por cada usuario que haya en la lista
			// comparamos que si tanto el nombre como la contraseña hasheada es igual a lo
			// que el usuario nos da por scanner
			// entonces mostramos el menu.
			// Si no es asi tiene 3 oportunidas en total y al final el programa termina
			// solo.
			for (Usuario u : listaUsuarios) {
				if (nombreUser.equals(u.getNombre()) && u.getPassword().equals(e.generarHashUser(passwordUser))) {
					System.out.println("\n¡ Hola " + u.getNombre() + " bienvenid@ !");

					try {
						// Creamos el generador a partir del cual obtendremos la clave simetrica.
						KeyGenerator generador = KeyGenerator.getInstance("AES");

						// Generamos la clave simetrica.
						// Si se hiciera otra vez, obtendria otra clave DIFERENTE
						SecretKey clave = generador.generateKey();

						// Objeto que nos permitira encriptar o desencriptar a partir de una
						// clave
						Cipher cifrador = Cipher.getInstance("AES");

						// Creamos el menu con su variable opcion para controlar lo que pide el usuario.
						// Creamos tambien la variable fraseOriginal para guardar en memoria la frase
						// del usuario.
						// Lo metemos todo dentro de un do-while para controlar la variable opcion.
						int opcion = 0;
						String fraseOriginal = null;
						byte[] bytesFraseCifrada = null;

						do {
							menu();

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
								System.out.println("Adiós " + u.getNombre() + ".");
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
				sc.close();
				return;

			}

		} while (intentos > 0);
	}

	public static void menu() {

		System.out.println("---MENU---");
		System.out.println("1. Cifrar frase:");
		System.out.println("2. Descifrar frase:");
		System.out.println("3. Salir del programa:\n");

	}

}
