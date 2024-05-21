import javax.crypto.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.LinkedList;

public class CryptoProject {

    static LinkedList<Integer> copiedList;
    static String input;
    static String algorithm;
    static SecretKey key;

    public static void main(String[] args) throws NoSuchAlgorithmException {
        algorithm = args[0];
        Integer keyLen = Integer.parseInt(args[1]);
        String outFile = args[2];
        Integer iterations = Integer.parseInt(args[3]);
        input = generateRandomString(algorithm == "AES" ? 128 : 64);
        key = generateKey(keyLen, algorithm);

        LinkedList<Long> evaluationTimes = null;
        try {
            evaluationTimes = getEvaluationTimes(iterations);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        writeLinkedListToFile(outFile, evaluationTimes);
    }

    public static String generateRandomString(int byteSize) {
        byte[] randomBytes = new byte[byteSize];
        new SecureRandom().nextBytes(randomBytes);
        return new String(randomBytes, StandardCharsets.UTF_8);
    }

    private static LinkedList<Long> getEvaluationTimes(int iterations) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        LinkedList<Long> evaluationTimes = new LinkedList<>();
        long startTime;
        long endTime;

        for (int i = 0; i < iterations; i++) {
            startTime = System.nanoTime();
            encrypt(algorithm, input, key);
            endTime = System.nanoTime();
            evaluationTimes.add(endTime-startTime);
            copiedList = null;
        }

        return evaluationTimes;
    }

    private static <E> void writeLinkedListToFile(String fileName, LinkedList<E> list) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Iteration" + " " + "Time" + "\n");
            for (int i = 1; i < list.size() + 1; i++) {
                writer.write(i + " " + list.get(i - 1) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Couldn't write to outfile");
        }
    }

    public static SecretKey generateKey(int n, String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static String encrypt(String algorithm, String input, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }
}
