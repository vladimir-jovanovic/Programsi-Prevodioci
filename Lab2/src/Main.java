import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {


        try {
            if (args.length != 1){
                System.err.println("path to file must be passed as arg");
                System.exit(-1);
            }

            Reader reader = new InputStreamReader(new FileInputStream(args[0]));

            Grammar grammar = new Grammar();
            LL1Parser parser = new LL1Parser(reader);

            int result = parser.SA_LL1();

            System.out.println(result == 0 ? "accepted" : "error");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
