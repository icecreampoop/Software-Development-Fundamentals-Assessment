import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileService {
    private Path path;
    private String lines = "";
    private ArrayList<String> temp = new ArrayList<>();

    public ArrayList<String> csvReader(String csvPath) {
        temp.clear();
        path = Paths.get(csvPath);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            while ((lines = bufferedReader.readLine()) != null) {
                temp.add(lines);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

        return temp;
    }

    public void writeAsCSV(String fileData, Path filePath) {
        //if it doesnt exist
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException io) {
                io.printStackTrace();
            }
        }

        //appending to file
        ArrayList<String> existingData = csvReader(filePath.toString());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (String x : existingData) {
                bufferedWriter.write(x);
                bufferedWriter.newLine();
            }
            bufferedWriter.write(fileData);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
