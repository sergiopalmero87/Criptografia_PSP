package mainAES;

import java.security.GeneralSecurityException;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainAES {

	public static void main(String args[]) {

		try {
			Scanner sc = new Scanner(System.in);

			// Creamos el generador a partir del cual obtendremos la clave simetrica.
			KeyGenerator generador = KeyGenerator.getInstance("AES");
			System.out.println("Paso 1: Se ha obtenido el generador de claves");

			// Generamos la clave simetrica.
			SecretKey clave = generador.generateKey();
			// Si se hiciera otra vez, obtendria otra clave DIFERENTE
			System.out.println("Paso 2: Se ha obtenido la clave");

			// Objeto que nos permitira encriptar o desencriptar a partir de una
			// clave
			Cipher cifrador = Cipher.getInstance("AES");
			System.out.println("Paso 3: Hemos obtenido el cifrador/descifrador");

			// Creamos el menu con su variable opcion para controlar lo que pide el usuario.
			//Creamos tambien la variable fraseOrigianl para guardar en memoria la frase del usuario.
			//Lo metemos todo dentro de un do-while para controlar la variable opcion.
			int opcion;
            String fraseOriginal = null; // Variable para almacenar la frase original
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
					System.out.println("Frase original: " + fraseOriginal);
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
					break;
					
				default:
					System.out.println("Introduzca una opción válida.");
				}
				
			} while (opcion != 3);

			// Simplificamos las excepciones capturando GeneralSecurityException
		} catch (GeneralSecurityException gse) {
			System.out.println("Algo ha fallado.." + gse.getMessage());
			gse.printStackTrace();
		}
	}

	public static void menu() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("---MENU---");
			System.out.println("1. Cifrar frase:");
			System.out.println("2. Descifrar frase:");
			System.out.println("3. Salir del programa:\n");

			System.out.println("Elige una opcion:");
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:

			}
		} while (opcion > 3 || opcion < 1);
	}

}
