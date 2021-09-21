import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

/**
 * Implements certain aspects of the game of "Go"
 */
public class GoBaseFrame extends JFrame implements ActionListener, MouseListener {

    // our Go-board
    int[][] board;

    // color for our board
    public static final Color BOARD_COLOR = new Color(230,210,180);

    // instance variables
    Button clear, random, capture, spirals; // buttons
    GoBoardCanvas canvas; // the canvas

    /**
     * Create the frame and display it.
     */
    public GoBaseFrame() {
        super();

        this.setSize(600,600); // set its size
        this.setTitle("Go Board");
        this.setup();

        board = new int[19][19];
        for (int[] row : board) {
            Arrays.fill(row, GoFrame.EMPTY);
        }

        this.setBoard(board);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setup() {
        // create Panel to hold the GUI objects
        Panel p = new Panel();
        this.getContentPane().add(p);

        // add clear-button
        clear = new Button("Clear");
        p.add(clear);
        clear.addActionListener(this);

        // add random-button
        random = new Button("Random");
        p.add(random);
        random.addActionListener(this);

        // add capture-button
        capture = new Button("Capture");
        p.add(capture);
        capture.addActionListener(this);

        // add spirals-button
        spirals = new Button("Spirals");
        p.add(spirals);
        spirals.addActionListener(this);

        // create/add our go-board canvas
        canvas = new GoBoardCanvas();
        canvas.setSize(500,500);
        canvas.setBackground(BOARD_COLOR);
        p.add(canvas);

        // register for mouse/window events
        canvas.addMouseListener(this);
    }

    public void setBoard(int[][] bd) {
        canvas.board = bd;
    }

    /**
     * Clears the board of all stones
     */
    void clearBoard() {

    }

    /**
     * Places stones randomly on the board, with
     * approximately 20% of the spaces blank, 40% black, 40% white
     */
    void randomizeBoard() {

    }

    /**
     * Determine which stones are captured and remove them from the board
     */
    void removeCapturedStones() {

    }

    /**
     * If the given column and row positions are a legal location on the board,
     *  modifies the location by either changing the stone's color,
     *  or by adding or removing a stone.
     *
     * @param row The vertical position of the spot on the board,
     *            with 0 representing the top of the board.
     *            
     * @param col The horizontal position of the spot on the board,
     *            with 0 representing the left of the board.
     */
    void pressedOnSpace(int row, int col) {

    }

    /**
     * Handles button-events, invoking the appropriate method.
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource(); // get event-object

        canvas.errors = false;

        try {
            // call appropriate method, depending on button
            if (obj == clear) {
                this.clearBoard();
            }
            else if (obj == random) {
                this.randomizeBoard();
            }
            else if (obj == capture) {
                this.removeCapturedStones();
            }
            else if (obj == spirals) {
                this.createSpirals();
            }
        }
        catch (Exception xx) {
            canvas.errors = true;
        }

        // repaint the canvas so that changes are seen
        canvas.repaint();
    }

    private void createSpirals() {
        int[][] board = canvas.board;

        // check that we have a 19x19 or better
        if (board == null || board.length < 19 ||
        Arrays.stream(board).anyMatch(row -> row.length < 19)) {
            return;
        }

        for (int[] row : board) {
            Arrays.fill(row, GoFrame.EMPTY);
        }

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                board[i][j] = GoFrame.BLACK;
                board[i+9][j+9] = GoFrame.WHITE;
            }
        }

        int top = 1;
        int left = 2;
        int bottom = 7;
        int right = 8;
        while (left < right && top < bottom) {
            for (int i = left; i <= right; i++) {
                board[bottom][i] = GoFrame.WHITE;
                board[bottom+9][i+9] = GoFrame.BLACK;
            }
            top++; bottom--;

            for (int i = top; i <= bottom; i++) {
                board[i][left] = GoFrame.WHITE;
                board[i+9][left+9] = GoFrame.BLACK;
            }
            left++; right--;

            for (int i = left; i <= right; i++) {
                board[top][i] = GoFrame.WHITE;
                board[top+9][i+9] = GoFrame.BLACK;
            }
            top++; bottom--;

            for (int i = top; i <= bottom; i++) {
                board[i][right] = GoFrame.WHITE;
                board[i+9][right+9] = GoFrame.BLACK;
            }
            left++; right--;
        }
    }

    /**
     * Handles mouse-pressed event
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        int xPos = e.getX();
        int yPos = e.getY();
        canvas.errors = false;

        // translate pixel-coordinates into go-board coordinates,
        Point point = canvas.translateToIndex(new Point(xPos, yPos));
        // invoke the method to handle the "press"
        try {
            if (point != null) {
                this.pressedOnSpace(point.y, point.x);
            }
        }
        catch (Exception xx) {
            canvas.errors = true;
        }
        // repaint canvas
        canvas.repaint();
    }

    //---------------- unused event-handlers ----------------
    public void mouseClicked(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}