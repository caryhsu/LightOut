package lightout.game.solver;

import lightout.game.Game;
import lightout.game.Graph;

public interface SolutionFinder {
	
	public Solution[] find(Graph graph);
	
}
