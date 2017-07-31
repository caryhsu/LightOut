package lightout.game;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

public abstract class AbstractGraph implements Graph, Cloneable {

	@Getter @Setter protected Integer modularNumber = null;
	
	protected int mod(int n) {
		if (this.modularNumber != null) {
			n %= this.modularNumber;
			if (n < 0) {
				n += this.modularNumber;
			}
		}
		return n;
	}
	
	@SneakyThrows
	@Override
	public Graph clone() {
		return (Graph) super.clone();
	}
	
}
