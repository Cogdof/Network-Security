import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        //key 입력받기
        String keyStr = input.nextLine();
        System.out.println("key : "+keyStr);

        byte s[] = new byte[256];
        byte k[] = new byte[256];

        byte key[]=keyStr.getBytes();
        System.out.println(key.length);

        try{
            File file = new File("src/plaintext.txt");
            FileReader fileReader = new FileReader(file);
          //  byte[] by=str.getBytes();
          // output.write(by);
            OutputStream output = new FileOutputStream("src/ciphertext.txt");
        }
        catch(Exception e){
            e.getStackTrace();
        }

        for(int i=0; i<256; i++){
            s[i]= (byte) i;
            k[i]= key[i%key.length];
            System.out.println(s[i] + " / "+ (char)k[i]);
        }


        //파일 입력받기



        //평문 암호화




        //암호문 복호화



        //입력받은 key <- 256 미만. 256 미만인경우 같은 key 내용이 반복해서 들어감.
        //key 가 0으로만 채워져있을떄.
        //key 길이가 256 일떄.



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
