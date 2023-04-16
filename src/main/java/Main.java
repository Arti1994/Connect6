import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * Main-Methode, hier startet das Programm
     * 
     * @param args
     *            Kommandozeilenargumente
     */
    public static void main(String[] args) {
        // Überprüfung der Kommandozeilenargumente auf korrekte Eingaben
        try {
        argumentsValidation(args); 
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            return;
        }
        String mode = args[0];
        int size = Integer.parseInt(args[1]);
        int number = Integer.parseInt(args[2]);
        Game game = new Game(mode, size, number);
        
        // Schleife zur Befehlsabfrage
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = br.readLine();
            while (!input.equals("quit")) {
                try {
                    Command.matchingInput(input, game);
                } catch (InputException e) {
                    System.err.println(e.getMessage());
                }
                input = br.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    
    /**
     * 
     * @param args
     * @return
     */
    public static void argumentsValidation(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("3 arguments expected.");
        }
        if (!args[0].matches("standard|torus")) {
            throw new IllegalArgumentException("illegal gamemode.");
        }
        if (!args[1].matches("18|20")) {
            throw new IllegalArgumentException("illegal boardsize.");
        }
        if (!args[2].matches("2|3|4")) {
            throw new IllegalArgumentException("illegal player number.");
        }
    }
    
}