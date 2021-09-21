import java.awt.*;

/**
 * YOU SHOULD NOT NEED TO MODIFY THIS CLASS
 */
public class GoBoardCanvas extends Canvas {

    // instance-variable -- the board-array
    int[][] board;

    // instance variable -- were there errors?
    boolean errors;

    GoBoardCanvas() {
    }

    /**
     * Determines the ratio of pixels to board squares.
     *
     * @return
     */
    int pixelRatio() {
        Dimension d = this.getSize();
        int xNeed = board.length + 1;
        int yNeed = board[0].length + 1;
        return Math.min(d.width / xNeed, d.height / yNeed);
    }

    /**
     * Translates a pixel point to an index on the Go-board
     *
     * @param pos
     * @return
     */
    Point translateToIndex(Point pos) {
        double ratio = pixelRatio();
        int myX8 = (int) (8 / ratio * (pos.x - ratio / 2)) + 8;
        int myY8 = (int) (8 / ratio * (pos.y - ratio / 2)) + 8;

        if ((myX8 + 7) % 8 >= 6 || (myY8 + 7) % 8 >= 6) {
            return null;
        }
        else {
            return new Point(myX8 / 8 - 1, myY8 / 8 - 1);
        }
    }

    /**
     * Draw the grid and stones
     *
     * @param g
     */
    public void paint(Graphics g) {
        int pixelDelta = pixelRatio();

        drawGrid(g, pixelDelta);
        drawStones(g, pixelDelta);

        if (errors) {
            // paint a big black X if there were errors
            drawX();
        }
    }

    private void drawGrid(Graphics g, int pixelDelta) {
        g.setColor(Color.BLACK);

        for (int i = 0; i < board.length; i++) {
            g.drawLine(pixelDelta, pixelDelta * (i + 1),
                    pixelDelta * (board[0].length), pixelDelta * (i + 1));
        }
        for (int j = 0; j < board[0].length; j++) {
            g.drawLine(pixelDelta * (j + 1), pixelDelta,
                    pixelDelta * (j + 1), pixelDelta * (board.length));
        }
    }

    /**
     * Draw the stones on the canvas
     *
     * @param g
     * @param pixelDelta
     */
    private void drawStones(Graphics g, int pixelDelta) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                switch (board[i][j]) {
                    case GoFrame.WHITE:
                        g.setColor(Color.WHITE);
                        break;
                    case GoFrame.BLACK:
                        g.setColor(Color.BLACK);
                        break;
                    case GoFrame.WHITE_IN_PERIL:
                        g.setColor(Color.PINK);
                        break;
                    case GoFrame.BLACK_IN_PERIL:
                        g.setColor(Color.RED);
                        break;
                    case GoFrame.EMPTY:
                    default:
                        continue;
                }

                g.fillOval(5 * pixelDelta / 8 + (pixelDelta * j),
                        5 * pixelDelta / 8 + (pixelDelta * i),
                        3 * pixelDelta / 4,
                        3 * pixelDelta / 4);
            }
        }
    }

    /**
     * Draw a big black X across the panel.
     */
    private void drawX() {
        Polygon p1 = new Polygon();
        Dimension size = this.getSize();
        p1.addPoint(size.width / 10, 0);
        p1.addPoint(size.width, size.height * 9 / 10);
        p1.addPoint(size.width * 9 / 10, size.height);
        p1.addPoint(0, size.height / 10);

        Polygon p2 = new Polygon();
        p2.addPoint(size.width * 9 / 10, 0);
        p2.addPoint(size.width, size.height / 10);
        p2.addPoint(size.width / 10, size.height);
        p2.addPoint(0, size.height * 9 / 10);

        Graphics g = this.getGraphics();
        g.setColor(Color.BLACK);
        g.fillPolygon(p1);
        g.fillPolygon(p2);
    }
}