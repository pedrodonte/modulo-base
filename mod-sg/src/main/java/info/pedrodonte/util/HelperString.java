package info.pedrodonte.util;

import java.nio.charset.Charset;
import java.util.Random;

public class HelperString {

	private static final String ALFABETO = "098765ABCDEF";
	private static final int LARGO_DEFECTO = 6;

	public static String generarClaveAleatorea(int largo) {
		String claveGenerada = "";

		for (int i = 0; i < largo; i++) {
			claveGenerada += generarCaracter();
		}

		return claveGenerada;
	}

	public static String generarClaveAleatorea() {
		return generarClaveAleatorea(LARGO_DEFECTO);
	}

	private static char generarCaracter() {
		Random rnd = new Random();
		return ALFABETO.charAt(rnd.nextInt(ALFABETO.length()));
	}

	public static void main(String[] args) {
		System.out.println(generarClaveAleatorea());
	}

	public static String cambioCharsetToUTF8(String cadena) {
		String resultado = null;
		// Instancias de charset
		Charset utf8charset = Charset.forName("UTF-8");
		Charset iso88591charset = Charset.forName("ISO-8859-1");
		// constructor de string
		resultado = new String(cadena.getBytes(iso88591charset), utf8charset);
		return resultado;
	}

}
