package ro.anghel.mz.domain;

public class Player {

	private long id = 0;

	private Team team = null;

	private boolean resting = false;

	private short number = 0;

	private String name = "";

	private long value = 0;

	private long salary = 0;

	private short age = 0;

	private short height = 0;

	private short weight = 0;

	private short speed = 0;

	private short stamina = 0;

	private short intelligence = 0;

	private short passing = 0;

	private short shooting = 0;

	private short heading = 0;

	private short keeping = 0;

	private short ballControl = 0;

	private short tackling = 0;

	private short aerialPassing = 0;

	private short setPlays = 0;

	private short experience = 0;

	private short form = 0;

	private Foot foot = Foot.BOTH;

	private final String countryShortName;

	private final String countryLongName;

	private FormStatus status = FormStatus.OK; // '+', '-', ' '

	private int goals = 0;

	private int yellowCards = 0;

	private int redCards = 0;

	private String trainingCamp = "";

	private String trainingCampDomain = ""; // general, defender, midfielder,
											// striker

	private short trainingCampNr = 0; // 0...10

	private final Injury injury;

	private boolean junior = false;

	private boolean alreadyAtTrainingCamp = false;

	private boolean dirty = false;

	public Player(final long aId, final Team aTeam, final short aNumber,
			final String aName, final long aValue, final long aSalary,
			final short aAge, final short aHeight, final short aWeight,
			final short aSpeed, final short aStamina, final short aIntelligence,
			final short aPassing, final short aShooting, final short aHeading,
			final short aKeeping, final short aBallControl,
			final short aTackling, final short aAerialPassing,
			final short aSetPlays, final short aExperience, final short aForm,
			final Foot aFoot, final String aCountryShortName,
			final String aCountryLongName, final FormStatus aStatus,
			final int aGoals, final int aYellowCards, final int aRedCards,
			final String aTrainingCamp, final String aTrainingCampDomain,
			final short aTrainingCampNr, final Injury aInjury,
			final boolean aJunior, final boolean aAlreadyAtTrainingCamp) {
		super();
		id = aId;
		team = aTeam;
		number = aNumber;
		name = aName;
		value = aValue;
		salary = aSalary;
		age = aAge;
		height = aHeight;
		weight = aWeight;
		speed = aSpeed;
		stamina = aStamina;
		intelligence = aIntelligence;
		passing = aPassing;
		shooting = aShooting;
		heading = aHeading;
		keeping = aKeeping;
		ballControl = aBallControl;
		tackling = aTackling;
		aerialPassing = aAerialPassing;
		setPlays = aSetPlays;
		experience = aExperience;
		form = aForm;
		foot = aFoot;
		countryShortName = aCountryShortName;
		countryLongName = aCountryLongName;
		status = aStatus;
		goals = aGoals;
		yellowCards = aYellowCards;
		redCards = aRedCards;
		trainingCamp = aTrainingCamp;
		trainingCampDomain = aTrainingCampDomain;
		trainingCampNr = aTrainingCampNr;
		injury = aInjury;
		junior = aJunior;
		alreadyAtTrainingCamp = aAlreadyAtTrainingCamp;

		resting = !("".equals(injury.getInjuryName())
				&& "".equals(trainingCamp));

		if (status == FormStatus.UNKNOWN) {
			status = form >= 7 ? FormStatus.OK : FormStatus.UNKNOWN;
		}
		dirty = false;
	}

	public short getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public long getValue() {
		return value;
	}

	public long getSalary() {
		return salary;
	}

	public short getAge() {
		return age;
	}

	public short getHeight() {
		return height;
	}

	public short getWeight() {
		return weight;
	}

	public short getSpeed() {
		return speed;
	}

	public short getStamina() {
		return stamina;
	}

	public short getIntelligence() {
		return intelligence;
	}

	public short getPassing() {
		return passing;
	}

	public short getShooting() {
		return shooting;
	}

	public short getHeading() {
		return heading;
	}

	public short getKeeping() {
		return keeping;
	}

	public short getBallControl() {
		return ballControl;
	}

	public short getTackling() {
		return tackling;
	}

	public short getAerialPassing() {
		return aerialPassing;
	}

	public short getSetPlays() {
		return setPlays;
	}

	public short getExperience() {
		return experience;
	}

	public short getForm() {
		return form;
	}

	public double getMaxRating() {
		final double m1 = Math.max(
				Position.GK.calculator().calculateRating(this),
				Position.WB.calculator().calculateRating(this));
		final double m2 = Math.max(
				Position.CB.calculator().calculateRating(this),
				Position.DM.calculator().calculateRating(this));
		final double m3 = Math.max(
				Position.CM.calculator().calculateRating(this),
				Position.WM.calculator().calculateRating(this));
		final double m4 = Math.max(
				Position.AM.calculator().calculateRating(this),
				Position.FW.calculator().calculateRating(this));
		final double m12 = Math.max(m1, m2);
		final double m34 = Math.max(m3, m4);
		return Math.max(m12, m34);
	}

	public Position getBestPosition() {
		final double max = getMaxRating();
		for (final Position pos : Position.values()) {
			if (max == pos.calculator().calculateRating(this)) {
				return pos;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(number).append(". ").append(name);
		return sb.toString();
	}

	public boolean isResting() {
		return resting;
	}

	public void setResting(final boolean aResting) {
		if (resting != aResting) {
			resting = aResting;
			dirty = true;
		}
	}

	public boolean isDirty() {
		return dirty;
	}

	public void resetDirty() {
		dirty = false;
	}

	public long getId() {
		return id;
	}

	public Team getTeam() {
		return team;
	}

	public Foot getFoot() {
		return foot;
	}

	public String getCountryShortName() {
		return countryShortName;
	}

	public String getCountryLongName() {
		return countryLongName;
	}

	public FormStatus getStatus() {
		return status;
	}

	public int getGoals() {
		return goals;
	}

	public int getYellowCards() {
		return yellowCards;
	}

	public int getRedCards() {
		return redCards;
	}

	public String getTrainingCamp() {
		return trainingCamp;
	}

	public String getTrainingCampDomain() {
		return trainingCampDomain;
	}

	public short getTrainingCampNr() {
		return trainingCampNr;
	}

	public Injury getInjury() {
		return injury;
	}

	public boolean isJunior() {
		return junior;
	}

	public boolean isAlreadyAtTrainingCamp() {
		return alreadyAtTrainingCamp;
	}
}
