import java.util.StringJoiner;

public abstract class GameBoard {
    /**
     * Spielbrett
     */
    protected String[][] board;

    /**
     * Instanziiert neues Spielbrett
     * 
     * @param size
     *            Größe des Spielbrettes
     */
    public GameBoard(int size) {
        this.board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = "**";
            }
        }
    }

    /**
     * Gibt das Spielbrett zurück
     * 
     * @return Spielbrett
     */
    public String[][] getBoard() {
        return board;
    }
    
    /**
     * Gibt die Spielbrettgröße zurück
     * 
     * @return Spielbrettgröße
     */
    public int getLength() {
        return board.length;
    }

    /**
     * Gibt die jeweilige Reihe als String zurück
     * 
     * @param row
     *            Nummer der Reihe
     * @return Reihe
     */
    public String rowToString(int row) {
        StringJoiner rowString = new StringJoiner(" ");
        for (int i = 0; i < board.length; i++) {
            rowString.add(board[row][i]);
        }
        return rowString.toString();
    }

    /**
     * Gibt die jeweilige Spalte als String zurück
     * 
     * @param col
     *            Nummer der Spalte
     * @return Spalte
     */
    public String colToString(int col) {
        StringJoiner colString = new StringJoiner(" ");
        for (int i = 0; i < board.length; i++) {
            colString.add(board[i][col]);
        }
        return colString.toString();
    }

    /**
     * Gibt das gesamte Spielfeld als String zurück
     * 
     * @return Spielfeld
     */
    public String boardToString() {
        StringJoiner boardString = new StringJoiner("\n");
        for (int i = 0; i < board.length; i++) {
            boardString.add(rowToString(i));
        }
        return boardString.toString();
    }

    /**
     * Gibt das jeweilige Feld zurück
     * 
     * @param row
     *            Zeilennummer des Feldes
     * @param col
     *            Spaltennummer des Feldes
     * @return Feld
     * @throws InputException
     *             falls ungültige Werte
     */
    public abstract String getPlace(int row, int col) throws InputException;

    /**
     * überprüft ob auf Feldern platziert werden darf
     * 
     * @param row1
     *            Zeilennummer des ersten Feldes
     * @param col1
     *            Spaltennummer des ersten Feldes
     * @param row2
     *            Zeilennummer des zweiten Feldes
     * @param col2
     *            Spaltennummer des zweiten Feldes
     * @return {@code true} falls platzierbar, sonst {@code false}
     */
    public abstract boolean placeable(int row1, int col1, int row2, int col2);

    /**
     * Setzt Spielstein des aktuellen Spielers auf Felder
     * 
     * @param row1
     *            Zeilennummer des ersten Feldes
     * @param col1
     *            Spaltennummer des ersten Feldes
     * @param row2
     *            Zeilennummer des zweiten Feldes
     * @param col2
     *            Spaltennummer des zweiten Feldes
     * @param token
     *            Spielstein des aktuellen Spieler
     */
    public abstract void setPlace(int row1, int col1, int row2, int col2, String token);

    /**
     * überprüft die jeweils 6 umliegenden Felder des zuletzt gesetzten Feldes in
     * Reihe auf Gleichheit
     * 
     * @param row
     *            Zeilennummer des Feldes
     * @param col
     *            Spaltennummer des Feldes
     * @return {@code true} falls 6 hintereinander, sonst {@code false}
     */
    public abstract boolean checkRow(int row, int col);

    /**
     * überprüft die jeweils 6 umliegenden Felder des zuletzt gesetzten Feldes in
     * Spalte auf Gleichheit
     * 
     * @param row
     *            Zeilennummer des Feldes
     * @param col
     *            Spaltennummer des Feldes
     * @return {@code true} falls 6 hintereinander, sonst {@code false}
     */
    public abstract boolean checkCol(int row, int col);

    /**
     * überprüft die jeweils 6 umliegenden Felder des zuletzt gesetzten Feldes in
     * Diagonale von links oben nach rechts unten auf Gleichheit
     * 
     * @param row
     *            Zeilennummer des Feldes
     * @param col
     *            Spaltennummer des Feldes
     * @return {@code true} falls 6 hintereinander, sonst {@code false}
     */
    public abstract boolean checkDiagTopToBottom(int row, int col);

    /**
     * überprüft die jeweils 6 umliegenden Felder des zuletzt gesetzten Feldes in
     * Diagonale von links unten nach rechts oben auf Gleichheit
     * 
     * @param row
     *            Zeilennummer des Feldes
     * @param col
     *            Spaltennummer des Feldes
     * @return {@code true} falls 6 hintereinander, sonst {@code false}
     */
    public abstract boolean checkDiagBottomToTop(int row, int col);

}