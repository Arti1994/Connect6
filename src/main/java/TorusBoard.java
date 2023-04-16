public class TorusBoard extends GameBoard {
    /**
     * Instanziiert neues Torusbrett
     * 
     * @param size
     *            Größe des Spielbrettes
     */
    public TorusBoard(int size) {
        super(size);
    }

    @Override
    public String getPlace(int row, int col) {
        int tRow = Math.floorMod(row, board.length);
        int tCol = Math.floorMod(col, board.length);
        return board[tRow][tCol];
    }

    @Override
    public boolean placeable(int row1, int col1, int row2, int col2) {
        int tRow1 = Math.floorMod(row1, board.length);
        int tCol1 = Math.floorMod(col1, board.length);
        int tRow2 = Math.floorMod(row2, board.length);
        int tCol2 = Math.floorMod(col2, board.length);
        if (board[tRow1][tCol1].equals("**") && board[tRow2][tCol2].equals("**")) {
            if (tRow1 != tRow2 || tCol1 != tCol2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setPlace(int row1, int col1, int row2, int col2, String token) {
        int tRow1 = Math.floorMod(row1, board.length);
        int tCol1 = Math.floorMod(col1, board.length);
        int tRow2 = Math.floorMod(row2, board.length);
        int tCol2 = Math.floorMod(col2, board.length);
        board[tRow1][tCol1] = token;
        board[tRow2][tCol2] = token;
    }

    @Override
    public boolean checkRow(int row, int col) {
        int tRow = Math.floorMod(row, board.length);
        int tCol = Math.floorMod(col, board.length);
        int counter = 1;
        int i = col + 1;
        while (i < col + 6 && counter < 6) {
            if (board[tRow][Math.floorMod(i, board.length)].equals(board[tRow][tCol])) {
                counter++;
                i++;
            } else {
                break;
            }
        }
        i = col - 1;
        while (i > col - 6 && counter < 6) {
            if (board[tRow][Math.floorMod(i, board.length)].equals(board[tRow][tCol])) {
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
        int tRow = Math.floorMod(row, board.length);
        int tCol = Math.floorMod(col, board.length);
        int counter = 1;
        int i = row + 1;
        while (i < row + 6 && counter < 6) {
            if (board[Math.floorMod(i, board.length)][tCol].equals(board[tRow][tCol])) {
                counter++;
                i++;
            } else {
                break;
            }
        }
        i = row - 1;
        while (i > row - 6 && counter < 6) {
            if (board[Math.floorMod(i, board.length)][tCol].equals(board[tRow][tCol])) {
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
        int tRow = Math.floorMod(row, board.length);
        int tCol = Math.floorMod(col, board.length);
        int counter = 1;
        int i = row + 1;
        int j = col + 1;
        while (i < row + 6 && counter < 6) {
            if (board[Math.floorMod(i, board.length)][Math.floorMod(j, board.length)].equals(board[tRow][tCol])) {
                counter++;
                i++;
                j++;
            } else {
                break;
            }
        }
        i = row - 1;
        j = col - 1;
        while (i > row - 6 && counter < 6) {
            if (board[Math.floorMod(i, board.length)][Math.floorMod(j, board.length)].equals(board[tRow][tCol])) {
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
        int tRow = Math.floorMod(row, board.length);
        int tCol = Math.floorMod(col, board.length);
        int counter = 1;
        int i = row - 1;
        int j = col + 1;
        while (i > row - 6 && counter < 6) {
            if (board[Math.floorMod(i, board.length)][Math.floorMod(j, board.length)].equals(board[tRow][tCol])) {
                counter++;
                i--;
                j++;
            } else {
                break;
            }
        }
        i = row + 1;
        j = col - 1;
        while (i < row + 6 && counter < 6) {
            if (board[Math.floorMod(i, board.length)][Math.floorMod(j, board.length)].equals(board[tRow][tCol])) {
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