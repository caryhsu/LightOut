package lightout.game;

public interface Delta<P extends Position> {

	public int getDeltaValue(P targetPosition, P cursorPosition);
	
}
