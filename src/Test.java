import java.io.*;

public class Test {
    GrammarAnalysis grammarAnalysis;

    public static GrammarAnalysis init(PushbackReader in){
        GrammarAnalysis grammarAnalysis = new GrammarAnalysis(in);
        grammarAnalysis.sym.word = "";
        grammarAnalysis.sym.type = "";
        grammarAnalysis.sym.number = -1;
        return grammarAnalysis;
    }

    public static void main(String[] args) throws IOException {
        String stream = args[0];
        File file = new File(stream);
//        File file = new File("/Users/merc./Desktop/MERC/大三上/编译原理/实验/lab3/lab3/src/test.txt");
        String output = args[1];
        File outFile = new File(output);
        FileWriter out = new FileWriter(outFile,true);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        PushbackReader in = new PushbackReader(reader, 10);

        GrammarAnalysis grammarAnalysis = init(in);
        grammarAnalysis.getSym();
        grammarAnalysis.CompUnit();
        if(!grammarAnalysis.isError)
//            System.out.println(grammarAnalysis.out);
        {
            out.write(grammarAnalysis.out);
            out.flush();
            out.close();
        }

        else System.exit(-1);
    }
}
