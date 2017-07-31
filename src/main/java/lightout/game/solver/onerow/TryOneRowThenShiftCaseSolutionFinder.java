package lightout.game.solver.onerow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import lightout.game.Delta;
import lightout.game.Graph;
import lightout.game.GraphApplyer;
import lightout.game.array2d.Array2DGraph;
import lightout.game.solver.SolutionFinder;
import lightout.game.solver.foreach.GraphList;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TryOneRowThenShiftCaseSolutionFinder implements SolutionFinder {

	private GraphList graphList;
	private Delta delta;
	private Graph initGraph;
	private Graph finalGraph;
	private int moduleNumber;
			
	@Override
	public Graph[] find() {
		Predicate<Graph> endPreidcate = (g) -> {
			return ((Array2DGraph) g).getVertexesForRow(0).stream().allMatch(v->v.getValue()==(g.getModularNumber()-1));
		};
		graphList.setEndPreidcate(endPreidcate);
		
		GraphApplyer applyer = new GraphApplyer(initGraph, delta, this.moduleNumber);
		List<Graph> solutions = new ArrayList<>();
		graphList.forEach(pattern0 -> {
			//System.out.println("1:(initGraph)\n" + initGraph);
			//System.out.println("2:(iterator item)\n" + pattern0);
			Array2DGraph pattern = new Array2DGraph(((Array2DGraph) initGraph).getWidth(), ((Array2DGraph) initGraph).getHeight());
			Array2DGraph result = null;// new Array2DGraph(((Array2DGraph) initGraph).getWidth(), ((Array2DGraph) initGraph).getHeight());
			for(int i = 0; i < ((Array2DGraph) initGraph).getWidth(); i++) {
				// copy row from (pattern0 or result) to pattern
				if (i == 0) 
					copyRow((Array2DGraph) pattern0, 0, pattern, 0, false);
				else
					copyRow(result, i-1, pattern, i, true);
				result = (Array2DGraph) applyer.apply(pattern);
				//System.out.println("3:(pattern)\n" + pattern);
				//System.out.println("4:(after merge)\n" + result);
			}
			Array2DGraph f = (Array2DGraph) applyer.apply(pattern);
			//System.out.println("5:(final test)\n" + f);
			f.setModularNumber(finalGraph.getModularNumber());
			if (Arrays.deepEquals(f.getValues(), ((Array2DGraph) finalGraph).getValues())) {
				solutions.add(pattern);
			}
		});
		return solutions.toArray(new Graph[] {});
	}

	private void copyRow(Array2DGraph srcGraph, int srcRow, Array2DGraph dstGraph, int dstRow, boolean reverse) {
		for(int j = 0; j < ((Array2DGraph) initGraph).getHeight(); j++) {
			int value = srcGraph.get(srcRow, j);
			if (reverse) {
				value = srcGraph.getModularNumber() - value; 
			}
			dstGraph.set(dstRow, j, value);
		}
	}

//	private Array2DGraph merge(Array2DGraph pattern0, Array2DGraph initGraph, ) {
//		Array2DGraph g = new Array2DGraph(initGraph.getWidth(), initGraph.getHeight());
//		g.setModularNumber(this.moduleNumber);
//		for(int i = 0; i < initGraph.getWidth(); i++) {
//			for(int j = 0; j < initGraph.getHeight(); j++) {
//				int value = (i == 0? pattern0.get(i, j) : initGraph.get(i-1, j));
//				g.set(i, j, value);
//			}
//		}
//		return g;
//	}


}
