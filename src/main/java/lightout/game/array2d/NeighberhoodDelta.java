package lightout.game.array2d;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lightout.game.Delta;
import lightout.game.Graph;
import lightout.game.Position;
import lombok.Getter;

public class NeighberhoodDelta implements Delta {

	@Getter private Graph graph;
	
	public NeighberhoodDelta(Graph graph) {
		this.graph = graph;
	}
	
	@Override
	public int getDeltaValue(Position target, Position cursor) {
		if (Objects.equals(target, cursor)) {
			return 1;
		} else {
			List<Position> neighberhood = Arrays.asList(graph.getNeighberhood(cursor));
			if (neighberhood.contains(target)) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

}
