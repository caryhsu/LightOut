package lightout.graph.array1d;

import lightout.graph.Position;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(exclude={})
public class Array1DPosition implements Position, Comparable<Array1DPosition> {

	@Getter private int x;
	
	@Override
	public String toString() {
		return "[" + x + "]";
	}

	@Override
	public int compareTo(Array1DPosition o) {
		int r1 = Integer.compare(this.x, o.x);
		return r1;
	}

}
