package ro.anghel.mz.domain;

public class Injury {
	private final InjurySeverity injurySeverity;

	private String injuryName;

	private final int injuryDays;

	public Injury(final InjurySeverity aInjurySeverity,
			final String aInjuryName, final int aInjuryDays) {
		injurySeverity = aInjurySeverity;
		if (InjurySeverity.NONE.equals(aInjurySeverity)) {
			injuryName = "";
			injuryDays = 0;
		} else {
			injuryName = aInjuryName;
			injuryDays = aInjuryDays;
		}
	}

	public Injury() {
		injurySeverity = InjurySeverity.NONE;
		injuryName = "";
		injuryDays = 0;
	}

	public InjurySeverity getInjurySeverity() {
		return injurySeverity;
	}

	public String getInjuryName() {
		return injuryName;
	}

	public int getInjuryDays() {
		return injuryDays;
	}
}