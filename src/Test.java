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
//        File file = new File(args[0]);
        File file = new File("/Users/guanhuaimin/本地磁盘/学习/本地_大三上/编译原理/labs/compilePreLab/src/a.txt");



        BufferedReader reader = new BufferedReader(new FileReader(file));
        PushbackReader in = new PushbackReader(reader, 10);

        GrammarAnalysis grammarAnalysis = init(in);
        grammarAnalysis.getSym();
        grammarAnalysis.CompUnit();

//        String output = args[1];
//        File outFile = new File(output);
//        FileWriter out = new FileWriter(outFile,true);
//
//        if(!grammarAnalysis.isError) {
//            out.write(grammarAnalysis.out);
//            out.flush();
//            out.close();
//        }
//        else System.exit(-1);

        System.out.print(grammarAnalysis.out);
    }
}
