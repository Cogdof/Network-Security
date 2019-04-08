import java.io.*;
import java.util.Scanner;

public class Main {

    static  int s[] = new int[256];
   // static byte s[] = new byte[256];
    static byte k[] = new byte[256];
    static byte key[];

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String keyStr = "";


        //=================     key 입력받기    =========================//
        do {
            System.out.println("Input the Key (1~256 Byte)");
            keyStr = input.nextLine();
        }
        while (keyStr.length() < 1 || keyStr.length() > 256);     // Key 는 1 ~ 256 Byte 만 가능.

        //입력받은 key <- 256 미만. 256 미만인경우 같은 key 내용이 반복해서 들어감.

        System.out.println("key : " + keyStr);
        System.out.println(keyStr.length());
        key = keyStr.getBytes();


        //=================     Key 초기화 및 Key 스트림 생성  =========================//

        for (int i = 0; i < 256; i++) {
            s[i] = i;
            // s[i]=  i;
            k[i] = key[i % key.length];
            System.out.println(s[i] + " / " + (char) k[i]);
        }

        int j = 0;

        for (int i = 0; i < 256; i++) {
            j = ((j + s[i] + k[i]) % 256);
            swap(i, j);
        }


        //=================     File 읽어오기    =========================//

            System.out.println("Choose mode : 1)평문 암호화      2) 암호문 복호화     3) Exit");
            int mode = input.nextInt();

            switch (mode) {

                case 1:     //평문 암호화 , file -> byte[] -> encryption -> hex

                    try {
                        File file = new File("src/plain_text.txt");
                        FileReader fileReader = new FileReader(file);
                        BufferedReader buffer = new BufferedReader(fileReader);
                        OutputStream output = new FileOutputStream("src/cipher_text.txt");
                        //String line = buffer.readLine();
                        String line = "";

                        do {

                            line = buffer.readLine();
                            System.out.println(line + "<< plain_text");

                            byte[] cipher = line.getBytes();
                            for (int x = 0; x < cipher.length; x++) {
                                System.out.print(cipher[x] + " ");
                            }
                            System.out.println("<< cipher_text");

                            byte[] result = encryption(cipher);
                            //byte[] result = decryption(cipher);       //같은 동작이다.

                            for (int x = 0; x < result.length; x++) {
                                System.out.print(result[x] + " ");
                            }
                            System.out.println("<< result");


                            String hexText = new java.math.BigInteger(result).toString(16);     //byte[] -> hex
                            output.write(hexText.getBytes());
                            output.write('\n');

                        } while (!line.equals(null));

                        buffer.close();
                        output.close();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    break;

                case 2:     //암호문 복호화 , hex -> byte -> decrypt -> show

                    try {
                        File file = new File("src/cipher_text.txt");
                        FileReader fileReader = new FileReader(file);
                        BufferedReader buffer = new BufferedReader(fileReader);
                        OutputStream output = new FileOutputStream("src/decrypt_text.txt");
                        //String line = buffer.readLine();
                        String line = "";

                        do {


                            line = buffer.readLine();
                           // System.out.println(line + "<< cipher_text");

                            byte[] decrypt = new java.math.BigInteger(line, 16).toByteArray();      //Hex-> byte[]
                           // System.out.println(decrypt.toString()+"<< decrypt_text");

                           /*
                          for (int x = 0; x < decrypt.length; x++) {
                                System.out.print(decrypt[x] + " ");
                            }
                            System.out.println("<< decrypt_text");
                            */

                            byte[] result = decryption(decrypt);
                            //byte[] result = encryption(decrypt);           //같은 동작이다.
                            String Origintext = new String(result);

                            System.out.println(Origintext);                 // << Hex -> 평문 String
                            output.write(result);
                            output.write('\n');

                        } while (!line.equals(null));

                        buffer.close();
                        output.close();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                    break;

                case 3:     //시스템 종료.
                    System.out.println("System shut down.");
                    System.exit(0);

                default:
                    System.out.println("System shut down.");
                    System.exit(0);

            }
        }


    public static void swap(int i, int j){
        int temp = s[i];
        s[i] = s[j];
        s[j] = s[i];
    }

    public static byte[] encryption(byte [] plain_line){
        final byte[] ciphertext = new byte[plain_line.length];
        int i = 0, j = 0, k, t;
        byte tmp;
        for (int counter = 0; counter < plain_line.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + s[i]) & 0xFF;
            swap(i, j);
            t = (s[i] + s[j]) & 0xFF;
            k = s[t];
            ciphertext[counter] = (byte) (plain_line[counter] ^ k);
        }
        return ciphertext;
    }

    //encryption 이나 Decryption 이나 같은 동작함.

    public static byte[] decryption(byte [] cipher_line){
        final byte[] ciphertext = new byte[cipher_line.length];
        int i = 0, j = 0, k, t;
        byte tmp;
        for (int counter = 0; counter < cipher_line.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + s[i]) & 0xFF;
            swap(i, j);
            t = (s[i] + s[j]) & 0xFF;
            k = s[t];
            ciphertext[counter] = (byte) (cipher_line[counter] ^ k);
        }
        return ciphertext;

    }
}
