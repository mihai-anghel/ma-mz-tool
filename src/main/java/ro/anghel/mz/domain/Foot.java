package ro.anghel.mz.domain;

public enum Foot {
	LEFT('L', "Left"), //
	BOTH('B', "Right / Left"), //
	RIGHT('R', "Right");

	private final char shortName;
	private final String longName;

	Foot(final char shortName, final String longName) {
		this.shortName = shortName;
		this.longName = longName;
	}

	public char shortName() {
		return shortName;
	}

	public String longName() {
		return longName;
	}

	public static Foot fromChar(final char shortName) {
		final char normalized = Character.toUpperCase(shortName);
		for (final Foot foot : values()) {
			if (foot.shortName == normalized) {
				return foot;
			}
		}
		if (normalized == '*' || normalized == '/' || normalized == ' '
				|| normalized == '-') {
			return Foot.BOTH;
		}
		return null;
	}

	public static Foot fromString(final String shortOrLongName) {
		if (shortOrLongName == null || shortOrLongName.isBlank()) {
			return null;
		}

		final String upper = shortOrLongName.toUpperCase().trim();

		// Explicit BOTH shortcut
		if (upper.equals("BOTH")) {
			return Foot.BOTH;
		}

		// Single character shortcut
		if (upper.length() == 1) {
			return fromChar(upper.charAt(0));
		}

		// Detect presence of L and R, including words LEFT / RIGHT
		final boolean hasL = upper.contains("L") || upper.contains("LEFT");

		// Decide result
		if (hasL) {
			final boolean hasR = upper.contains("R") || upper.contains("RIGHT");
			return hasR ? Foot.BOTH : LEFT;
		}
		final boolean hasR = upper.contains("R") || upper.contains("RIGHT");
		return hasR ? Foot.RIGHT : null;
	}

}