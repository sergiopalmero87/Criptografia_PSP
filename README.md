# Actividad de criptografía utilizando Java para 2ºDAM.

## Descripción.

Aprender a manejar la criptografía simétrica y asimétrica, así como los algoritmos hash.

## Pautas de elaboración.

### Requerimiento 1

Se pide hacer una aplicación que encripte frases introducidas por el usuario de manera simétrica.

La aplicación mostrará el siguiente menú:

- Salir del programa

- Encriptar frase

- Desencriptar frase

Con la opción 1 el programa le pedirá al usuario una frase, la encriptará y la guardará en memoria.

Con la opción 2 el programa mostrará la frase desencriptándola.

Solo se guardará una frase al mismo tiempo. Se utilizará un método de encriptación simétrico.

### Requerimiento 2

Se pide agregar seguridad a la aplicación para poder entrar. El programa arrancará con 3 objetos usuario que tendrá su nombre de usuario y su contraseña “hasheada”. Los objetos permanecerán en memoria durante todo el programa.

Antes de mostrar el menú, el programa pedirá que se introduzca el nombre del usuario y su contraseña (sin “hashear”), en caso de que sea correcto, se mostrará el menú y un mensaje de bienvenida al usuario con su nombre, en caso contrario se le volverá a pedir hasta un máximo de tres veces. Si en tres intentos no se ha conseguido introducir bien los datos de ningún usuario registrado, la aplicación se detendrá.


