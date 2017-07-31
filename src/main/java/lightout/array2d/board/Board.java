package lightout.array2d.board;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lightout.game.Graph;
import lightout.game.Rectangle;
import lightout.game.array2d.Array2DGraph;
import lightout.game.array2d.Array2DPosition;
import lightout.game.delta.NeighberhoodDelta;
import lightout.game.solver.grouping.PercentSolvableCalculator;
import lightout.game.solver.matrix.Solver;
import lightout.game.solver.matrix.vectorb.VectorBFactoryImpl1;
import lombok.Getter;
import lombok.Setter;

public class Board extends JFrame {
	// Fields that deals with the puzzle's logic
	@Getter @Setter private BoardViewModel game;

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
		this.game = new BoardViewModel(size, size, state);

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
		List<String> boardSizesList = new ArrayList<String>();
		for(int i = 3; i <= 20; i++) {
			boardSizesList.add("" + i + "x" + i);
		}
		String[] boardSizes = boardSizesList.toArray(new String[] {});
		List<String> numOfColorsList = new ArrayList<String>();
		for(int i = 2; i <= 20; i++) {
			numOfColorsList.add("" + i + " colors");
		}
		String[] numOfColors = numOfColorsList.toArray(new String[] {});
		final JComboBox boardSizeCB = new JComboBox(boardSizes);
		final int color = state;
		boardSizeCB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = (String) boardSizeCB.getSelectedItem();
				String[] ss = s.split("x");
				int width = Integer.parseInt(ss[0]);
				int height = Integer.parseInt(ss[1]);
				((Rectangle) game).setSize(width, height);
				createBoard();
				p.revalidate();
				subSolutionP.revalidate();
			}

		});
		final JComboBox colorsCB = new JComboBox(numOfColors);
		colorsCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = (String) colorsCB.getSelectedItem();
				Integer colors = Integer.parseInt(s = s.split(" ")[0]);
				game.setState(colors);
				createBoard();
				p.revalidate();
				subSolutionP.revalidate();
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
		
		int width = ((Rectangle) game).getWidth();
		int height = ((Rectangle) game).getHeight();
		Graph values = game.getGraph();
		
		p.setLayout(new GridLayout(width, height));
		p.setPreferredSize(new Dimension(600, 600));
		p.setBackground(GameColorManager.getColor("#F0F0F0"));
		buttons = new JButton[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				final int x = i;
				final int y = j;
				Array2DPosition position = new Array2DPosition(i, j);
				buttons[i][j] = new JButton(values.get(position) + "");
				buttons[i][j].setFont(new Font("Dialog", Font.PLAIN, 60 - 3 * ((Rectangle) game).getWidth()));
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
						game.setCursor(new Array2DPosition(x, y));
						refreshModelBinding();
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						game.clearCursor();
						refreshModelBinding();
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						game.select(new Array2DPosition(x, y));
						refreshModelBinding();
					}
				});
				p.add(buttons[i][j]);
			}
		}
				
		subSolutionP.removeAll();
		subSolutionP.setLayout(new GridLayout(width, height));
		subSolutionP.setBackground(GameColorManager.getColor("#F0F0F0"));

		solutionGrid = new JLabel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				solutionGrid[i][j] = new JLabel("0");
				subSolutionP.add(solutionGrid[i][j]);
			}
		}
		solutionP.add(subSolutionP);
		
		this.refreshModelBinding();
	}
	
	private void refreshModelBinding() {
		int width = ((Rectangle) game).getWidth();
		int height = ((Rectangle) game).getHeight();
		Graph values = game.getGraph();
		
		//GameColorManager cm = new GameColorManager(game.getState());
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Array2DPosition position = new Array2DPosition(i, j);
				if (game.getDeltaValue(i, j) > 0) {
		//			buttons[i][j].setBackground(Color.decode(cm.darkenColor(values.get(position))));
				}
				else {
		//			Color theColor = GameColorManager.getColor(cm.colorHex(values.get(position)));
		//			buttons[i][j].setBackground(theColor);
				}
				buttons[i][j].setText("" + values.get(position));
			}
		}
		currentL.setText("Current Moves: " + game.getNumberOfClicks());
		
		double percentSolvable = game.getPercentSolvable() * 100;
		percentSolvableLabel.setText((new DecimalFormat("#0.000000")).format(percentSolvable) + "% Solvable");
		
	}

	public void publishSolution() {
		int width = ((Rectangle) game).getWidth();
		int height = ((Rectangle) game).getHeight();
		int state = game.getState();
		
		Array2DGraph graph = game.getGraph();
		// Create a solver
		NeighberhoodDelta delta = new NeighberhoodDelta(graph);
		Solver einstein = new Solver(width, height, state, delta);

		// Build the b vector
		int[] b = new VectorBFactoryImpl1(delta).newInstance();
				
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
			for (int i = 0; i < width; i++){
				for (int j = 0; j < height; j++) {
					solutionGrid[i][j].setText("" + solution[i][j]);
				}
			} 
		} else {
			solutionHeader.setText("NO SOLUTION!");
			solutionHeader.setForeground(Color.red);
		}
	}

	public int[] publishB() {
		int width = ((Rectangle) game).getWidth();
		int height = ((Rectangle) game).getHeight();
		int state = game.getState();
		Graph values = game.getGraph();
		int[] b = new int[width * height];
		int count = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Array2DPosition position = new Array2DPosition(i, j);
				b[count] = (state - values.get(position)) % state;
				count++;
			}
		}
		return b;
	}

	public int[][] solve() {
		Array2DGraph values = (Array2DGraph) game.getGraph();
		return values.getValues();
	}
	

}
