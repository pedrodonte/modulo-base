package info.pedrodonte.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public class EncriptadorSHA {

	public static void main(String[] args) throws GeneralSecurityException {
		System.out.println(getSha256("password"));

	}

	public static String getSha256(String convertme) throws GeneralSecurityException {
		final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
		MessageDigest md = null;
		md = MessageDigest.getInstance("SHA-256");
		byte[] buf = md.digest(convertme.getBytes());
		char[] chars = new char[2 * buf.length];
		for (int i = 0; i < buf.length; ++i) {
			chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
			chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
		}
		return new String(chars);
	}

}
