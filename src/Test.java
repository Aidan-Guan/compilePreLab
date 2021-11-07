import java.io.*;

public class Test {

    public static GrammarAnalysis init(PushbackReader in){
        GrammarAnalysis grammarAnalysis = new GrammarAnalysis(in);
        grammarAnalysis.currentSym.value = "";
        grammarAnalysis.currentSym.type = "";
        return grammarAnalysis;
    }

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
//        File file = new File("/Users/guanhuaimin/本地磁盘/学习/本地_大三上/编译原理/labs/compilePreLab/src/a.txt");



        BufferedReader reader = new BufferedReader(new FileReader(file));
        PushbackReader in = new PushbackReader(reader, 10);
        LexAnal.getTokenInit(in);

        GrammarAnalysis grammarAnalysis = init(in);
        grammarAnalysis.getSym();
        grammarAnalysis.CompUnit();


        String output = args[1];
        File outFile = new File(output);
        FileWriter out = new FileWriter(outFile,true);
        out.write(grammarAnalysis.out);
        out.flush();
        out.close();

        System.out.print(grammarAnalysis.out);
    }
}
