public class Game {
    private GameBoard gameBoard;
    private Player[] players;
    private int turn;
    private boolean winOrDraw;

    /**
     * Instanziiert neues Connect6 Spiel
     * 
     * @param mode
     *            Spielmodus
     * @param size
     *            Größe des Spielfeldes
     * @param number
     *            Anzahl der Spieler
     */
    public Game(String mode, int size, int number) {
        if (mode.equals("standard")) {
            this.gameBoard = new StandardBoard(size);
        } else {
            this.gameBoard = new TorusBoard(size);
        }
        this.players = new Player[number];
        for (int i = 0; i < number; i++) {
            this.players[i] = new Player(i);
        }
        this.turn = 0;
        this.winOrDraw = false;
    }

    /**
     * Setzt jeweiligen Spielstein auf Felder, falls Werte korrekt
     * 
     * @param row1
     *            Reihe des ersten Feldes
     * @param col1
     *            Spalte des ersten Feldes
     * @param row2
     *            Reihe des zweiten Feldes
     * @param col2
     *            Spalte des zweiten Feldes
     * @throws InputException
     *             falls ungültige Werte
     */
    public void place(int row1, int col1, int row2, int col2) throws InputException {
        if (gameBoard.placeable(row1, col1, row2, col2)) {
            String token = getCurrentPlayer().getToken();
            gameBoard.setPlace(row1, col1, row2, col2, token);
        } else {
            throw new InputException("choose other coordinates");
        }
    }

    /**
     * überprüft ob zuletzt gesetze Felder zu einem Sieg geführt haben
     * 
     * @param row1
     *            Reihe des ersten Feldes
     * @param col1
     *            Spalte des ersten Feldes
     * @param row2
     *            Reihe des zweiten Feldes
     * @param col2
     *            Spalte des zweiten Feldes
     * @return {@code true}, falls es einen Sieger gibt, sonst {@code false}
     */
    public boolean checkWinner(int row1, int col1, int row2, int col2) {
        if (gameBoard.checkRow(row1, col1) || gameBoard.checkCol(row1, col1)
                || gameBoard.checkDiagTopToBottom(row1, col1) || gameBoard.checkDiagBottomToTop(row1, col1)) {
            this.winOrDraw = true;
            return true;
        }
        if (gameBoard.checkRow(row2, col2) || gameBoard.checkCol(row2, col2)
                || gameBoard.checkDiagTopToBottom(row2, col2) || gameBoard.checkDiagBottomToTop(row2, col2)) {
            this.winOrDraw = true;
            return true;

        }
        this.turn++;
        return false;
    }

    /**
     * überprüft, ob Spiel unentschieden
     * 
     * @return {@code true}, falls unentschieden, sonst {@code false}
     */
    public boolean checkDraw() {
        if (turn * 2 == gameBoard.getLength() * gameBoard.getLength()) {
            this.winOrDraw = true;
            return true;
        }
        return false;
    }

    /**
     * Gibt die jeweilige Reihe zurück
     * 
     * @param row
     *            Nummer der Reihe
     * @return Reihe
     * @throws InputException
     *             falls ungültige Reihe
     */
    public String getRow(int row) throws InputException {
        if (row < 0 || row >= gameBoard.getLength()) {
            throw new InputException("invalid values");
        }
        return gameBoard.rowToString(row);
    }

    /**
     * Gibt die jeweilige Spalte zurück
     * 
     * @param col
     *            Nummer der Spalte
     * @return Spalte
     * @throws InputException
     *             falls ungültige Spalte
     */
    public String getCol(int col) throws InputException {
        if (col < 0 || col >= gameBoard.getLength()) {
            throw new InputException("invalid values");
        }
        return gameBoard.colToString(col);
    }

    /**
     * Gibt das Spielfeld (als String) zurück
     * 
     * @return Spielfeld
     */
    public String getGameBoard() {
        return gameBoard.boardToString();
    }

    /**
     * Gibt das jeweilige Feld zurück
     * 
     * @param row
     *            Nummer der Reihe
     * @param col
     *            Nummer der Reihe
     * @return Feld
     * @throws InputException
     *             falls ungültige Werte
     */
    public String getPlace(int row, int col) throws InputException {
        return gameBoard.getPlace(row, col);
    }

    /**
     * Setzt alle nötigen Werte auf Startzustand zurück
     */
    public void reset() {
        if (this.gameBoard instanceof StandardBoard) {
            this.gameBoard = new StandardBoard(gameBoard.getLength());
        } else {
            this.gameBoard = new TorusBoard(gameBoard.getLength());
        }
        this.turn = 0;
        this.winOrDraw = false;
    }

    /**
     * Gibt den aktuellen Spieler zurück
     * 
     * @return Spieler
     */
    public Player getCurrentPlayer() {
        return players[turn % players.length];
    }

    /**
     * Gibt zurück ob platziert werden darf
     * 
     * @return {@code true} falls platzierbar, sonst {@code false}
     */
    public boolean winOrDraw() {
        return winOrDraw;
    }
}