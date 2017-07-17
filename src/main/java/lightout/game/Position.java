package lightout.game;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public interface Position {

	@AllArgsConstructor
	@EqualsAndHashCode(exclude={})
	public static class SequialPosition implements Position {
		
		@Getter private int value;
		
	}
}
