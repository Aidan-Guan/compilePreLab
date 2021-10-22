import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

public class Lab1_Test {

    public static PushbackReader in;
    public static String outputStr = "";

    public static void main(String[] args) throws IOException {
        String fileLoc = args[0];
        in = new PushbackReader(new FileReader(fileLoc));
        Lab1_LexicalAnalysisForGA.getTokenInit(in);

        Lab1_GrammarAnalysis.compUnitAnal();
    }
}
