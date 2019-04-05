public class Main {

    public static void main(String[] args) {

        String cipher = "NCJAEZRCLASJLYODEPRLYZRCLASJLCPEHZDTOPDZQLNZTY";

        System.out.println((int)'A'+" "+(int)'Z');
        System.out.println((char)65+" "+(char)90);


        for(int key=0; key<27; key++) {
            System.out.println(key +": key");
            for(int i=0; i< cipher.length(); i++) {
                int num=cipher.charAt(i);

                num = Math.abs((num-key));
                if(num<65) {//65 - 15 = 45
                    num = 91-(65-num);
                }
                else {

                }



                char show = (char) (num);

                System.out.print(show);
            }
            System.out.println();
            System.out.println("-------------");
        }



        for(int i='A'; i<='Z'; i++) {
            System.out.println(i +" " +(char)i);
        }

    }
}
