package lightout.math.algebra;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(exclude= {})
public class ZnElement {
	@Getter private Zn zn;
	@Getter int value;
}