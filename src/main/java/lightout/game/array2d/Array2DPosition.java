package lightout.game.array2d;

import lightout.game.Position;
import lightout.game.Position.SequialPosition;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(exclude={})
public class Array2DPosition implements Position {

	@Getter private int x;
	@Getter private int y;
	
}
