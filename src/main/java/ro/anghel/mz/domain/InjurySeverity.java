package ro.anghel.mz.domain;

public enum InjurySeverity {
	NONE(""), //
	RECOVER("Recover"), //
	SEVERE("Severe");

	private final String niceName;

	InjurySeverity(final String aNiceName) {
		niceName = aNiceName;
	}

	public String niceName() {
		return niceName;
	}
}
