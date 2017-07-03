package lightout.board;

import java.awt.Color;

public class GameTheme {

	private int state;

	public GameTheme(int state) {
		this.state = state;
	}
	
	public static Color getColor(String a) {
		return Color.decode(a);
	}
	
	public String darkenColor(int currState) {
		int r, g, b;
		String rHex, gHex, bHex;
		int[] color1 = { 45, 117, 182 };
		int[] color2 = { 255, 255, 255 };
		double x = (double) (currState) / (state - 1); // Percentage of color1
														// in the mixture
		r = (int) ((x * color1[0] + (1 - x) * color2[0]) * 0.7);
		g = (int) ((x * color1[1] + (1 - x) * color2[1]) * 0.7);
		b = (int) ((x * color1[2] + (1 - x) * color2[2]) * 0.7);

		rHex = Integer.toHexString(r);
		gHex = Integer.toHexString(g);
		bHex = Integer.toHexString(b);

		if (rHex.length() == 1) {
			rHex = "0" + rHex;
		}
		if (gHex.length() == 1) {
			gHex = "0" + gHex;
		}
		if (bHex.length() == 1) {
			bHex = "0" + bHex;
		}

		return "#" + rHex + gHex + bHex;
	}

	public String colorHex(int currState) {
		int r, g, b;
		String rHex, gHex, bHex;
		int[] color1 = { 45, 117, 182 };
		int[] color2 = { 255, 255, 255 };
		double x = (double) (currState) / (state - 1); // Percentage of color1
														// in the mixture
		r = (int) (x * color1[0] + (1 - x) * color2[0]);
		g = (int) (x * color1[1] + (1 - x) * color2[1]);
		b = (int) (x * color1[2] + (1 - x) * color2[2]);

		rHex = Integer.toHexString(r);
		gHex = Integer.toHexString(g);
		bHex = Integer.toHexString(b);

		if (rHex.length() == 1) {
			rHex = "0" + rHex;
		}
		if (gHex.length() == 1) {
			gHex = "0" + gHex;
		}
		if (bHex.length() == 1) {
			bHex = "0" + bHex;
		}

		return "#" + rHex + gHex + bHex;
	}


}
