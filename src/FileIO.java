import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char chr = sc.next().charAt(0);
        try (FileReader fr = new FileReader(".\\Sample.txt");
             BufferedReader br = new BufferedReader(fr);) {
            String sCurrentLine;
            int count = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                for(int i = 0; i < sCurrentLine.length(); i++) {
                    if(chr == sCurrentLine.charAt(i)) {
                        count++;
                    }
                }
            }

            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
