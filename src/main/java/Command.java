import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
    /**
     * Platziert bei korrekter Eingabe einen Spielstein und gibt den aktuellen
     * Spielstand aus
     * 
     * @throws InputException
     *             falls Befehl nicht verfügbar oder ungültige Werte
     */
    PLACE("place (-?\\d+);(-?\\d+);(-?\\d+);(-?\\d+)") {
        @Override
        public void doCommand(MatchResult matcher, Game game) throws InputException {
            if (!game.winOrDraw()) {
                int row1 = Integer.parseInt(matcher.group(1));
                int col1 = Integer.parseInt(matcher.group(2));
                int row2 = Integer.parseInt(matcher.group(3));
                int col2 = Integer.parseInt(matcher.group(4));
                game.place(row1, col1, row2, col2);
                if (game.checkWinner(row1, col1, row2, col2)) {
                    System.out.println(game.getCurrentPlayer().getToken() + " wins");
                } else if (game.checkDraw()) {
                    System.out.println("draw");
                } else {
                    System.out.println("OK");
                }
            } else {
                throw new InputException("command 'place' is not available");
            }
        }
    },

    /**
     * Gibt bei korrekter Eingabe die jeweilige Zeile aus
     * 
     * @throws InputException
     *             falls ungültige Werte
     */
    ROWPRINT("rowprint (-?\\d+)") {
        @Override
        public void doCommand(MatchResult matcher, Game game) throws InputException {
            int row = Integer.parseInt(matcher.group(1));
            System.out.println(game.getRow(row));
        }
    },

    /**
     * Gibt bei korrekter Eingabe die jeweilige Spalte aus
     * 
     * @throws InputException
     *             falls ungültige Werte
     */
    COLPRINT("colprint (-?\\d+)") {
        @Override
        public void doCommand(MatchResult matcher, Game game) throws InputException {
            int col = Integer.parseInt(matcher.group(1));
            System.out.println(game.getCol(col));
        }
    },

    /**
     * Gibt das gesamte Spielfeld aus
     */
    PRINT("print") {
        @Override
        public void doCommand(MatchResult matcher, Game game) {
            System.out.println(game.getGameBoard());
        }
    },

    /**
     * Gibt bei korrekter Eingabe das jeweilige Feld aus
     * 
     * @throws InputException
     *             falls ungültige Werte
     */
    STATE("state (-?\\d+);(-?\\d+)") {
        @Override
        public void doCommand(MatchResult matcher, Game game) throws InputException {
            int row = Integer.parseInt(matcher.group(1));
            int col = Integer.parseInt(matcher.group(2));
            System.out.println(game.getPlace(row, col));
        }
    },

    /**
     * Setzt das Spiel zurück auf den Anfangszustand
     */
    RESET("reset") {
        @Override
        public void doCommand(MatchResult matcher, Game game) {
            game.reset();
            System.out.println("OK");
        }
    };

    private Pattern pattern;

    /**
     * Instanziiert neuen Befehl
     * 
     * @param pattern
     *            jeweiliges Eingabemuster für Befehl
     */
    Command(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * überprüft ob Eingabe mit einem Eingabemuster für einen Befehl übereinstimmt,
     * und führt diesen dann aus
     * 
     * @param input
     *            zu überprüfende Eingabe
     * @param game
     *            aktuelles Spiel
     * @throws InputException
     *             falls ungültiger Befehl oder Werte
     */
    public static void matchingInput(String input, Game game) throws InputException {
        for (Command command : Command.values()) {
            Matcher matcher = command.pattern.matcher(input);
            if (matcher.matches()) {
                command.doCommand(matcher, game);
                return;
            }
        }
        throw new InputException("unknown command");
    }

    /**
     * Abstrakte Methode zur Befehlsausführung bei jeweiliger Eingabe
     * 
     * @param matcher
     *            zur Überprüfung der Übereinstimmung von input und pattern
     * @param game
     *            aktuelles Spiel
     * @throws InputException
     *             falls ungültige Werte
     */
    public abstract void doCommand(MatchResult matcher, Game game) throws InputException;
}