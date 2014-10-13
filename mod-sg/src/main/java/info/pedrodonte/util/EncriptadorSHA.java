package info.pedrodonte.util;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
 
public class EncriptadorSHA {
     
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String passwordToHash = "password";
         
        String securePassword = getSha256(passwordToHash);
        System.out.println(securePassword);
        System.out.println(securePassword.length());
         
    }
 
    public static String getSha256(String passwordToHash) throws NoSuchAlgorithmException
    {
        String generatedPassword = null;
        String salt = "SEMILLA";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     
    //Add salt
    private static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
}
