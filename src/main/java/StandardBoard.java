public class StandardBoard extends GameBoard {
    /**
     * Instanziiert neues Standardbrett
     * 
     * @param size
     *            Größe des Spielbrettes
     */
    public StandardBoard(int size) {
        super(size);
    }

    @Override
    public String getPlace(int row, int col) throws InputException {
        if (row < 0 || row >= board.length || col < 0 || col >= board.length) {
            throw new InputException("invalid values");
        }
        return board[row][col];
    }

    @Override
    public boolean placeable(int row1, int col1, int row2, int col2) {
        if (row1 < board.length && col1 < board.length && row1 >= 0 && col1 >= 0 && board[row1][col1].equals("**")) {
            if (row2 < board.length && col2 < board.length && row2 >= 0 && col2 >= 0
                    && board[row2][col2].equals("**")) {
                if (row1 != row2 || col1 != col2) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void setPlace(int row1, int col1, int row2, int col2, String token) {
        board[row1][col1] = token;
        board[row2][col2] = token;
    }

    @Override
    public boolean checkRow(int row, int col) {
        int counter = 1;
        int i = col + 1;
        while (i < board.length && counter < 6) {
            if (board[row][i].equals(board[row][col])) {
                counter++;
                i++;
            } else {
                break;
            }
        }
        i = col - 1;
        while (i >= 0 && counter < 6) {
            if (board[row][i].equals(board[row][col])) {
                counter++;
                i--;
            } else {
                break;
            }
        }
        return counter == 6;
    }

    @Override
    public boolean checkCol(int row, int col) {
        int counter = 1;
        int i = row + 1;
        while (i < board.length && counter < 6) {
            if (board[i][col].equals(board[row][col])) {
                counter++;
                i++;
            } else {
                break;
            }
        }
        i = row - 1;
        while (i >= 0 && counter < 6) {
            if (board[i][col].equals(board[row][col])) {
                counter++;
                i--;
            } else {
                break;
            }
        }
        return counter == 6;
    }

    @Override
    public boolean checkDiagTopToBottom(int row, int col) {
        int counter = 1;
        int i = row + 1;
        int j = col + 1;
        while (i < board.length && j < board.length && counter < 6) {
            if (board[i][j].equals(board[row][col])) {
                counter++;
                i++;
                j++;
            } else {
                break;
            }
        }
        i = row - 1;
        j = col - 1;
        while (i >= 0 && j >= 0 && counter < 6) {
            if (board[i][j].equals(board[row][col])) {
                counter++;
                i--;
                j--;
            } else {
                break;
            }
        }
        return counter == 6;
    }

    @Override
    public boolean checkDiagBottomToTop(int row, int col) {
        int counter = 1;
        int i = row - 1;
        int j = col + 1;
        while (i >= 0 && j < board.length && counter < 6) {
            if (board[i][j].equals(board[row][col])) {
                counter++;
                i--;
                j++;
            } else {
                break;
            }
        }
        i = row + 1;
        j = col - 1;
        while (i < board.length && j >= 0 && counter < 6) {
            if (board[i][j].equals(board[row][col])) {
                counter++;
                i++;
                j--;
            } else {
                break;
            }
        }
        return counter == 6;
    }
}