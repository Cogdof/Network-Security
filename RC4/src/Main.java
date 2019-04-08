import java.io.*;
import java.util.Scanner;

public class Main {

    //int s[] = new int[256];
    static byte s[] = new byte[256];
    static byte k[] = new byte[256];
    static byte key[];

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        String keyStr="";


        //=================     key 입력받기    =========================//
        do {
            System.out.println("Input the Key (1~256 Byte)");
            keyStr = input.nextLine();
        }
        while(keyStr.length()< 1 || keyStr.length() > 256);     // Key 는 1 ~ 256 Byte 만 가능.

        //입력받은 key <- 256 미만. 256 미만인경우 같은 key 내용이 반복해서 들어감.

        System.out.println("key : "+keyStr);
        System.out.println(keyStr.length());
        key = keyStr.getBytes();



        //=================     Key 초기화 및 Key 스트림 생성  =========================//

        for(int i=0; i<256; i++){
            s[i]= (byte) i;
            // s[i]=  i;
            k[i]= key[i % key.length];
            System.out.println( s[i] + " / "+ (char)k[i]);
        }

        int j=0;

        for(int i=0; i<256; i++){
            j =  ((j+s[i]+k[i])% 256);
            swap(i,j);
        }


        //=================     File 읽어오기    =========================//

        System.out.println("Choose mode : 1)Plain -> Cypher // 2) Cypher -> Plain 3) Exit");
        int mode = input.nextInt();

        switch (mode){

            case 1:     //평문 암호화

                try{
                    File file = new File("src/plain_text.txt");
                    FileReader fileReader = new FileReader(file);
                    //  byte[] by=str.getBytes();
                    // output.write(by);
                    OutputStream output = new FileOutputStream("src/cipher_text.txt");
                }
                catch(Exception e){
                    e.getStackTrace();
                }


            case 2:     //암호문 복호화

                try{
                    File file = new File("src/cipher_text.txt");
                    FileReader fileReader = new FileReader(file);
                    //  byte[] by=str.getBytes();
                    // output.write(by);
                    OutputStream output = new FileOutputStream("src/decrypt_text.txt");
                }
                catch(Exception e){
                    e.getStackTrace();
                }




            case 3:     //시스템 종료.
                System.out.println("System shut down.");
                System.exit(0);;


            default:
                System.out.println("System shut down.");
                System.exit(0);;
        }






        //=================     File 읽어오기    =========================//

        try{
            File file = new File("src/plain_text.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fileReader);
            OutputStream output = new FileOutputStream("src/cipher_text.txt");
            //String line = buffer.readLine();
            String line="";

            do{
              line = buffer.readLine();
              byte[] cipher = line.getBytes();
              byte[] result = encryption(cipher);

              //hexadecimal 로 text 저장. 필요!
              output.write(result);
            }while(!line.equals(null));

            buffer.close();
            output.close();
        }
        catch(Exception e){
            e.getStackTrace();
        }









        //=================     File 저장하기    =========================//





    }

    public static void swap(int i, int j){
        byte temp = s[i];
        s[i] = s[j];
        s[j] = s[i];
    }

    public static byte[] encryption(byte [] bytes){
        byte[] result;

        return result;
    }

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

    // 16진수 변환.
    public void printHEX(String st)
    {

        for(int i = 0; i<st.length(); i++){
            int ch=(int)st.charAt( i );
            System.out.format("%04X", ch);
        }

    }


    public void printHEX(byte[] ba)
    {
        byte c ;

        for (int i =0;i<ba.length ; i++)
        {

            c = ba[i];
            System.out.format("%02X", c);
        }

    }



}
