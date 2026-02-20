package ro.anghel.mz.domain;

public class PositionCalculatorSettings {

	public static final PositionCalculatorSettings DEFAULT = new PositionCalculatorSettings(
			true, 0.2 /* 0.050 */, 0.1 /* 0.025 */, 0.3 /* 0.100 */);
	public static final PositionCalculatorSettings INSTANCE = new PositionCalculatorSettings(
			DEFAULT);

	private boolean useStaminaExperienceForm;

	private double staminaFactor;

	private double experienceFactor;

	private double formFactor;

	private PositionCalculatorSettings(final boolean useStaminaExperienceForm,
			final double staminaFactor, final double experienceFactor,
			final double formFactor) {
		this.useStaminaExperienceForm = useStaminaExperienceForm;
		this.staminaFactor = staminaFactor;
		this.experienceFactor = experienceFactor;
		this.formFactor = formFactor;
	}

	private PositionCalculatorSettings(
			final PositionCalculatorSettings settings) {
		this(settings.useStaminaExperienceForm, settings.staminaFactor,
				settings.experienceFactor, settings.formFactor);
	}

	public boolean useStaminaExperienceForm() {
		return useStaminaExperienceForm;
	}

	public double getStaminaFactor() {
		return staminaFactor;
	}

	public double getExperienceFactor() {
		return experienceFactor;
	}

	public double getFormFactor() {
		return formFactor;
	}

	public static void setUseStaminaExperienceForm(
			final boolean aUseStaminaExperienceForm) {
		INSTANCE.useStaminaExperienceForm = aUseStaminaExperienceForm;
	}

	public static void setStaminaFactor(final double aStaminaFactor) {
		INSTANCE.staminaFactor = aStaminaFactor;
	}

	public static void setExperienceFactor(final double aExperienceFactor) {
		INSTANCE.experienceFactor = aExperienceFactor;
	}

	public static void setFormFactor(final double aFormFactor) {
		INSTANCE.formFactor = aFormFactor;
	}

	public static void resetToDefaults() {
		INSTANCE.useStaminaExperienceForm = DEFAULT.useStaminaExperienceForm;
		INSTANCE.staminaFactor = DEFAULT.staminaFactor;
		INSTANCE.experienceFactor = DEFAULT.experienceFactor;
		INSTANCE.formFactor = DEFAULT.formFactor;
	}

}
