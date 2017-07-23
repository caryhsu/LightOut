package lightout.game.solver.foreach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Objects;

import lightout.game.Delta;
import lightout.game.Graph;
import lightout.game.GraphApplyer;
import lightout.game.solver.SolutionFinder;
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
		System.out.println("init case:");
		System.out.println(initGraph);
		System.out.println("final case:");
		System.out.println(finalGraph);
		
		GraphApplyer applyer = new GraphApplyer(initGraph, delta, this.moduleNumber);
		List<Graph> solutions = new ArrayList<>();
		graphList.forEach(pattern ->{
			Graph f = applyer.apply(pattern);
			/*
			System.out.println("try pattern:");
			System.out.println(pattern);
			System.out.println("result:");
			System.out.println(f);
			*/
			f.setModularNumber(finalGraph.getModularNumber());
			if (Arrays.deepEquals(f.getValues(), finalGraph.getValues())) {
				solutions.add(pattern);
			}
		});
		return solutions.toArray(new Graph[] {});
	}


}
