package ro.anghel.mz.domain;

public enum Position {
	// - PositionCalculator(Sp, St, PI, Pa, Sh, He, Ke, BC, Ta, AP, SP, Ex, Fo)
	GK("Goalkeeper", "#3366FF", new short[] { 1 }, new PositionCalculator(null,
			11, 100, 10, 3, 0, 0, 64, 6, 1, 4, 1, 100, 100)), //
	WB("Wing Blocker", "#FF8080", new short[] { 0, 1, 2 },
			new PositionCalculator(null, 22, 100, 12, 10, 1, 2, 0, 16, 23, 13,
					1, 100, 100)), //
	CB("Central Blocker", "#FF3E3E", new short[] { 0, 1, 2, 3 },
			new PositionCalculator(null, 21, 100, 12, 10, 1, 2, 0, 17, 25, 11,
					1, 100, 100)), //
	DM("Defensive Midfielder", "#FFC80C", new short[] { 0, 1, 2, 3 },
			new PositionCalculator(null, 20, 100, 12.5, 10.5, 1, 1.5, 0, 20, 23,
					10.5, 1, 100, 100)), //
	CM("Central Midfielder", "#FFFF00", new short[] { 0, 1, 2, 3 },
			new PositionCalculator(null, 19, 100, 13, 11, 2, 1, 0, 23, 20, 10,
					1, 100, 100)), //
	WM("Wing Midfielder", "#FFFF99", new short[] { 0, 1, 2 },
			new PositionCalculator(null, 22, 100, 12.5, 12, 2, 1, 0, 21.5, 15,
					13, 1, 100, 100)), //
	AM("Advanced Midfielder", "#B6E61E", new short[] { 0, 1, 2, 3 },
			new PositionCalculator(null, 22, 100, 13, 12, 10, 1, 0, 22, 10, 10,
					0, 100, 100)), //
	FW("Forward", "#00C000", new short[] { 1, 2, 3 }, new PositionCalculator(
			null, 24, 100, 14, 10, 25, 3, 0, 21, 2, 1, 0, 100, 100));

	private final String longName;
	private final String color;
	private final short[] counts;
	private final PositionCalculator defaultCalculator;
	private final PositionCalculator calculator;

	Position(final String longName, final String color, final short[] counts,
			final PositionCalculator positionCalculator) {
		this.longName = longName;
		this.color = color;
		this.counts = counts;
		defaultCalculator = positionCalculator;
		calculator = new PositionCalculator(defaultCalculator);
		calculator.setPosition(this);
	}

	public String longName() {
		return longName;
	}

	public String color() {
		return color;
	}

	public short[] counts() {
		return counts.clone();
	}

	public PositionCalculator defaultCalculator() {
		return defaultCalculator;
	}

	public PositionCalculator calculator() {
		return calculator;
	}

	public static void resetPositionCalculatorsToDefaults() {
		for (final Position value : values()) {
			value.calculator.copy(value.defaultCalculator);
		}
	}
}
