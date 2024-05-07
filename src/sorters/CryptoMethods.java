package sorters;

import javax.crypto.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptoMethods {


  /*  public static void encryptWithAES(SecretKey key) {
        try {
            String algorithm = "AES";
            String encryptedVal = encrypt(algorithm, aesInput, key);
            decrypt(algorithm, encryptedVal, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public static void encryptWithDES(SecretKey key) {
        try {
            String algorithm = "DES";
            String encryptedVal = encrypt(algorithm, otherInput, key);
            System.out.println(decrypt(algorithm, encryptedVal, key));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public static void encryptWithDESede(SecretKey key) {
        try {
            String algorithm = "DESede";
            String encryptedVal = encrypt(algorithm, otherInput, key);
            decrypt(algorithm, encryptedVal, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public static void encryptWithBlowfish(SecretKey key) {
        try {
            String algorithm = "Blowfish";
            String encryptedVal = encrypt(algorithm, otherInput, key);
            System.out.println(decrypt(algorithm, encryptedVal, key));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                 InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }*/

    public static String encrypt(String algorithm, String input, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String algorithm, String cipherText, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
}
