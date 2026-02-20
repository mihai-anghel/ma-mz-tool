package ro.anghel.mz.domain;

public enum FormStatus {
	OK(' ', "Satisfied"), //
	PLUS('+', "Needs To Rest"), //
	MINUS('-', "Needs To Play"), //
	UNKNOWN('?', "Unknown");

	private final char shortName;
	private final String longName;

	FormStatus(final char shortName, final String longName) {
		this.shortName = shortName;
		this.longName = longName;
	}

	public char shortName() {
		return shortName;
	}

	public String longName() {
		return longName;
	}

	public static FormStatus fromChar(final char shortName) {
		for (final FormStatus status : FormStatus.values()) {
			if (status.shortName == shortName) {
				return status;
			}
		}
		return null;
	}
}
