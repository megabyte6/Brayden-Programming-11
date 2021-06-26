import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

    private ArrayList<String> data = new ArrayList<>();
    private String filePath = "";

    public ReadFile(String filePath) {
        this.filePath = filePath;
    }

    public boolean fileExists() {
        File file = new File(filePath);
        if (file.exists() && file.isFile() && file.canRead()) {
            return true;
        } else {
            return false;
        }
    }

    public void setFilePath(String newFilePath) {
        this.filePath = newFilePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void readAll() throws FileNotFoundException, IOException {
        if (!this.fileExists()) return;
        if (!data.isEmpty()) data.clear();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            data.add(line);
        }
        bufferedReader.close();
    }

    public Integer[] search(String text) {
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).contains(text)) {
                indices.add(i + 1);
            }
        }
        return indices.toArray(new Integer[0]);
    }

    public Integer[] search(String text, boolean caseInsensitive) {
        if (caseInsensitive) return this.search(text);

        text = text.toLowerCase();
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).toLowerCase().contains(text)) {
                indices.add(i + 1);
            }
        }
        return indices.toArray(new Integer[0]);
    }
}
