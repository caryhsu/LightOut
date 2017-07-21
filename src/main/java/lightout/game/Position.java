package lightout.game;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public interface Position {

	@AllArgsConstructor
	@EqualsAndHashCode(exclude={})
	public static class SequialPosition implements Position, Comparable<SequialPosition> {
		
		@Getter private int value;

		@Override
		public int compareTo(SequialPosition o) {
			return Integer.compare(this.value, o.value);
		}
		
	}
}
