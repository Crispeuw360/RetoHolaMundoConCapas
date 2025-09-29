package utilidades;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Clase de utilidades con métodos estáticos para lectura y validación de datos
 * desde la entrada estándar, conversión de fechas y operaciones con ficheros de
 * objetos.
 */
public class Utilidades {
	/**
	 * Calcula el número de objetos almacenados en un fichero binario
	 * serializado.
	 *
	 * @param fich Fichero a leer
	 * @return Número de objetos encontrados en el fichero; 0 si no existe o está vacío
	 */
	public static int calculoFichero(File fich){
	 	int cont=0;
	 	if (fich.exists()){
		 	FileInputStream fis=null;
		 	ObjectInputStream ois=null;
		 	try{
		 		fis=new FileInputStream(fich);
		 		ois=new ObjectInputStream(fis);
	
		 		Object aux=ois.readObject();
	
		 		while (aux!=null){
		 			cont++;
		 			aux=ois.readObject();
		 		}
		 		
	
		 	}catch(EOFException e1){
			//	System.out.println("Has acabado de leer, tienes "+cont+" objetos");
				
		 	}catch (Exception e2){
				 e2.printStackTrace();
		 	}
		 	
		 	
		 	try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar los flujos");
				
			}
	 	}
	 	return cont;
	 }
	/**
	 * Convierte una fecha a cadena con formato dd/MM/yyyy.
	 *
	 * @param fecha Fecha a formatear
	 * @return Cadena con la fecha formateada
	 */
	public static String fechaToString(LocalDate fecha) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String wfecha;
		
		wfecha = fecha.format(formateador);
		
		return wfecha;
	}
	/**
	 * Lee una fecha desde la entrada estándar con formato dd/MM/yyyy.
	 *
	 * @return Fecha válida introducida por el usuario
	 */
	public static LocalDate leerFechaDMA() {
		boolean error;
		LocalDate date = null;
		String dateString;
		DateTimeFormatter formateador=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		do{
			error=false;
			dateString=introducirCadena();
			try{
				date=LocalDate.parse(dateString, formateador);
			}catch(DateTimeParseException e){
				System.out.println("Error, introduce una fecha en formato dd/mm/aaaa ");
				error=true;
			}
		}while (error);
		return date;
	}

	/**
	 * Lee una fecha desde la entrada estándar con formato yyyy/MM/dd.
	 *
	 * @return Fecha válida introducida por el usuario
	 */
	public static LocalDate leerFechaAMD() {
		boolean error;
		LocalDate date = null;
		String dateString;
		DateTimeFormatter formateador=DateTimeFormatter.ofPattern("yyyy/MM/dd");
		do{
			error=false;
			dateString=introducirCadena();
			try{
				date=LocalDate.parse(dateString, formateador);
			}catch(DateTimeParseException e){
				System.out.println("Error, introduce una fecha en formato yyyy/MM/dd ");
				error=true;
			}
		}while (error);
		return date;
	}
	/**
	 * Lee un carácter y valida que sea uno de los dos permitidos.
	 * La comparación se realiza en mayúsculas.
	 *
	 * @param opt1 Primera opción válida
	 * @param opt2 Segunda opción válida
	 * @return Carácter leído (en mayúscula) que coincide con alguna opción
	 */
	public static char leerChar(char opt1, char opt2) {
		char letra=' ';
		String cadena;
		boolean error;
		do{
			error=false;
			cadena=introducirCadena();
			if (cadena.length()!=1){
				System.out.println("Error, introduce un único caracter: ");
				error=true;
			}
			else{
				letra=cadena.charAt(0);
				letra=Character.toUpperCase(letra);
				if (letra!=opt1 && letra!=opt2){
					System.out.println("Error, la opción introducida no es correcta, introduce "+ opt1+ " o "+ opt2);
					error=true;
				}
			}
		}while (error);
			
		return letra;
	}

	/**
	 * Lee un carácter sin validación adicional.
	 *
	 * @return Carácter leído
	 */
	public static char leerChar() {
		char letra=' ';
		String cadena;
		boolean error;
		do{
			error=false;
			cadena=introducirCadena();
			if (cadena.length()!=1){
				System.out.println("Error, introduce un único caracter: ");
				error=true;
			}
		}while (error);
		letra=cadena.charAt(0);
		return letra;
	}

	/**
	 * Lee un número de tipo float.
	 *
	 * @return Valor float introducido por el usuario
	 */
	public static float leerFloat() {
		float num = 0;
		boolean error;
		do{
			error=false;
			try{
				num=Float.parseFloat(introducirCadena());
			}catch (NumberFormatException e){
				System.out.println("Valor no numérico. Introduce de nuevo:");
				error=true;
			}
		}while (error);
		return num;
	}

	/**
	 * Lee un número float mostrando un mensaje y validando que esté en el rango
	 * [min, max].
	 *
	 * @param message Mensaje a mostrar antes de la lectura
	 * @param min Valor mínimo permitido
	 * @param max Valor máximo permitido
	 * @return Valor float válido en el rango especificado
	 */
	public static float leerFloat(String message, float min, float max) {
		float num = 0;
		boolean error;
		System.out.println(message);
		do{
			error=false;
			try{
				num=Float.parseFloat(introducirCadena());
				
			}catch (NumberFormatException e){
				System.out.println("Valor no num�rico. Introduce de nuevo:");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.println("Número fuera de rango, introduce número entre "+ min+ " y "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}

	/**
	 * Lee un número float validando que esté en el rango [min, max].
	 *
	 * @param min Valor mínimo permitido
	 * @param max Valor máximo permitido
	 * @return Valor float válido en el rango especificado
	 */
	public static float leerFloat(float min, float max) {
		float num = 0;
		boolean error;
		do{
			error=false;
			try{
				num=Float.parseFloat(introducirCadena());
				
			}catch (NumberFormatException e){
				System.out.println("Valor no numérico. Introduce de nuevo:");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.println("Número fuera de rango, introduce n� entre "+ min+ " y "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}
	/**
	 * Lee un número de tipo double.
	 *
	 * @return Valor double introducido por el usuario
	 */
	public static double leerDouble() { 
		double num = 0;
		boolean error;

		do{
			error=false;
			try{
				num=Double.parseDouble(introducirCadena());
			}catch (NumberFormatException e){
				System.out.print("[ERROR] Valor no numerico.\nIntroduce de nuevo: ");
				error=true;
			}
		}while (error);
		return num;
	}
	/**
	 * Lee un número double validando que esté en el rango [min, max].
	 *
	 * @param min Valor mínimo permitido
	 * @param max Valor máximo permitido
	 * @return Valor double válido en el rango especificado
	 */
	public static double leerDouble(double min, double max) { //
		double num = 0;
		boolean error;

		do{
			error=false;
			try{
				num=Double.parseDouble(introducirCadena());
			}catch (NumberFormatException e){
				System.out.print("[ERROR] Valor no numerico. Introduce de nuevo: ");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.print("[ERROR] Numero fuera de rango.\nIntroduce uno entre "+ min+ " y "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}
	/**
	 * Lee un número double mostrando un mensaje y validando que esté en el rango
	 * [min, max].
	 *
	 * @param message Mensaje a mostrar antes de la lectura
	 * @param min Valor mínimo permitido
	 * @param max Valor máximo permitido
	 * @return Valor double válido en el rango especificado
	 */
	public static double leerDouble(String message, double min, double max) { 
		double num = 0;
		boolean error;

		System.out.println(message);
		do{
			error=false;
			try{
				num=Double.parseDouble(introducirCadena());

			}catch (NumberFormatException e){
				System.out.print("[ERROR] Valor no numerico. Introduce de nuevo: ");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.print("[ERROR] Numero fuera de rango.\nIntroduce numero entre "+ min+ " y "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}

	/**
	 * Lee un número entero mostrando un mensaje y validando que esté en el rango
	 * [min, max].
	 *
	 * @param message Mensaje a mostrar antes de la lectura
	 * @param min Valor mínimo permitido
	 * @param max Valor máximo permitido
	 * @return Valor int válido en el rango especificado
	 */
	public static int leerInt(String message, int min, int max) {
		int num = 0;
		boolean error;
		System.out.println(message);
		do{
			error=false;
			try{
				num=Integer.parseInt(introducirCadena());
				
			}catch (NumberFormatException e){
				System.out.println("Valor no numérico. Introduce de nuevo:");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.println("Número fuera de rango, introduce número entre "+ min+ " y "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}

	/**
	 * Lee un número entero validando que esté en el rango [min, max].
	 *
	 * @param min Valor mínimo permitido
	 * @param max Valor máximo permitido
	 * @return Valor int válido en el rango especificado
	 */
	public static int leerInt(int min, int max) {
		int num = 0;
		boolean error;
		do{
			error=false;
			try{
				num=Integer.parseInt(introducirCadena());
				
			}catch (NumberFormatException e){
				System.out.println("Valor no num�rico. Introduce de nuevo:");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.println("Número fuera de rango, introduce número entre "+ min+ " y "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}

	/**
	 * Lee un número entero sin restricciones de rango.
	 *
	 * @return Valor int introducido por el usuario
	 */
	public static int leerInt() {
		int num = 0;
		boolean error;
		do{
			error=false;
			try{
				num=Integer.parseInt(introducirCadena());
			}catch (NumberFormatException e){
				System.out.println("Valor no numérico. Introduce de nuevo:");
				error=true;
			}
		}while (error);
		return num;
	}

	/**
	 * Lee una cadena de texto desde la entrada estándar.
	 *
	 * @return Cadena introducida por el usuario
	 */
	public static String introducirCadena() {
		String cadena = "";
		boolean error;
		InputStreamReader entrada =new InputStreamReader(System.in);
		BufferedReader teclado= new BufferedReader(entrada);
		do{
			error=false;
			try {
				cadena=teclado.readLine();
			} catch (IOException e) {
				System.out.println("Error en la entrada de datos");
				error=true;
			}
		}while (error);
		return cadena;
	}
}
