public class InputException extends Exception {
    /**
     * Wirft Exception für falschen Input
     * 
     * @param message
     *            Nachricht bei geworfener Exception
     */
    public InputException(String message) {
        super(message);
    }
}