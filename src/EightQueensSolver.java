import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shubham
 */

public class EightQueensSolver implements Runnable {
	
        // The chessboard
	private Chessboard board;
        // Solution of the Problem
        private List<int[][]> sol;
        private int[][] cord;
        
        // Copies an Array
	public static int[][] cloneArray(int[][] src) {
            int length = src.length;
            int[][] target = new int[length][src[0].length];
            for (int i = 0; i < length; i++) {
                System.arraycopy(src[i], 0, target[i], 0, src[i].length);
            }
            return target;
        }
	// Number of queens placed so far.
	private int numQueens = 0;
	
	/**
	 * Create an eight queens solver
	 * @param b the chessboard to place queens on
	 */
	public EightQueensSolver(Chessboard b) {
            board = b;
            sol = new ArrayList< >();
            cord = new int[board.getNumCols()][board.getNumCols()];
                
	}
	
	/**
	 * Place the queens on the board.
         * @return 
	 * @throws InterruptedException if the thread that the solver is
	 * 	running in is interrupted.
	 */
	public List<int[][]> placeQueens() throws InterruptedException {
		
            // Solve and store the solution
            placeQueens(0);
            
            // Displaying the solutions
            for (int i = 0; i < sol.size(); i++){
                int[][] uu = sol.get(i);
                
                // Remove the displayed queens
                 for (int row = 0; row < uu.length; row++) 
                    for (int col = 0; col < uu[row].length; col++) 
                        if( !board.isEmpty(row, col)) board.removeQueen(row, col);

                for (int row = 0; row < uu.length; row++) 
                    for (int col = 0; col < uu[row].length; col++) 
                           if( uu[row][col] == 1) board.addQueen(row, col); 
                
                // Wait to Print the next solution
                Thread.sleep(2000);            
            }
            return sol;	
	}
	
	/**
	 * Place a queen in the given row and recursively in all rows that follow it.
	 * @param row the row to place a queen in
	 * @return true if all the queens could be placed
	 * @throws InterruptedException if the thread that the solver is running in is interrupted
	 */
	private void placeQueens(int row) throws InterruptedException {
            
            // A solution is found. Store in the list.
            if (row == board.getNumRows()) sol.add(cloneArray(cord));
				
            else{
                
		for (int col = 0; col < board.getNumCols(); col++){
		    if(isSafe(cord, row, col)) 
                        {
                        numQueens++;
                        cord[row][col] = 1;
                        placeQueens(row+1);
                    }
                // Failed attempt. Backtrack the co-ordinates.
                numQueens--;
                cord[row][col] = 0;
                    }
                }
       }
        
       boolean isSafe(int board[][], int row, int col)
    {
        int i, j;
 
        /* Check this row on left side */
        for (i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;
 
        /* Check upper diagonal on left side */
        for (i=row, j=col; i >=0 && j>=0; i--, j--)
            if (board[i][j] == 1)
                return false;
 
        /* Check lower diagonal on left side */
        for (i=row, j=col; j < board.length && i >= 0; i--, j++)
            if (board[i][j] == 1)
                return false;
 
        return true;
    }

	/**
	 * Solve the eight queens problem.
	 */
	public void run() {
		try {
			placeQueens();
		} catch (InterruptedException e) {
			// Thread stops.
		}
	}
}
