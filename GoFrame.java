/**
 * Implements certain aspects of the game of "Go"
 */
public class GoFrame extends GoBaseFrame {

    // symbolic constants, representing stones on the board
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int EMPTY = 2;
    public static final int WHITE_IN_PERIL = 3;
    public static final int BLACK_IN_PERIL = 4;

    /**
     * Clears the board of all stones
     */
    @Override
    protected void clearBoard() {

        // **** YOUR CODE FOR CHECKPOINT 9 OF LAB 6 SHOULD GO HERE ****

    }
    /**
     * If the given column and row positions are a legal location on the board,
     *  modifies the location by either changing the stone's color,
     *  or by adding or removing a stone.
     *
     * @param col The horizontal position of the spot on the board,
     *            with 0 representing the left of the board.
     * @param row The vertical position of the spot on the board,
     *            with 0 representing the top of the board.
     */
    @Override
    protected void pressedOnSpace(int row, int col) {

        // at this point, col contains the column-coordinate denoting the square
        // that was pressed, and row contains the row-coordinate       
        // **** YOUR CODE FOR CHECKPOINT 10 OF LAB 6 SHOULD GO HERE ****

        // this is a placeholder; you can remove this line
        System.out.println("row = " + row + ", col = " + col);
    }

    /**
     * Places stones randomly on the board, with
     * approximately 20% of the spaces blank, 40% black, 40% white
     */
    @Override
    protected void randomizeBoard() {
        // **** YOUR CODE FOR CHECKPOINT 1 OF LAB 7 SHOULD GO HERE ****
    }

    /**
     * Determine which stones are captured and remove them from the board
     */
    @Override
    protected void removeCapturedStones() {

        // **** YOUR CODE FOR CHECKPOINTS 2+ LAB 7 SHOULD GO HERE ****

    }

    ///////////////////////////////////////////////////////////////////
    //*****************************************************************
    //**CS 273 students should not need to modify anything below here**
    //*****************************************************************
    ///////////////////////////////////////////////////////////////////

    /**
     * Creates and displays frame
     * @param args
     */
    public static void main(String[] args) {
        new GoFrame().setVisible(true);
    }
}