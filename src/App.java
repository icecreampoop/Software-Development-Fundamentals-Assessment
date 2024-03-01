import java.util.Scanner;

public class App {
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

        System.out.print(welcome);

        while (!userQuit) {
            userInput = scan.nextLine();

            if (userInput.equals("q")) {
                System.out.println();
                System.out.println("Thank you for using the program...");
                System.out.println("Hope to see you soon...");
                userQuit = true;
            }
        }

        scan.close();

    }
}
