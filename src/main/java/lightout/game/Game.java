package lightout.game;

public interface Game {

	void reset();

	void randomize();

	boolean isEditMode();
	void setEditMode(boolean b);

	int getNumberOfClicks();

	int getState();
	void setState(int state);

	Graph getValues();

	Position getCursor();
	void setCursor(Position position);

	void clearCursor();

	void select(Position position);

	int getDeltaValue(int i, int j);

	double getPercentSolvable();
	

}
