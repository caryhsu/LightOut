package lightout.game.solver.foreach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lightout.game.Delta;
import lightout.game.delta.GraphDeltaApplyer;
import lightout.game.solver.SolutionFinder;
import lightout.graph.Graph;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TryAllCaseSolutionFinder implements SolutionFinder {

	private GraphList graphList;
	private Delta delta;
	private Graph initGraph;
	private Graph finalGraph;
	private int moduleNumber;
	
	@Override
	public Graph[] find() {
		
		GraphDeltaApplyer applyer = new GraphDeltaApplyer(initGraph, delta, this.moduleNumber);
		List<Graph> solutions = new ArrayList<>();
		graphList.forEach(pattern ->{
			Graph f = applyer.apply(pattern);			
			f.setModularNumber(finalGraph.getModularNumber());
			if (Arrays.equals(f.getValuesAs1DArray(), finalGraph.getValuesAs1DArray())) {
				solutions.add(pattern);
			}
		});
		return solutions.toArray(new Graph[] {});
	}


}
