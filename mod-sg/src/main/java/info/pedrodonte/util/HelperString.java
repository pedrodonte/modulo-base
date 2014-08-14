package info.pedrodonte.util;

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

}
