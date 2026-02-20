package ro.anghel.mz.parser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ro.anghel.mz.domain.Foot;
import ro.anghel.mz.domain.FormStatus;
import ro.anghel.mz.domain.Injury;
import ro.anghel.mz.domain.InjurySeverity;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Team;
import ro.anghel.util.StringFormatUtil;

public class ProfilePageTeamParserOld {

	private static final String PLAYER_REG_EX = "(var\\s+skills\\s*=\\s*new\\s+Array\\(\\);.+?;)\\s*\\n";

	private static final Pattern PLAYER_PATTERN = Pattern.compile(PLAYER_REG_EX,
			Pattern.MULTILINE | Pattern.DOTALL);

	private static final String SKILL_REG_EX = "skills\\[\\s*'(\\w+)'\\s*\\]\\s*=\\s*(\\d+);";

	private static final Pattern SKILL_PATTERN = Pattern.compile(SKILL_REG_EX);

	private static final String TRAINING_REGEX = "new\\s+Array\\(" //
			+ "\\s*'(\\d+)'\\s*," //
			+ "\\s*'(.*?)'\\s*," //
			+ "\\s*'(.*?)'\\s*" //
			+ "\\)";

	private static final Pattern TRAINING_PATTERN = Pattern
			.compile(TRAINING_REGEX);

	private static final String ATTR_REGEX = "new\\s+Array\\(" //
			+ "\\s*\\d+\\s*," //
			+ "\\s*\\d+\\s*," //
			+ "\\s*'(\\w)'\\s*," //
			+ "\\s*(\\d+)\\s*," //
			+ "\\s*\\d+\\s*," //
			+ "\\s*\\d+\\s*," //
			+ "\\s*(\\d+)\\s*," //
			+ "\\s*'(.?)'\\s*" //
			+ "\\)";

	private static final Pattern ATTR_PATTERN = Pattern.compile(ATTR_REGEX);

	private static final String STATS_REGEX = "new\\s+Array\\(" //
			+ "\\s*(\\d+)\\s*," //
			+ "\\s*(\\d+)\\s*," //
			+ "\\s*(\\d+)\\s*," //
			+ "\\s*\\d+\\s*" //
			+ "\\)";

	private static final Pattern STATS_PATTERN = Pattern.compile(STATS_REGEX);

	private static final String INJURY_REGEX = "new\\s+Array\\(" //
			+ "\\s*'(.*?)'\\s*," //
			+ "\\s*'(.*?)'\\s*," //
			+ "\\s*(\\d+)\\s*," //
			+ "\\s*'.*?'\\s*," //
			+ "\\s*'.*?'\\s*," //
			+ "\\s*'.*?'\\s*" //
			+ "\\)";

	private static final Pattern INJURY_PATTERN = Pattern.compile(INJURY_REGEX);

	/*
	 * private static final String TEAM_ID_REGEX =
	 * "var\\s+p_steamid\\s*=\\s*(\\d+)\\s*;";
	 * 
	 * private static final Pattern TEAM_ID_PATTERN =
	 * Pattern.compile(TEAM_ID_REGEX);
	 * 
	 * private static final String CURRENCY_REGEX =
	 * "var\\s+user_currency_suffix\\s*=\\s*'(.+?)'\\s*;";
	 * 
	 * private static final Pattern CURRENCY_PATTERN =
	 * Pattern.compile(CURRENCY_REGEX);
	 */

	private static final String[] INST_ARG_OPTION_REG_EX_ARR = {
			"\\s*new\\s*Array\\(.*?\\)\\s*", //
			"\\s*\\w+\\s*", //
			"\\s*\\d+\\s*", //
			"\\s*'.*?'\\s*", //
			"\\s*\".*?\"\\s*" };

	private static final Pattern INST_PATTERN;

	private static final Comparator<Player> PLAYER_BY_NR_COMPARATOR = Comparator
			.comparingInt(Player::getNumber).thenComparingLong(Player::getId);

	static {
		final StringJoiner sjArgs = new StringJoiner("|", "(", ")");
		for (int i = 0; i < INST_ARG_OPTION_REG_EX_ARR.length; ++i) {
			sjArgs.add("(?:" + INST_ARG_OPTION_REG_EX_ARR[i] + ")");
		}

		final String sArgs = sjArgs.toString();
		final StringJoiner sjInst = new StringJoiner(",",
				"new\\s*soccerPlayer\\(", "\\)\\s*;");
		for (int i = 0; i < 47; ++i) {
			sjInst.add(sArgs);
		}
		INST_PATTERN = Pattern.compile(sjInst.toString());
	}

	private final String stringToParse;

	private final Team team;

	public ProfilePageTeamParserOld(final Team team,
			final String stringToParse) {
		super();
		this.stringToParse = stringToParse != null ? stringToParse : "";
		this.team = team;
	}

	public void parse() {
		team.setCurrency("");
		team.setName("Unknown");
		team.setId(-1);

		final Set<Player> players = new TreeSet<>(PLAYER_BY_NR_COMPARATOR);

		final Matcher matcher = PLAYER_PATTERN.matcher(stringToParse);
		while (matcher.find()) {
			final String playerLine = matcher.group(1);
			// System.out.println(playerLine + "\n-------------\n");

			final Player player = parsePlayer(playerLine);
			if (player != null) {
				players.add(player);
			}
		}
		team.setPlayers(players.toArray(new Player[players.size()]));
	}

	private Player parsePlayer(final String playerAsString) {
		final Matcher matchSkills = SKILL_PATTERN.matcher(playerAsString);
		final HashMap<String, String> skillMap = new HashMap<>();
		while (matchSkills.find()) {
			final String sName = matchSkills.group(1);
			final String sVal = matchSkills.group(2);
			// System.out.print(sName + " = " + sVal + ", ");
			skillMap.put(sName, sVal);
		}

		final Matcher match = INST_PATTERN.matcher(playerAsString);
		if (!match.find()) {
			return null;
		}

		final String sId = match.group(1).trim();
		final String sTeamId = match.group(2).trim();
		String teamName = match.group(13).trim();
		teamName = teamName.substring(1, teamName.length() - 1);
		String name = match.group(14).trim();
		name = name.substring(1, name.length() - 1);
		String sTCCurrSeason = match.group(18).trim();
		sTCCurrSeason = sTCCurrSeason.substring(1, sTCCurrSeason.length() - 1);
		String countryShortName = match.group(21).trim();
		countryShortName = countryShortName.substring(1,
				countryShortName.length() - 1);
		String countryLongName = match.group(22).trim();
		countryLongName = countryLongName.substring(1,
				countryLongName.length() - 1);

		final String attrs = match.group(27).trim();
		final Matcher attrsMatch = ATTR_PATTERN.matcher(attrs);
		if (!attrsMatch.matches()) {
			return null;
		}
		final String sFoot = attrsMatch.group(1);
		final String sH = attrsMatch.group(2);
		final String sW = attrsMatch.group(3);
		final String sStatus = attrsMatch.group(4);

		final String stats = match.group(28).trim();
		final Matcher statsMatch = STATS_PATTERN.matcher(stats);
		if (!statsMatch.matches()) {
			return null;
		}
		final String sRC = statsMatch.group(1);
		final String sYC = statsMatch.group(2);
		final String sGoals = statsMatch.group(3);

		final String sNr = match.group(29).trim();
		final String sVal = match.group(31).trim();
		final String sSal = match.group(32).trim();
		final String sAge = match.group(33).trim();

		// 37
		String sPriColor = match.group(37).trim();
		sPriColor = '#' + sPriColor.substring(1, sPriColor.length() - 1);
		String sSecColor = match.group(38).trim();
		sSecColor = '#' + sSecColor.substring(1, sSecColor.length() - 1);
		final String sShirtPattern = match.group(39).trim();

		final String sJunior = match.group(40).trim();
		String currency = match.group(42).trim();
		currency = currency.substring(1, currency.length() - 1);

		final String sInjury = match.group(45).trim();
		String sInjSeverity = "";
		String injuryName = "";
		String sInjuryDays = "0";
		if (!"''".equals(sInjury)) {
			final Matcher injMatch = INJURY_PATTERN.matcher(sInjury);
			if (!injMatch.matches()) {
				return null;
			}
			sInjSeverity = injMatch.group(1).toUpperCase();
			injuryName = StringFormatUtil.capitalize(injMatch.group(2).trim());
			sInjuryDays = injMatch.group(3).trim();

		}

		final String training = match.group(46).trim();
		String trainingCamp = "";
		String trainingCampDomain = "";
		String sTrainingCampNr = "0";
		if (!"''".equals(training)) {
			final Matcher trMatch = TRAINING_PATTERN.matcher(training);
			if (!trMatch.matches()) {
				return null;
			}
			sTrainingCampNr = trMatch.group(1).trim();
			trainingCamp = trMatch.group(2).trim();
			trainingCampDomain = trMatch.group(3).trim();

		}

		/*
		 * System.out.println(sId + ", " + sTeamId + ", " + teamName + ", " +
		 * name + ", " + sTCCurrSeason + ", " + countryShortName + ", " +
		 * countryLongName + ", " + sFoot + ", " + sH + ", " + sW + ", " +
		 * sStatus + ", " + sGoals + ", " + sYC + ", " + sRC + ", " + sNr + ", "
		 * + sVal + ", " + sSal + ", " + sAge + ", " + sJunior + ", " + currency
		 * + ", " + injurySeverity + ", " + injuryName + ", " + sInjuryDays +
		 * ", " + trainingCamp + ", " + trainingCampDomain + ", " +
		 * sTrainingCampNr);
		 */

		final short number = Short.parseShort(sNr);
		if (-1 == team.getId()) {
			team.setId(Long.parseLong(sTeamId));
			team.setCurrency(currency);
			team.setName(teamName);
			team.setPriColor(sPriColor);
			team.setSecColor(sSecColor);
			team.setShirtPattern(Integer.parseInt(sShirtPattern));
		}
		final long value = Long.parseLong(sVal);
		final long salary = Long.parseLong(sSal);
		final short age = Short.parseShort(sAge);
		final short height = Short.parseShort(sH);
		final short weight = Short.parseShort(sW);

		final short speed = Short
				.parseShort(getNumberFromMap(skillMap, "attrspeed"));
		final short stamina = Short
				.parseShort(getNumberFromMap(skillMap, "attrstamina"));
		final short intelligence = Short.parseShort(
				getNumberFromMap(skillMap, "skillgameintelligence"));
		final short passing = Short
				.parseShort(getNumberFromMap(skillMap, "skillpassing"));
		final short shooting = Short
				.parseShort(getNumberFromMap(skillMap, "skillshooting"));
		final short heading = Short
				.parseShort(getNumberFromMap(skillMap, "skillheading"));
		final short keeping = Short
				.parseShort(getNumberFromMap(skillMap, "skillgoalkeeping"));
		final short ballControl = Short
				.parseShort(getNumberFromMap(skillMap, "skilltechnique"));
		final short tackling = Short
				.parseShort(getNumberFromMap(skillMap, "skilltackling"));
		final short aerialPassing = Short
				.parseShort(getNumberFromMap(skillMap, "skillhighpassing"));
		final short setPlays = Short
				.parseShort(getNumberFromMap(skillMap, "skillsituations"));
		final short experience = Short
				.parseShort(getNumberFromMap(skillMap, "attrexperience"));
		final short form = Short
				.parseShort(getNumberFromMap(skillMap, "attrform"));

		final long id = Long.parseLong(sId);
		final Foot foot = Foot.fromString(sFoot);
		final FormStatus status = FormStatus
				.fromChar((sStatus + " ").charAt(0));

		final int goals = Integer.parseInt(sGoals);
		final int yc = Integer.parseInt(sYC);
		final int rc = Integer.parseInt(sRC);
		final short trCampNr = Short.parseShort(sTrainingCampNr);
		final int injDays = Integer.parseInt(sInjuryDays);
		final boolean junior = !"0".equals(sJunior);
		final boolean tcThisSeason = "y".equalsIgnoreCase(sTCCurrSeason);
		final InjurySeverity injurySeverity = "".equals(sInjSeverity)
				? InjurySeverity.NONE
				: InjurySeverity.valueOf(sInjSeverity);
		final Injury injury = new Injury(injurySeverity, injuryName, injDays);
		return new Player(id, team, number, name, value, salary, age, height,
				weight, speed, stamina, intelligence, passing, shooting,
				heading, keeping, ballControl, tackling, aerialPassing,
				setPlays, experience, form, foot, countryShortName,
				countryLongName, status, goals, yc, rc, trainingCamp,
				trainingCampDomain, trCampNr, injury, junior, tcThisSeason);

	}

	private static String getNumberFromMap(final Map<String, String> map,
			final String key) {
		final String val = map.get(key);
		return val != null ? val : "0";
	}
}
