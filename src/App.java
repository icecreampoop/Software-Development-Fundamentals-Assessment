import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    static HashMap<Integer, List<String>> pokemonStackStore = new HashMap<>();
    public static void main(String[] args) throws Exception {
        String welcome = """
                Welcome to Pokemon Gaole Legend 4 Rush 2

                (1) View the list of Pokemon in the selected stack
                (2) View unique list of Pokemon in the selected stackv
                (3) Find next 5 stars Pokemon occurrence
                (4) Create new Pokemon stack and save (append) to csv file
                (q) to exit the program
                Enter your selection >""";
        boolean userQuit = false;
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        System.out.print(welcome);

        //storing csv data
        FileService fileService = new FileService();
        int count = 1;
         for (String lines : fileService.csvReader(args[0])) {
            pokemonStackStore.put(count, Arrays.asList(lines.split(",")));
            count++;
         }

        while (!userQuit) {
            userInput = scan.nextLine().toLowerCase().trim();

            if (userInput.equals("q")) {
                System.out.println();
                System.out.println("Thank you for using the program...");
                System.out.println("Hope to see you soon...");
                userQuit = true;
            }

            if (userInput.equals("4")) {
                System.out.println("Create a new Pokemon stack and save to a new file >");
                String fileData = scan.nextLine();
                System.out.println("Enter filename to save (e.g. path/filename.csv) >");
                String filePath = scan.nextLine();
                fileService.writeAsCSV(fileData, Paths.get(filePath));
            }
        }

        scan.close();

    }
}
