import static java.lang.System.out;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ReadFile readFile = new ReadFile("../data/ProgrammingHistory.txt");
        readFile.readAll();
        Integer[] indices = readFile.search("code", true);
        for (int item : indices) {
            out.println(item);
        }
    }
}
