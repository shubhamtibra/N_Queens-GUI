/**
 *
 * @author shubham
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class EightQueens {
	
        // The thread that the solver runs in
	private static Thread solvingThread;
	
	public static void main(String[] args) {
            
                // Welcome Screen
                JFrame welcome = new JFrame();
                welcome.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                
                JPanel root = new JPanel();
                welcome.setTitle("N Queens Solver");
		welcome.setSize(new Dimension(400, 400));
                
		JTextField n = new HintTextField("Type here");
                JLabel userLabel = new JLabel("Number of Queens:");
                JButton javaButton = new JButton("Solve");
                
                userLabel.setBounds(10,20,80,25);
                n.setBounds(50,20,80,25);
                javaButton.setBounds(30,40,80,25);
                
                root.add(userLabel);
                root.add(n);
                root.add(javaButton);
                welcome.add(root);
                
                welcome.setVisible(true);
                javaButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    
                int N_ = Integer.parseInt(n.getText());
                
                JFrame board = new JFrame();
                board.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		board.setTitle("Solutions");
		board.setSize(new Dimension(400, 400));
		
		// Create the panel that will display the maze
		final Chessboard componentsPanel = new Chessboard(N_);
		
		Container contentPane = board.getContentPane();
		contentPane.add(componentsPanel, BorderLayout.CENTER);
		
		// Solve button
		JPanel buttonPanel = new JPanel();
		JButton startButton = new JButton("Exit");
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);	  
			}
			
		});
                
		buttonPanel.add(startButton);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
                
                EightQueensSolver solver = new EightQueensSolver(componentsPanel);
                solvingThread = new Thread(solver);
                solvingThread.start();
		
		// Display the window.
		board.setVisible(true);
                }
                });
	}

}

class HintTextField extends JTextField implements FocusListener {

  private final String hint;
  private boolean showingHint;

  public HintTextField(final String hint) {
    super(hint);
    this.hint = hint;
    this.showingHint = true;
    super.addFocusListener(this);
  }

  @Override
  public void focusGained(FocusEvent e) {
    if(this.getText().isEmpty()) {
      super.setText("");
      showingHint = false;
    }
  }
  @Override
  public void focusLost(FocusEvent e) {
    if(this.getText().isEmpty()) {
      super.setText(hint);
      showingHint = true;
    }
  }

  @Override
  public String getText() {
    return showingHint ? "" : super.getText();
  }
}
