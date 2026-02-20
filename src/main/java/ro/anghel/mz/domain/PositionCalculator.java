package ro.anghel.mz.domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Properties;

import ro.anghel.util.NumberFormatUtil;

public class PositionCalculator {

	private Position position;

	private double prSpeed;
	private double prStamina;
	private double prIntelligence;
	private double prPassing;
	private double prShooting;
	private double prHeading;
	private double prKeeping;
	private double prBallControl;
	private double prTackling;
	private double prAerialPassing;
	private double prSetPlays;
	private double prExperience;
	private double prForm;

	public PositionCalculator(final Position position, final double prSpeed,
			final double prStamina, final double prIntelligence,
			final double prPassing, final double prShooting,
			final double prHeading, final double prKeeping,
			final double prBallControl, final double prTackling,
			final double prAerialPassing, final double prSetPlays,
			final double prExperience, final double prForm) {

		this.position = position;
		this.prSpeed = prSpeed;
		this.prStamina = prStamina;
		this.prIntelligence = prIntelligence;
		this.prPassing = prPassing;
		this.prShooting = prShooting;
		this.prHeading = prHeading;
		this.prKeeping = prKeeping;
		this.prBallControl = prBallControl;
		this.prTackling = prTackling;
		this.prAerialPassing = prAerialPassing;
		this.prSetPlays = prSetPlays;
		this.prExperience = prExperience;
		this.prForm = prForm;
	}

	public PositionCalculator(final PositionCalculator other) {
		copy(other);
	}

	public void copy(final PositionCalculator other) {
		position = other.position;
		prSpeed = other.prSpeed;
		prStamina = other.prStamina;
		prIntelligence = other.prIntelligence;
		prPassing = other.prPassing;
		prShooting = other.prShooting;
		prHeading = other.prHeading;
		prKeeping = other.prKeeping;
		prBallControl = other.prBallControl;
		prTackling = other.prTackling;
		prAerialPassing = other.prAerialPassing;
		prSetPlays = other.prSetPlays;
		prExperience = other.prExperience;
		prForm = other.prForm;
	}

	public final Position getPosition() {
		return position;
	}

	public double getPrSpeed() {
		return prSpeed;
	}

	public double getPrStamina() {
		return prStamina;
	}

	public double getPrIntelligence() {
		return prIntelligence;
	}

	public double getPrPassing() {
		return prPassing;
	}

	public double getPrShooting() {
		return prShooting;
	}

	public double getPrHeading() {
		return prHeading;
	}

	public double getPrKeeping() {
		return prKeeping;
	}

	public double getPrBallControl() {
		return prBallControl;
	}

	public double getPrTackling() {
		return prTackling;
	}

	public double getPrAerialPassing() {
		return prAerialPassing;
	}

	public double getPrSetPlays() {
		return prSetPlays;
	}

	public double getPrExperience() {
		return prExperience;
	}

	public double getPrForm() {
		return prForm;
	}

	public double calculateRating(final Player player) {
		double rating = prSpeed * player.getSpeed()
				+ prIntelligence * player.getIntelligence()
				+ prPassing * player.getPassing()
				+ prShooting * player.getShooting()
				+ prHeading * player.getHeading()
				+ prKeeping * player.getKeeping()
				+ prBallControl * player.getBallControl()
				+ prTackling * player.getTackling()
				+ prAerialPassing * player.getAerialPassing()
				+ prSetPlays * player.getSetPlays();

		final PositionCalculatorSettings settings = PositionCalculatorSettings.INSTANCE;
		if (settings.useStaminaExperienceForm()) {

			double varRating = rating * settings.getStaminaFactor() * prStamina
					/ 100;
			double ctRating = rating - varRating;
			rating = ctRating + varRating * player.getStamina() / 10;

			varRating = rating * settings.getExperienceFactor() * prExperience
					/ 100;
			ctRating = rating - varRating;
			rating = ctRating + varRating * player.getExperience() / 10;

			varRating = rating * settings.getFormFactor() * prForm / 100;
			ctRating = rating - varRating;
			rating = ctRating + varRating * player.getForm() / 10;
		}
		return rating / 100;
	}

	public double getPrSum() {
		return prSpeed + prIntelligence + prPassing + prShooting + prHeading
				+ prKeeping + prBallControl + prTackling + prAerialPassing
				+ prSetPlays;
	}

	public final void setPosition(final Position position) {
		this.position = position;
	}

	public void setPrSpeed(final double prSpeed) {
		this.prSpeed = prSpeed;
	}

	public void setPrStamina(final double prStamina) {
		this.prStamina = prStamina;
	}

	public void setPrIntelligence(final double prIntelligence) {
		this.prIntelligence = prIntelligence;
	}

	public void setPrPassing(final double prPassing) {
		this.prPassing = prPassing;
	}

	public void setPrShooting(final double prShooting) {
		this.prShooting = prShooting;
	}

	public void setPrHeading(final double prHeading) {
		this.prHeading = prHeading;
	}

	public void setPrKeeping(final double prKeeping) {
		this.prKeeping = prKeeping;
	}

	public void setPrBallControl(final double prBallControl) {
		this.prBallControl = prBallControl;
	}

	public void setPrTackling(final double prTackling) {
		this.prTackling = prTackling;
	}

	public void setPrAerialPassing(final double prAerialPassing) {
		this.prAerialPassing = prAerialPassing;
	}

	public void setPrSetPlays(final double prSetPlays) {
		this.prSetPlays = prSetPlays;
	}

	public void setPrExperience(final double prExperience) {
		this.prExperience = prExperience;
	}

	public void setPrForm(final double prForm) {
		this.prForm = prForm;
	}

	private static String getConfigFileName() {
		return System.getProperty("user.home") + "/MAMZTool.properties";
	}

	public static void load() {
		final Properties properties = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(getConfigFileName());
		} catch (final IOException ignored) {
		}

		if (in != null) {
			try {
				properties.load(in);
				in.close();
			} catch (final IOException ignored) {
			}
		}

		final NumberFormat fmt3 = NumberFormatUtil.FORMAT_3_3.numberFormat();
		final PositionCalculatorSettings defaultSettings = PositionCalculatorSettings.DEFAULT;

		PositionCalculatorSettings.setUseStaminaExperienceForm(
				getBooleanProperty(properties, "UseStaminaExperienceForm",
						defaultSettings.useStaminaExperienceForm()));
		PositionCalculatorSettings
				.setStaminaFactor(getDoubleProperty(properties, "StaminaFactor",
						defaultSettings.getStaminaFactor(), fmt3));
		PositionCalculatorSettings.setExperienceFactor(
				getDoubleProperty(properties, "ExperienceFactor",
						defaultSettings.getExperienceFactor(), fmt3));
		PositionCalculatorSettings.setFormFactor(getDoubleProperty(properties,
				"FormFactor", defaultSettings.getFormFactor(), fmt3));

		for (final Position pos : Position.values()) {
			load(properties, pos);
		}
	}

	public static void save() {
		final Properties properties = new Properties();
		final PositionCalculatorSettings settings = PositionCalculatorSettings.INSTANCE;
		final NumberFormat fmt3 = NumberFormatUtil.FORMAT_3_3.numberFormat();

		properties.setProperty("UseStaminaExperienceForm",
				Boolean.toString(settings.useStaminaExperienceForm()));
		properties.setProperty("StaminaFactor",
				fmt3.format(settings.getStaminaFactor()));
		properties.setProperty("ExperienceFactor",
				fmt3.format(settings.getExperienceFactor()));
		properties.setProperty("FormFactor",
				fmt3.format(settings.getFormFactor()));

		for (final Position pos : Position.values()) {
			put(properties, pos);
		}

		try (FileOutputStream out = new FileOutputStream(getConfigFileName())) {
			properties.store(out, "");
		} catch (final IOException ignored) {
		}
	}

	private static void put(final Properties where, final Position position) {
		final NumberFormat fmt2 = NumberFormatUtil.FORMAT_2_2.numberFormat();
		final String name = position.name();
		final PositionCalculator pc = position.calculator();

		where.setProperty(name + ".Speed", fmt2.format(pc.prSpeed));
		where.setProperty(name + ".Stamina", fmt2.format(pc.prStamina));
		where.setProperty(name + ".Intelligence",
				fmt2.format(pc.prIntelligence));
		where.setProperty(name + ".Passing", fmt2.format(pc.prPassing));
		where.setProperty(name + ".Shooting", fmt2.format(pc.prShooting));
		where.setProperty(name + ".Heading", fmt2.format(pc.prHeading));
		where.setProperty(name + ".Keeping", fmt2.format(pc.prKeeping));
		where.setProperty(name + ".BallControl", fmt2.format(pc.prBallControl));
		where.setProperty(name + ".Tackling", fmt2.format(pc.prTackling));
		where.setProperty(name + ".CrossBalls",
				fmt2.format(pc.prAerialPassing));
		where.setProperty(name + ".SetPlays", fmt2.format(pc.prSetPlays));
		where.setProperty(name + ".Experience", fmt2.format(pc.prExperience));
		where.setProperty(name + ".Form", fmt2.format(pc.prForm));
	}

	private static void load(final Properties from, final Position position) {
		final NumberFormat fmt2 = NumberFormatUtil.FORMAT_2_2.numberFormat();
		final String name = position.name();
		final PositionCalculator def = position.defaultCalculator();
		final PositionCalculator calc = position.calculator();

		calc.prSpeed = getDoubleProperty(from, name + ".Speed", def.prSpeed,
				fmt2);
		calc.prStamina = getDoubleProperty(from, name + ".Stamina",
				def.prStamina, fmt2);
		calc.prIntelligence = getDoubleProperty(from, name + ".Intelligence",
				def.prIntelligence, fmt2);
		calc.prPassing = getDoubleProperty(from, name + ".Passing",
				def.prPassing, fmt2);
		calc.prShooting = getDoubleProperty(from, name + ".Shooting",
				def.prShooting, fmt2);
		calc.prHeading = getDoubleProperty(from, name + ".Heading",
				def.prHeading, fmt2);
		calc.prKeeping = getDoubleProperty(from, name + ".Keeping",
				def.prKeeping, fmt2);
		calc.prBallControl = getDoubleProperty(from, name + ".BallControl",
				def.prBallControl, fmt2);
		calc.prTackling = getDoubleProperty(from, name + ".Tackling",
				def.prTackling, fmt2);
		calc.prAerialPassing = getDoubleProperty(from, name + ".CrossBalls",
				def.prAerialPassing, fmt2);
		calc.prSetPlays = getDoubleProperty(from, name + ".SetPlays",
				def.prSetPlays, fmt2);
		calc.prExperience = getDoubleProperty(from, name + ".Experience",
				def.prExperience, fmt2);
		calc.prForm = getDoubleProperty(from, name + ".Form", def.prForm, fmt2);
	}

	private static double getDoubleProperty(final Properties from,
			final String key, final double def, final NumberFormat fmt) {
		final String val = from.getProperty(key, fmt.format(def));
		try {
			return fmt.parse(val).doubleValue();
		} catch (final ParseException ignored) {
			return def;
		}
	}

	private static boolean getBooleanProperty(final Properties from,
			final String key, final boolean def) {
		return Boolean
				.parseBoolean(from.getProperty(key, Boolean.toString(def)));
	}

	public static void loadDefaults() {
		PositionCalculatorSettings.resetToDefaults();
		Position.resetPositionCalculatorsToDefaults();
	}
}
