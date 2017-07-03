package lightout.board;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lightout.Game;
import lightout.solver.PercentSolvableCalculator;
import lightout.solver.Solver;

public class Board extends JFrame {
	// Fields that deals with the puzzle's logic
	private Game game;

	// Fields that deals with GUI
	// private JPanel m = new JPanel(new FlowLayout());
	private JPanel m = new JPanel();
	private JPanel p = new JPanel();
	private JPanel sideP = new JPanel();
	private JPanel sideButtonP = new JPanel();
	private JPanel sideLabelP = new JPanel();
	private JPanel dropDownP = new JPanel();
	private JPanel solutionP = new JPanel();
	private JPanel subSolutionP = new JPanel();

	// Buttons
	private JButton[][] buttons;
	private JButton solveB = new JButton();
	private JButton resetB = new JButton();
	private JButton randomizeB = new JButton();
	private JButton editB = new JButton();

	// labels
	private JLabel currentL = new JLabel();
	private JLabel bestL = new JLabel();
	private JLabel[][] solutionGrid;
	private JLabel solutionHeader;
	private JLabel percentSolvableLabel;

	// combo Boxes

	// boolean edit
	private int editOnOff = 0;

	public Board(int size, int state) {
		// Board GUI
		super("Lights Out!");
		setSize(820, 635);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().add(m);

		m.setBackground(GameColorManager.getColor("#E0E0E0"));
		m.add(p);
		m.add(sideP);
		sideP.setLayout(new FlowLayout());
		// sideButtonP.setLayout(new BoxLayout(sideButtonP, BoxLayout.Y_AXIS));
		sideButtonP.setLayout(new GridLayout(5, 1));
		sideLabelP.setLayout(new BoxLayout(sideLabelP, BoxLayout.Y_AXIS));
		solutionP.setLayout(new BoxLayout(solutionP, BoxLayout.Y_AXIS));
		dropDownP.setLayout(new BoxLayout(dropDownP, BoxLayout.Y_AXIS));

		// set Sizes
		sideButtonP.setPreferredSize(new Dimension(200, 100));
		dropDownP.setPreferredSize(new Dimension(200, 100));
		solutionP.setPreferredSize(new Dimension(200, 200));
		subSolutionP.setPreferredSize(new Dimension(200, 200));

		// add Components
		sideP.add(sideLabelP);
		sideP.add(solutionP);
		sideP.add(dropDownP);
		sideP.add(sideButtonP);

		// SideP GUI
		sideP.setPreferredSize(new Dimension(200, 600));

		// Set puzzle's parameter
		this.game = new Game(size, state);

		// initialize GUI for side Panel
		// need to add methods for these buttons
		solveB.setText("Show Solution");
		solveB.setBackground(GameColorManager.getColor("#CCCCCC"));
		solveB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				publishSolution();
			}
		});
		sideButtonP.add(solveB);
		resetB.setText("Reset Board");
		resetB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.reset();
				refreshModelBinding();
			}
		});
		resetB.setBackground(GameColorManager.getColor("#CCCCCC"));
		sideButtonP.add(resetB);
		randomizeB.setText("Randomize Board");
		randomizeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.randomize();
				refreshModelBinding();
			}
		});
		randomizeB.setBackground(GameColorManager.getColor("#CCCCCC"));
		sideButtonP.add(randomizeB);
		editB.setText("Edit Board");
		editB.setBackground(GameColorManager.getColor("#CCCCCC"));
		editB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editOnOff = (editOnOff + 1) % 2;
				if (editOnOff == 1) {
					game.setEditMode(true);
					editB.setBackground(GameColorManager.getColor("#AAAAAA"));
				} else {
					game.setEditMode(false);
					editB.setBackground(GameColorManager.getColor("#CCCCCC"));
				}
			}
		});
		sideButtonP.add(editB);

		// initialize GUI for side Label Panel
		// need to add methods for these panels
		JLabel lightsOutHeader = new JLabel();
		lightsOutHeader.setText("Lights Out");
		// lightsOutHeader.setFont(new Font("Lights Out", Font.BOLD, 12));
		currentL.setText("Current Moves: " + game.getNumberOfClicks());
		sideLabelP.add(lightsOutHeader);
		sideLabelP.add(currentL);

		// initialize GUI for Solution Panel
		// also includes sub solution

		solutionHeader = new JLabel();
		solutionHeader.setText("Solution");
		solutionP.add(solutionHeader);
		percentSolvableLabel = new JLabel("100% Solvable");
		solutionP.add(percentSolvableLabel);

		// initialize comboBoxes for dropDown Panel
		String[] boardSizes = { "3x3", "4x4", "5x5", "6x6", "7x7", "8x8" };
		String[] numOfColors = { "2 colors", "3 colors", "5 colors", "7 colors" };
		final JComboBox boardSizeCB = new JComboBox(boardSizes);
		final int color = state;
		boardSizeCB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String s = (String) boardSizeCB.getSelectedItem();
				switch (s) {
				case "3x3":
					game.setSize(3);
					createBoard();
					p.revalidate();
					subSolutionP.revalidate();
					break;
				case "4x4":
					game.setSize(4);
					createBoard();
					p.revalidate();
					subSolutionP.revalidate();
					break;
				case "5x5":
					game.setSize(5);
					createBoard();
					p.revalidate();
					subSolutionP.revalidate();
					break;
				case "6x6":
					game.setSize(6);
					createBoard();
					p.revalidate();
					subSolutionP.revalidate();
					break;
				case "7x7":
					game.setSize(7);
					createBoard();
					p.revalidate();
					subSolutionP.revalidate();
					break;
				case "8x8":
					game.setSize(8);
					createBoard();
					p.revalidate();
					subSolutionP.revalidate();
					break;
				}
			}

		});
		final JComboBox colorsCB = new JComboBox(numOfColors);
		colorsCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = (String) colorsCB.getSelectedItem();
				switch (s) {
				case "2 colors":
					setState(2);
					createBoard();
					p.revalidate();
					break;
				case "3 colors":
					setState(3);
					createBoard();
					p.revalidate();
					break;
				case "5 colors":
					setState(5);
					createBoard();
					p.revalidate();
					break;
				case "7 colors":
					setState(7);
					createBoard();
					p.revalidate();
					break;
				}
			}

		});
		dropDownP.add(boardSizeCB);
		dropDownP.add(colorsCB);
		// initialize the buttons
		createBoard();
		setVisible(true);
	}

	public void createBoard() {
		// Compute how unsolvable the current version of the puzzle is
		
		p.removeAll();
		// initialize the values
		game.reset();
		//game.randomize();
		
		int size = game.getSize();
		int[][] valueTable = game.getValueTable();
		
		p.setLayout(new GridLayout(size, size));
		p.setPreferredSize(new Dimension(600, 600));
		p.setBackground(GameColorManager.getColor("#F0F0F0"));
		buttons = new JButton[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				final int x = i;
				final int y = j;
				buttons[i][j] = new JButton(valueTable[i][j] + "");
				buttons[i][j].setFont(new Font("Dialog", Font.PLAIN, 30));
				buttons[i][j].setOpaque(true);
				/*
				 * buttons[i][j].addActionListener(new ActionListener() {
				 * 
				 * @Override public void actionPerformed(ActionEvent e) {
				 * press(x, y); } });
				 */
				buttons[i][j].addMouseListener(new MouseListener() {
					@Override
					public void mousePressed(MouseEvent e) {
						// do some stuff
					}

					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						game.setCursor(x, y);
						refreshModelBinding();
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						game.clearCursor();
						refreshModelBinding();
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						game.select(x, y);
						refreshModelBinding();
					}
				});
				p.add(buttons[i][j]);
			}
		}
				
		subSolutionP.removeAll();
		subSolutionP.setLayout(new GridLayout(size, size));
		subSolutionP.setBackground(GameColorManager.getColor("#F0F0F0"));

		solutionGrid = new JLabel[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				solutionGrid[i][j] = new JLabel("0");
				subSolutionP.add(solutionGrid[i][j]);
			}
		}
		solutionP.add(subSolutionP);
		
		this.refreshModelBinding();
	}
	
	private void refreshModelBinding() {
		int size = game.getSize();
		int[][] valueTable = game.getValueTable();
		
		GameColorManager cm = new GameColorManager(game.getState());
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (game.isHighlight(i, j)) {
					buttons[i][j].setBackground(Color.decode(cm.darkenColor(valueTable[i][j])));
				}
				else {
					Color theColor = GameColorManager.getColor(cm.colorHex(valueTable[i][j]));
					buttons[i][j].setBackground(theColor);
				}
				buttons[i][j].setText("" + valueTable[i][j]);
			}
		}
		currentL.setText("Current Moves: " + game.getNumberOfClicks());
		
		double percentSolvable = game.getPercentSolvable() * 100;
		percentSolvableLabel.setText((new DecimalFormat("#0.000000")).format(percentSolvable) + "% Solvable");
		
	}

	public void publishSolution() {
		int size = game.getSize();
		int state = game.getState();
		int[][] valueTable = game.getValueTable();
		// Create a solver
		Solver einstein = new Solver(size, size, state);
		
		// Build the b vector
		int[] b = new int[size*size];
		int index = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				b[index] = state - valueTable[i][j];
				index++;
			}
		}
		
		// Give the b vector to the solver and solve
		einstein.setBVector(b);
		einstein.RowReduce();
		
		// Count the row of zero
		PercentSolvableCalculator c = new PercentSolvableCalculator(einstein);
		double percentSolvable = c.calculate().doubleValue() * 100;
		percentSolvableLabel.setText((new DecimalFormat("#0.00")).format(percentSolvable) + "% Solvable");

		
		// Check if a solution exists
		if (einstein.hasSolution()) {
			solutionHeader.setText("Solution");
			solutionHeader.setForeground(Color.black);
			int[][] solution = einstein.publishSolution();
			for (int i = 0; i < size; i++){
				for (int j = 0; j < size; j++) {
					solutionGrid[i][j].setText("" + solution[i][j]);
				}
			} 
		} else {
			solutionHeader.setText("NO SOLUTION!");
			solutionHeader.setForeground(Color.red);
		}
	}

	public int[] publishB() {
		int size = game.getSize();
		int state = game.getState();
		int[][] valueTable = game.getValueTable();
		int[] b = new int[size * size];
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				b[count] = (state - valueTable[i][j]) % state;
				count++;
			}
		}
		return b;
	}

	public int[][] solve() {
		int[][] valueTable = game.getValueTable();
		return valueTable;
	}
	

}
