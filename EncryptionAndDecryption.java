import java.util.*;

public class EncryptionAndDecryption {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      ArrayList<Integer> specials = new ArrayList<Integer>();
      System.out.print("Enter a sentence to be translated to ASCII: ");
      String word = console.nextLine();
      System.out.print("Input a short word to used as the key (Only characters): ");
      String key = console.nextLine();
      while (word.length() > key.length()) {
         key += key;
      }
      String encryption = encrypt(word, key, specials);
      System.out.println("Encrypted text: " + encryption);
      
      System.out.print("\nWould you like to decrypt? (y/n): ");
      String decision = console.nextLine();
      if (decision.equalsIgnoreCase("y")) {
         String decryption = decrypt(encryption, key, specials);
         System.out.println("Decrypted text: " + decryption);
      }
   }
   
   public static String encrypt(String word, String key, ArrayList<Integer> specials) {
      String encryption = "";
      for (int i = 0; i < word.length(); i++) {
         int wordNum = (int)word.charAt(i);
         int keyNum = (int)key.charAt(i);
         int sumChar = wordNum + keyNum;
         if (sumChar >= 256) {
            sumChar -= 256;
            specials.add(sumChar);
         }
         encryption += Character.toString((char)sumChar);
      }
      return encryption;
   }
   
   public static String decrypt(String encryption, String key, ArrayList<Integer> specials) {
      String decryption = "";
      for (int i = 0; i < encryption.length(); i++) {
         int wordNum = (int)encryption.charAt(i);
         if (specials.contains(wordNum)) {
            wordNum += 256;
         }
         int keyNum = (int)key.charAt(i);
         int sumChar = wordNum - keyNum;
         decryption += Character.toString((char)sumChar);
      }
      return decryption;
   }
}