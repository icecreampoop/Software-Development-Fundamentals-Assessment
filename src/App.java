import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class App {
    private static HashMap<Integer, List<String>> pokemonStackStore = new HashMap<>();
    private static HashSet<String> uniquePokemonSet = new HashSet<>();
    public static void main(String[] args) throws Exception {
        String welcome = """
                Welcome to Pokemon Gaole Legend 4 Rush 2

                (1) View unique list of Pokemon in the selected stack
                (2) Find next 5 stars Pokemon occurrence
                (3) Create new Pokemon stack and save (append) to csv file
                (4) Print distinct Pokemon and cards count
                (q) to exit the program
                Enter your selection >""";
        boolean userQuit = false;
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        //storing csv data
        FileService fileService = new FileService();
        int count = 1;
         for (String lines : fileService.csvReader(args[0])) {
            pokemonStackStore.put(count, Arrays.asList(lines.split(",")));
            count++;
         }

         //main session
        while (!userQuit) {
            System.out.print(welcome);
            userInput = scan.nextLine().toLowerCase().trim();

            if (userInput.equals("q")) {
                System.out.println();
                System.out.println("Thank you for using the program...");
                System.out.println("Hope to see you soon...");
                userQuit = true;
                continue;
            }

            if (userInput.equals("1")) {
                System.out.println("Display the list of unique Pokemon in stack (1 - 8) >");
                userInput = scan.nextLine();
                try {
                    int stackNumber = Integer.parseInt(userInput);

                    if (stackNumber > 0 && stackNumber < 9) {
                        printUniquePokemonInStack(stackNumber);
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid stack number");
                }
            }

            if (userInput.equals("3")) {
                System.out.println("Create a new Pokemon stack and save to a new file >");
                String fileData = scan.nextLine();
                System.out.println("Enter filename to save (e.g. path/filename.csv) >");
                String filePath = scan.nextLine();
                fileService.writeAsCSV(fileData, Paths.get(filePath));
            }

            System.out.println("Press any key to continue...");
            userInput = scan.nextLine();
        }

        scan.close();
    }

    private static void printUniquePokemonInStack(int stackNumber) {
        uniquePokemonSet.clear();

        for (int x = 0; x < pokemonStackStore.get(stackNumber).size(); x++) {
            uniquePokemonSet.add(pokemonStackStore.get(stackNumber).get(x));
        }

        int count = 1;
        for (String uniquePokemon : uniquePokemonSet) {
            System.out.println(String.format("%d ==> %s", count, uniquePokemon));
            count++;
        }
    }
}
