import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//ACCESS TOKEN  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

public class Test {

    public static Scanner in;

    public static void main (String[] args) throws FileNotFoundException {


        String fileLoc = args[0];
        in = new Scanner(new FileReader(fileLoc));
        // 按行读取
        while (in.hasNextLine()) {
            String line = in.nextLine();
            LexicalAnalysis.readLine(line);
        }



//         FOR DEBUG
//        in = new Scanner(System.in);
//        while (true) {
//            String line = in.nextLine();
//            LexicalAnalysis.readLine(line);
//        }

    }
}
