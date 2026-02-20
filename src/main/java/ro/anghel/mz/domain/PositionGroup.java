package ro.anghel.mz.domain;

import static ro.anghel.mz.domain.Position.AM;
import static ro.anghel.mz.domain.Position.CB;
import static ro.anghel.mz.domain.Position.CM;
import static ro.anghel.mz.domain.Position.DM;
import static ro.anghel.mz.domain.Position.FW;
import static ro.anghel.mz.domain.Position.GK;
import static ro.anghel.mz.domain.Position.WB;
import static ro.anghel.mz.domain.Position.WM;

public enum PositionGroup {

	GLK("Goalkeeper", (short) 1, (short) 1, GK), //
	DEF("Defender", (short) 3, (short) 5, WB, CB), //
	MID("Midfielder", (short) 3, (short) 6, DM, CM, WM, AM), //
	ATT("Attacker", (short) 1, (short) 3, FW);

	private final String longName;
	private final short minCount;
	private final short maxCount;
	private final Position[] positions;

	PositionGroup(final String longName, final short minCount,
			final short maxCount, final Position... positions) {
		this.longName = longName;
		this.positions = positions;
		this.minCount = minCount;
		this.maxCount = maxCount;
	}

	public final String longName() {
		return longName;
	}

	public final short minCount() {
		return minCount;
	}

	public final short maxCount() {
		return maxCount;
	}

	public final Position[] positions() {
		return positions.clone();
	}

}
