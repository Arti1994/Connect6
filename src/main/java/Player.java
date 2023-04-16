public class Player {
    private String token;

    /**
     * Instanziiert einen neuen Spieler
     * 
     * @param number
     *            Index des Spielers
     */
    public Player(int number) {
        this.token = "P" + (number + 1);
    }

    /**
     * Gibt das Symbol des Spielers zurück
     * 
     * @return Symbol des Spieler
     */
    public String getToken() {
        return token;
    }
}