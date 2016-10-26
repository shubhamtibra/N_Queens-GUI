/**
 *
 * @author shubham
 */
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chessboard extends JPanel {
	// Size of the board
	private final int NUM_ROWS;
	private final int NUM_COLS;
        private int[][] cord;
	
	// The cells that make up the board
	private JLabel[][] grid;
	
	// Unicode character for a queen
	private static final char QUEEN = '\u2655';
	
	/**
	 * Creates and displays a new, empty chessboard.
	 */
	public Chessboard(int n) {
            NUM_ROWS = n;
            NUM_COLS = n;
            grid = new JLabel[NUM_ROWS][NUM_COLS];
		setLayout (new GridLayout (NUM_ROWS, NUM_COLS));
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {
				JLabel newCell = new JLabel("", JLabel.CENTER);
				newCell.setFont(newCell.getFont().deriveFont(20.0f));
				newCell.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				grid[i][j] = newCell;
				add(newCell);
			}
		}
		
		//randomlyPlaceQueens();
	}

	/**
	 * Randomly place queens on the board.  Just for demo purposes.
	 */
	private void randomlyPlaceQueens() {
		Random locGen = new Random();
		int numQueens = 0;
		while (numQueens < NUM_COLS) {
			int x = locGen.nextInt(NUM_COLS);
			int y = locGen.nextInt(NUM_COLS);
			if (isEmpty(x, y)) {
				addQueen(x, y);
				numQueens++;
			}
		}
	}
	
	/**
	 * 
	 * @return number of rows in the board
	 */
	public int getNumRows() {
		return NUM_ROWS;
	}

	/**
	 * 
	 * @return number of columns in the board
	 */
	public int getNumCols() {
		return NUM_COLS;
	}

	/**
	 * Add a queen to cell x, y
	 * @param x row of board
	 * @param y column of board
	 */
	public void addQueen(int x, int y) {
		grid[x][y].setText("" + QUEEN);		
	}

	/**
	 * Remove the queen from cell x, y
	 * @param x row of board
	 * @param y column of board
	 */
	public void removeQueen(int x, int y) {
		grid[x][y].setText("");		
	}
	
	/**
	 * Returns true if we can place a queen here that will be unable to capture
	 * any of the existing queens
	 * @param x row to place queen
	 * @param y column to place queen
	 * @return
	 */
	public boolean isLegal (int x, int y) {
		if (!isEmpty(x, y)) {
			return false;
		}
		
		if (!isRowEmpty(x)) {
			return false;
		}

		if (!isColEmpty(y)) {
			return false;
		}
		
		if (!isLeftDiagEmpty(x, y)) {
			return false;
		}
		
		if (!isRightDiagEmpty(x, y)) {
			return false;
		}
		
		return true;
	}

	/**
	 * @param x row to check
	 * @return true if the row is empty
	 */
	private boolean isRowEmpty(int x) {
		for (int y = 0; y < NUM_COLS; y++) {
			if (!isEmpty(x, y)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param y column to check
	 * @return true if the column is empty
	 */
	private boolean isColEmpty(int y) {
		for (int x = 0; x < NUM_ROWS; x++) {
			if (!isEmpty(x, y)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param startX row to check
	 * @param startY column to check
	 * @return true if the diagonal going up-left and down-right is empty
	 */
	private boolean isLeftDiagEmpty(int startX, int startY) {
		for (int x = startX, y = startY; x < NUM_ROWS && y < NUM_COLS	; x++, y++) {
			if (!isEmpty(x, y)) {
				return false;
			}
		}

		for (int x = startX, y = startY; x >= 0 && y >= 0; x--, y--) {
			if (!isEmpty(x, y)) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @param startX row to check
	 * @param startY column to check
	 * @return true if the diagonal going up-right and down-left is empty
	 */
	private boolean isRightDiagEmpty(int startX, int startY) {
		for (int x = startX, y = startY; x < NUM_ROWS && y >= 0; x++, y--) {
			if (!isEmpty(x, y)) {
				return false;
			}
		}

		for (int x = startX, y = startY; x >= 0 && y < NUM_COLS; x--, y++) {
			if (!isEmpty(x, y)) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @param x row to check
	 * @param y column to check
	 * @return true if the square is empty
	 */
	public boolean isEmpty(int x, int y) {
		return grid[x][y].getText().equals("");
	}


}
