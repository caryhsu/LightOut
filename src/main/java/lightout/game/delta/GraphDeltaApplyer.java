package lightout.game.delta;

import java.util.ArrayList;
import java.util.List;

import lightout.graph.Graph;
import lightout.graph.Position;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GraphDeltaApplyer {

	private Graph target;
	private Delta delta;
	private int moduleNumber;
	
	public Graph apply(Graph pattern) {
		Graph target = this.target.clone();
		target.setModularNumber(this.moduleNumber);
		List<Position> positions = new ArrayList<>();
		pattern.getVertexes().forEach(v->{
				for(int i = 0; i < v.getValue(); i++) {
					positions.add(v.getPosition());
				}}
			);
		Position[] psArray = positions.toArray(new Position[] {});
		delta.setTarget(target);
		delta.apply(psArray);
		delta.setTarget(this.target);
		return target;
	}
}
